package com.example.vjoshi.wattsapp.addDeviceClasses;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.vjoshi.wattsapp.Device;
import com.example.vjoshi.wattsapp.Home_Activity;
import com.example.vjoshi.wattsapp.R;

import java.util.Random;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

public class ModelSelectionActivity extends AppCompatActivity {

    String[] applePhoneModel = {"iPhone 6","iPhone 6s","iPhone 8","iPhone 8s","iPhone X","iPhone XS","iPhone XR"};
    String[] googlePhoneModel = {"Pixel", "Pixel 2", "Pixel 3", "Nexus"};

    Bundle modelBundle;
    ListView modelList;
    ListAdapter listAdapter;

    public void setListAdapter(String company, String device){
        switch (device){
            case "Laptop":
                break;
            case "Desktop":
                break;
            case "Phone":
                if(company.equalsIgnoreCase("Apple")){
                    listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, applePhoneModel);
                } else if(company.equalsIgnoreCase("Google")){
                    listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, googlePhoneModel);
                }
                break;
            case "Tablet":
                break;
            case "TV":
                break;
            case "Smart Watch":
                break;
        }
        modelList.setAdapter(listAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_selection);

        setTitle("Select Model");

        modelList = findViewById(R.id.modelSelection);

        final Intent modelIntent = getIntent();
        modelBundle = modelIntent.getExtras();

        final String device = modelBundle.getString(DEVICENAME);
        final String company = modelBundle.getString(COMPANYNAME);

        setListAdapter(company, device);


        modelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedModel = String.valueOf(parent.getItemAtPosition(position));
                modelBundle.putString(MODELNAME, selectedModel);
                createAlert(device, company,selectedModel);

            }
        });

    }

    public void createAlert(final String device, final String company, final String model){

        final Intent mainIntent = new Intent(this, Home_Activity.class);
        final Intent deviceIntent = new Intent(this, DeviceSelectionActivity.class);

        final AlertDialog.Builder builder = new AlertDialog.Builder(ModelSelectionActivity.this);
        builder.setTitle(R.string.app_name);
        final String message = "Device: " + device + " | " + "Company: " + company + " | " + "Model: " + model;
        builder.setMessage("Do you wish to add this device? \n" + company + " " + model);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Device newDevice = new Device((int) (Math.random() * 50 + 1), device, company, model, 0.0);
                dialog.dismiss();
                startActivity(mainIntent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.i(TAG, "Device not selected");
                dialog.dismiss();
                startActivity(deviceIntent);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
