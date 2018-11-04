package com.example.vjoshi.wattsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.vjoshi.wattsapp.addDeviceClasses.DeviceSelectionActivity;

public class Home_Activity extends AppCompatActivity {

    Bundle mainBundle = new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Watt's App");

        final User user = new User();


        final Intent deviceIntent = new Intent(this, DeviceSelectionActivity.class);
        //https://www.dev2qa.com/android-alert-dialog-example/
        //May want to do selection with dialogs
        final Button addDeviceButton = findViewById(R.id.itemButton);
        addDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Need to pass user so we can add device
                startActivity(deviceIntent);
            }
        });

    }
}
