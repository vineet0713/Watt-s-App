package com.example.vjoshi.wattsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.example.vjoshi.wattsapp.addDeviceClasses.DeviceSelectionActivity;
import com.example.vjoshi.wattsapp.profile.ProfileActivity;

import java.util.ArrayList;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

// For Redeem class
// https://www.youtube.com/watch?v=hl0AcuplFwE

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private static GridLayout gridLayout = null;
    private static Context context = null;

    private static String company, model;
    private static int count = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Watt's App");

        final User user = new User();
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
                Device deviceToAdd = new Device(10, "Desktop", "Apple", "Mac Mini", 0.09);
                Backend.getInstance().addDevice(deviceToAdd);

                // for testing purposes, returns a list of all devices
                ArrayList<Device> userDevices = Backend.getInstance().getDevices();
                Log.d(TAG, "about to print");
                for (Device d : userDevices) {
                    Log.d(TAG, d.toString());
                }

                //Need to pass user so we can add device
                startActivity(deviceIntent);
            }
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

    private void logout() {
        MySQLiteHelper myDB = new MySQLiteHelper(this);
        myDB.clearUsername();
    }
}
