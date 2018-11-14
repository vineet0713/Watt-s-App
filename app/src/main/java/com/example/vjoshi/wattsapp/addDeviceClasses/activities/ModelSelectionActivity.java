package com.example.vjoshi.wattsapp.addDeviceClasses.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.vjoshi.wattsapp.Backend;
import com.example.vjoshi.wattsapp.Device;
import com.example.vjoshi.wattsapp.HomeActivity;
import com.example.vjoshi.wattsapp.R;
import com.example.vjoshi.wattsapp.addDeviceClasses.adapters.CompanyRecyclerViewAdapter;
import com.example.vjoshi.wattsapp.addDeviceClasses.adapters.ModelRecyclerViewAdapter;

import java.util.ArrayList;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

public class ModelSelectionActivity extends AppCompatActivity {

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        Log.d(TAG, "onCreate: started");
        setTitle("Select Model");

        String device = HomeActivity.getDevice();
        String company =  HomeActivity.getCompany();

        Log.d(TAG, "COMPANY: " + company + " | DEVICE: " + device);
        switch (device){
            case LAPTOP:
                break;
            case DESKTOP:
                break;
            case PHONE:
                phoneCompany(company);
                break;
            case TABLET:
                break;
            case TELEVISION:
                break;
            case WATCH:
                break;
        }

    }
    private void laptopCompany(String company){
        switch (company){
            case APPLE:
                break;
            case DELL:
                break;
            case WINDOWS:
                break;
            case ASUS:
                break;
            case HP:
                break;
            case GOOGLE:
                break;
            case NOKIA:
                break;
            case HUAWEI:
                break;
            case SAMSUNG:
                break;
            case AMAZON:
                break;
            case LENOVO:
                break;
            case LG:
                break;
            case TOSHIBA:
                break;
            case TCL:
                break;
            case SONY:
                break;
            case VIZIO:
                break;
            case FITBIT:
                break;
        }
    }

    private void desktopompany(String company){
        switch (company){
            case APPLE:
                break;
            case DELL:
                break;
            case WINDOWS:
                break;
            case ASUS:
                break;
            case HP:
                break;
            case GOOGLE:
                break;
            case NOKIA:
                break;
            case HUAWEI:
                break;
            case SAMSUNG:
                break;
            case AMAZON:
                break;
            case LENOVO:
                break;
            case LG:
                break;
            case TOSHIBA:
                break;
            case TCL:
                break;
            case SONY:
                break;
            case VIZIO:
                break;
            case FITBIT:
                break;
        }
    }

    private void phoneCompany(String company){
        Log.d(TAG, "Phone Company");
        switch (company){
            case APPLE:
                initApplePhoneBitMaps();
                break;
            case DELL:
                break;
            case WINDOWS:
                break;
            case ASUS:
                break;
            case HP:
                break;
            case GOOGLE:
                break;
            case NOKIA:
                break;
            case HUAWEI:
                break;
            case SAMSUNG:
                break;
            case AMAZON:
                break;
            case LENOVO:
                break;
            case LG:
                break;
            case TOSHIBA:
                break;
            case TCL:
                break;
            case SONY:
                break;
            case VIZIO:
                break;
            case FITBIT:
                break;
        }
    }

    private void tabletCompany(String company){
        switch (company){
            case APPLE:
                break;
            case DELL:
                break;
            case WINDOWS:
                break;
            case ASUS:
                break;
            case HP:
                break;
            case GOOGLE:
                break;
            case NOKIA:
                break;
            case HUAWEI:
                break;
            case SAMSUNG:
                break;
            case AMAZON:
                break;
            case LENOVO:
                break;
            case LG:
                break;
            case TOSHIBA:
                break;
            case TCL:
                break;
            case SONY:
                break;
            case VIZIO:
                break;
            case FITBIT:
                break;
        }
    }

    private void televisionCompany(String company){
        switch (company){
            case APPLE:
                break;
            case DELL:
                break;
            case WINDOWS:
                break;
            case ASUS:
                break;
            case HP:
                break;
            case GOOGLE:
                break;
            case NOKIA:
                break;
            case HUAWEI:
                break;
            case SAMSUNG:
                break;
            case AMAZON:
                break;
            case LENOVO:
                break;
            case LG:
                break;
            case TOSHIBA:
                break;
            case TCL:
                break;
            case SONY:
                break;
            case VIZIO:
                break;
            case FITBIT:
                break;
        }
    }

    private void watchCompany(String company){
        switch (company){
            case APPLE:
                break;
            case DELL:
                break;
            case WINDOWS:
                break;
            case ASUS:
                break;
            case HP:
                break;
            case GOOGLE:
                break;
            case NOKIA:
                break;
            case HUAWEI:
                break;
            case SAMSUNG:
                break;
            case AMAZON:
                break;
            case LENOVO:
                break;
            case LG:
                break;
            case TOSHIBA:
                break;
            case TCL:
                break;
            case SONY:
                break;
            case VIZIO:
                break;
            case FITBIT:
                break;
        }
    }

    private void initApplePhoneBitMaps(){

        Log.d(TAG, "Apple Phone BitMaps");

        mImageUrls.add(APPLEURL);
        mNames.add(IPHONE6);

        mImageUrls.add(APPLEURL);
        mNames.add(IPHONE6S);

        mImageUrls.add(APPLEURL);
        mNames.add(IPHONE8);

        mImageUrls.add(APPLEURL);
        mNames.add(IPHONE8S);

        mImageUrls.add(APPLEURL);
        mNames.add(IPHONEX);

        mImageUrls.add(APPLEURL);
        mNames.add(IPHONEXS);

        mImageUrls.add(APPLEURL);
        mNames.add(IPHONEXR);

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ModelRecyclerViewAdapter adapter = new ModelRecyclerViewAdapter(mNames, mImageUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
