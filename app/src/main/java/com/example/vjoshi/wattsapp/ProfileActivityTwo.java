package com.example.vjoshi.wattsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ProfileActivityTwo extends FragmentActivity implements DeviceUsage.OnFragmentInteractionListener,DeviceTypeUsage.OnFragmentInteractionListener,UsageHistory.OnFragmentInteractionListener {
    private ViewPager mViewPager;
    private int mPagerPosition;
    private int mPagerOffsetPixels;

    private Button logoutButton;
    private TextView pointsField, todaysUsageField;

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.swipeback_stack_to_front,
                R.anim.swipeback_stack_right_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile_graphs);
        SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.activity_profile_graphs)
                .setSwipeBackView(R.layout.swipeback_default);

        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivityTwo.this);
                builder.setTitle("Confirm Logout");
                builder.setMessage("Are you sure you want to logout '" + Backend.getInstance().getUsername() + "'?");
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                });

                builder.show();
            }
        });

        pointsField = findViewById(R.id.pointsView);
        todaysUsageField = findViewById(R.id.usageView);

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final String username = Backend.getInstance().getUsername();
        database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                pointsField.setText("Points: " + user.getTotalPoints());

                ArrayList<UsageEntry> entries = user.getUsageEntries();

                float todaysWatts = 0;
                if (entries.size() > 0) {
                    Calendar calendar = Calendar.getInstance();
                    // sets the calendar to the current time
                    calendar.setTime(new Date(System.currentTimeMillis()));
                    int todayIndex = calendar.get(Calendar.DAY_OF_WEEK);

                    int entryIndex = entries.size() - 1;

                    calendar.setTime(entries.get(entryIndex).getUsageDate());
                    int currentDayIndex = calendar.get(Calendar.DAY_OF_WEEK);

                    while (currentDayIndex == todayIndex) {
                        todaysWatts += entries.get(entryIndex).getWattsUsed();
                        entryIndex--;
                        if (entryIndex < 0) {
                            break;
                        }

                        calendar.setTime(entries.get(entryIndex).getUsageDate());
                        currentDayIndex = calendar.get(Calendar.DAY_OF_WEEK);
                    }
                }

                todaysUsageField.setText("Today's Usage: " + todaysWatts + " Watts");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Devices"));
        tabLayout.addTab(tabLayout.newTab().setText("Types"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void logout() {
        final Intent logoutIntent = new Intent(this, SignInActivity.class);

        MySQLiteHelper myDB = new MySQLiteHelper(this);
        myDB.clearUsername();
        myDB.close();

        startActivity(logoutIntent);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}