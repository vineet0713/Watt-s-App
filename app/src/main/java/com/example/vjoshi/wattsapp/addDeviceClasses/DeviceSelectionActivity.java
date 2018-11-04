package com.example.vjoshi.wattsapp.addDeviceClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.vjoshi.wattsapp.R;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

public class DeviceSelectionActivity extends AppCompatActivity {

    Bundle deviceBundle = new Bundle();

    public void cleanBundle(){
        deviceBundle.remove(INDEX);
        deviceBundle.remove(DEVICENAME);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_selection);

        setTitle("Select Device");

        final Intent companyIntent = new Intent(this, CompanySelectionActivity.class);

        String[] devices = {"Laptop", "Desktop","Phone","Tablet","TV","Smart Watch"};

        final ListView deviceList = findViewById(R.id.deviceList);
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, devices);
        deviceList.setAdapter(listAdapter);

        deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedDevice = String.valueOf(parent.getItemAtPosition(position));
                deviceBundle.putString(DEVICENAME, selectedDevice);
                deviceBundle.putInt(INDEX, position);
                companyIntent.putExtras(deviceBundle);
                startActivity(companyIntent);
            }
        });

    }
}
