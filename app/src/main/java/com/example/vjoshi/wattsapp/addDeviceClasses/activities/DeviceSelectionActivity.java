package com.example.vjoshi.wattsapp.addDeviceClasses.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vjoshi.wattsapp.R;
import com.example.vjoshi.wattsapp.addDeviceClasses.adapters.DeviceRecyclerViewAdapter;
import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;

import java.util.ArrayList;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

public class DeviceSelectionActivity extends AppCompatActivity {

    final static Bundle deviceBundle = new Bundle();

    private static final String TAG = "DeviceSelectionActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    public void cleanBundle(){
        deviceBundle.remove(INDEX);
        deviceBundle.remove(DEVICENAME);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout_recycler_view);
        SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.layout_recycler_view)
                .setSwipeBackView(R.layout.swipeback_default);


        Log.d(TAG, "onCreate: started");
        setTitle("Select Device");

        initImageBitMaps();

    }
    private void initImageBitMaps(){
        Log.d(TAG, "initImageBitMaps: preparing bitMaps");

        mImageUrls.add("https://cdn3.iconfinder.com/data/icons/computers-programming/512/Laptop_B-512.png");
        mNames.add(LAPTOP);

        mImageUrls.add("https://cdn3.iconfinder.com/data/icons/computers-programming/512/computer-512.png");
        mNames.add(DESKTOP);

        mImageUrls.add("https://cdn2.iconfinder.com/data/icons/picons-essentials/71/mobile-512.png");
        mNames.add(PHONE);

        mImageUrls.add("https://cdn2.iconfinder.com/data/icons/pittogrammi/142/05-512.png");
        mNames.add(TABLET);

        mImageUrls.add("https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/tv-512.png");
        mNames.add(TELEVISION);

        mImageUrls.add("https://cdn3.iconfinder.com/data/icons/smartwatch-5/30/smart-watch-7-512.png");
        mNames.add(WATCH);

        initRecyclerView();

    }


    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        DeviceRecyclerViewAdapter adapter = new DeviceRecyclerViewAdapter(mNames, mImageUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
