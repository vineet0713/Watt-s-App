package com.example.vjoshi.wattsapp.addDeviceClasses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.vjoshi.wattsapp.HomeActivity;
import com.example.vjoshi.wattsapp.R;
import com.example.vjoshi.wattsapp.addDeviceClasses.adapters.CompanyRecyclerViewAdapter;
import com.example.vjoshi.wattsapp.addDeviceClasses.adapters.DeviceRecyclerViewAdapter;

import java.util.ArrayList;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

public class CompanySelectionActivity extends AppCompatActivity {

    Bundle companyBundle;

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    public void cleanBundle(){
        companyBundle.remove(INDEX);
        companyBundle.remove(DEVICENAME);
        companyBundle.remove(COMPANYNAME);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        Log.d(TAG, "onCreate: started");
        setTitle("Select Company");

        switch (HomeActivity.getDevice()){

            case LAPTOP:
                Log.d(TAG, "Laptop InitBitmaps()");
                initLaptopImageBitMaps();
                break;
            case DESKTOP:
                initDesktopImageBitMaps();
                break;
            case PHONE:
                initPhoneImageBitMaps();
                break;
            case TABLET:
                initTabletImageBitMaps();
                break;
            case TELEVISION:
                initTVImageBitMaps();
                break;
            case WATCH:
                initWatchImageBitMaps();
                break;
        }

    }

    private void initLaptopImageBitMaps(){

        mImageUrls.add(APPLEURL);
        mNames.add(APPLE);

        mImageUrls.add(DELLURL);
        mNames.add(DELL);

        mImageUrls.add(WINDOWSURL);
        mNames.add(WINDOWS);

        mImageUrls.add(ASUSURL);
        mNames.add(ASUS);

        mImageUrls.add(HPURL);
        mNames.add(HP);

        mImageUrls.add(GOOGLEURL);
        mNames.add(GOOGLE);

        initRecyclerView();
    }
    private void initDesktopImageBitMaps(){

        mImageUrls.add(APPLEURL);
        mNames.add(APPLE);

        mImageUrls.add(DELLURL);
        mNames.add(DELL);

        mImageUrls.add(WINDOWSURL);
        mNames.add(WINDOWS);

        mImageUrls.add(ASUSURL);
        mNames.add(ASUS);

        mImageUrls.add(HPURL);
        mNames.add(HP);

        initRecyclerView();

    }
    private void initPhoneImageBitMaps(){

        mImageUrls.add(APPLEURL);
        mNames.add(APPLE);

        mImageUrls.add(WINDOWSURL);
        mNames.add(WINDOWS);

        mImageUrls.add(NOKIAURL);
        mNames.add(NOKIA);

        mImageUrls.add(HUAWEIURL);
        mNames.add(HUAWEI);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNG);

        mImageUrls.add(GOOGLEURL);
        mNames.add(GOOGLE);

        initRecyclerView();

    }
    private void initTabletImageBitMaps(){

        mImageUrls.add(APPLEURL);
        mNames.add(APPLE);

        mImageUrls.add(WINDOWSURL);
        mNames.add(WINDOWS);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNG);

        mImageUrls.add(AMAZONURL);
        mNames.add(AMAZON);

        mImageUrls.add(LENOVOURL);
        mNames.add(LENOVO);

        initRecyclerView();

    }
    private void initTVImageBitMaps(){

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNG);

        mImageUrls.add(LGURL);
        mNames.add(LG);

        mImageUrls.add(TOSHIBAURL);
        mNames.add(TOSHIBA);

        mImageUrls.add(TCLURL);
        mNames.add(TCL);

        mImageUrls.add(SONYURL);
        mNames.add(SONY);

        mImageUrls.add(VIZIO);
        mNames.add(VIZIO);

        initRecyclerView();

    }
    private void initWatchImageBitMaps(){

        mImageUrls.add(FITBIT);
        mNames.add(FITBIT);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNG);

        mImageUrls.add(APPLEURL);
        mNames.add(APPLE);

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        CompanyRecyclerViewAdapter adapter = new CompanyRecyclerViewAdapter(mNames, mImageUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
