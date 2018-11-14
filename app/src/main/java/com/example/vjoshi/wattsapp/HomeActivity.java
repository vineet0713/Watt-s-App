package com.example.vjoshi.wattsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.example.vjoshi.wattsapp.addDeviceClasses.activities.DeviceSelectionActivity;
import com.example.vjoshi.wattsapp.profile.ProfileActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

// For Redeem class
// https://www.youtube.com/watch?v=hl0AcuplFwE

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private DatabaseReference database;

    private ArrayList<Device> devices;

    private static GridLayout gridLayout = null;
    private static Context context = null;

    private static String device, company, model;
    private static int count = 0;


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

        devices = new ArrayList<>();

        final Intent profileIntent = new Intent(this, ProfileActivity.class);
        final Intent redeemIntent = new Intent(this, TestRedeemActivity.class);
        final Intent deviceIntent = new Intent(this, DeviceSelectionActivity.class);

        final ImageButton profileButton = findViewById(R.id.profileButton);
        final ImageButton redeemButtom = findViewById(R.id.redeemButton);
        final ImageButton leaderboardButton = findViewById(R.id.leaderboardButton);
        final Button addDeviceButton = findViewById(R.id.itemButton);

        final ScrollView scrollView = findViewById(R.id.scrollView);
        gridLayout = findViewById(R.id.gridLayout);
        context = this;


//        final Button testButton = findViewById(R.id.testButton);
//        testButton.setOnClickListener(new View.OnClickListener() {
//            int count = 0;
//            @Override
//            public void onClick(View v) {
//                Button newButton = new Button(HomeActivity.this);
//                newButton.setText("Button " + count++);
//                gridLayout.addView(newButton);
//
//            }
//        });


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
                // for testing purposes, adds a sample device:
                //Device deviceToAdd = new Device(10, "Desktop", "Apple", "Mac Mini", 0.09);
                //Backend.getInstance().addDevice(deviceToAdd);

                // for testing purposes, returns a list of all devices
                //ArrayList<Device> userDevices = Backend.getInstance().getDevices();
//                Log.d(TAG, "about to print");
//                for (Device d : userDevices) {
//                    Log.d(TAG, d.toString());
//                }

                //Need to pass user so we can add device
                startActivity(deviceIntent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        String username = Backend.getInstance().getUsername();
        database.child(username).child("devices").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Device d = dataSnapshot.getValue(Device.class);
                devices.add(d);
                devicesUpdated();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Device d = dataSnapshot.getValue(Device.class);
                devices.remove(d);
                devicesUpdated();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    public static void setButtonParameters(Bundle bundle){
        //company = bundle.getString(MODELNAME);
        model = bundle.getString(MODELNAME);
    }

    public static void addDeviceButton(){
        Button newButton = new Button(context);
        System.out.println("Model: " + model);
        newButton.setText(model);
        gridLayout.addView(newButton);
    }

    /*
    NOTE TO KEN:
    THE FOLLOWING METHOD IS CALLED EVERY TIME A DEVICE IS ADDED OR REMOVED
    */
    private void devicesUpdated() {
        Log.d(TAG, "Updated 'devices' ArrayList: " + devices.toString());
    }

    private void logout() {
        MySQLiteHelper myDB = new MySQLiteHelper(this);
        myDB.clearUsername();
        myDB.close();
    }
}
