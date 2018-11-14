package com.example.vjoshi.wattsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.vjoshi.wattsapp.addDeviceClasses.activities.DeviceSelectionActivity;
import com.example.vjoshi.wattsapp.profile.ProfileActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

// For Redeem class
// https://www.youtube.com/watch?v=hl0AcuplFwE

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private DatabaseReference database;
    private ChildEventListener listener;

    private ArrayList<Device> devices =  new ArrayList<>();;

    private static GridLayout gridLayout = null;
    private static Context context = null;

    private static String device, company, model, longPressButton;

    private int buttonRow, buttonCol;
    private User user;



    public static String getDevice() {
        return device;
    }

    public static void setDevice(String device) {
        HomeActivity.device = device;
    }

    public static String getCompany() {
        return company;
    }

    public static void setCompany(String company) {
        HomeActivity.company = company;
    }

    public static String getModel() {
        return model;
    }

    public static void setModel(String model) {
        HomeActivity.model = model;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Watt's App");

        // this is the root of the database
        database = FirebaseDatabase.getInstance().getReference();

        final Intent profileIntent = new Intent(this, ProfileActivity.class);
        final Intent redeemIntent = new Intent(this, TestRedeemActivity.class);
        final Intent deviceIntent = new Intent(this, DeviceSelectionActivity.class);

        final ImageButton profileButton = findViewById(R.id.profileButton);
        final ImageButton redeemButtom = findViewById(R.id.redeemButton);
        final ImageButton leaderboardButton = findViewById(R.id.leaderboardButton);
        final Button addDeviceButton = findViewById(R.id.itemButton);

        gridLayout = findViewById(R.id.gridLayout);
        context = this;


        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for testing purposes, adds a sample set of 3 users
                Backend.getInstance().addSampleUsers(3);

                // for testing purposes, clear the username as if the user has logged out
                logout();

                startActivity(profileIntent);
            }
        });

        redeemButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(redeemIntent);
            }
        });

        //https://www.javatpoint.com/android-context-menu-example
        //Context Menu for delete device
        addDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(deviceIntent);
            }
        });

        loadDevices();
        registerForContextMenu(addDeviceButton);
    }



    private void loadDevices() {
        String username = Backend.getInstance().getUsername();
        database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                addDeviceButtons(user.getDevices());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void addDeviceButtons(ArrayList<Device> devices) {
        for(int i =0; i < devices.size(); i++){
            final int position = i;
            Device d = devices.get(i);
            final Button newButton = new Button(context);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(20, 20, 20, 20);
            params.width = 250;
            params.height = 150;
            newButton.setLayoutParams(params);
            newButton.setText(d.getModel());
            newButton.setBackgroundResource(R.drawable.button_bg);
            registerForContextMenu(newButton);
            gridLayout.addView(newButton);

            newButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longPressButton = newButton.getText().toString() + " " +position;
                    //Toast.makeText(getApplicationContext(),newButton.getText() + " button has been pressed",Toast.LENGTH_LONG).show();
                    return false;
                }
            });

            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }

    private void logout() {
        MySQLiteHelper myDB = new MySQLiteHelper(this);
        myDB.clearUsername();
        myDB.close();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.setHeaderTitle("Select The Action");
    }

    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.edit){
            Toast.makeText(getApplicationContext(),"Editing Device",Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.delete){
            Toast.makeText(getApplicationContext(),"Deleting Device " + longPressButton,Toast.LENGTH_LONG).show();

        }else{
            return false;
        }
        return true;
    }

}
