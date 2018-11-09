package com.example.vjoshi.wattsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.vjoshi.wattsapp.addDeviceClasses.DeviceSelectionActivity;
import com.example.vjoshi.wattsapp.profile.ProfileActivity;
import com.example.vjoshi.wattsapp.profile.TopProfileFragment;

import java.util.ArrayList;
// For Redeem class
// https://www.youtube.com/watch?v=hl0AcuplFwE

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    Bundle mainBundle = new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Watt's App");

        final User user = new User();


        final Intent deviceIntent = new Intent(this, DeviceSelectionActivity.class);
        //https://www.javatpoint.com/android-context-menu-example
        //Context Menu for delete device
        //https://www.dev2qa.com/android-alert-dialog-example/
        //May want to do selection with dialogs
        final Button addDeviceButton = findViewById(R.id.itemButton);
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

        final Intent profileIntent = new Intent(this, ProfileActivity.class);
        final Button profileButton = findViewById(R.id.profileButton);
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

    }

    private void logout() {
        MySQLiteHelper myDB = new MySQLiteHelper(this);
        myDB.clearUsername();
    }
}
