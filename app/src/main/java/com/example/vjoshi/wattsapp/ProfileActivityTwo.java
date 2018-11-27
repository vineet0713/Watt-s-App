package com.example.vjoshi.wattsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vjoshi.wattsapp.profile.ProfileActivity;
import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;


public class ProfileActivityTwo extends AppCompatActivity implements DeviceUsage.OnFragmentInteractionListener,DeviceTypeUsage.OnFragmentInteractionListener,UsageHistory.OnFragmentInteractionListener {
    private ViewPager mViewPager;
    private int mPagerPosition;
    private int mPagerOffsetPixels;

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


        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivityTwo.this);
                builder.setTitle("Confirm Logout");
                builder.setMessage("Are you sure you want to logout?");
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

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Devices"));
        tabLayout.addTab(tabLayout.newTab().setText("Types"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));





//        LinearLayout rootLayout = findViewById(R.id.profileRootLayout);
//        rootLayout.setOnTouchListener(new OnSwipeTouchListener(ProfileActivityTwo.this) {
//            public void onSwipeRight() {
//                Toast.makeText(ProfileActivityTwo.this, "Right", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//
//        });
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