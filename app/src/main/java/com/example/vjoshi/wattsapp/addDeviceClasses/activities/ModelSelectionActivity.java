package com.example.vjoshi.wattsapp.addDeviceClasses.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vjoshi.wattsapp.HomeActivity;
import com.example.vjoshi.wattsapp.R;
import com.example.vjoshi.wattsapp.addDeviceClasses.adapters.ModelRecyclerViewAdapter;
import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;

import java.util.ArrayList;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

public class ModelSelectionActivity extends AppCompatActivity {

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout_recycler_view);
        SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.layout_recycler_view)
                .setSwipeBackView(R.layout.swipeback_default);

        Log.d(TAG, "onCreate: started");
        setTitle("Select Model");

        String device = HomeActivity.getDevice();
        String company =  HomeActivity.getCompany();

        Log.d(TAG, "COMPANY: " + company + " | DEVICE: " + device);
        switch (device){
            case LAPTOP:
                laptopCompany(company);
                break;
            case DESKTOP:
                desktopompany(company);
                break;
            case PHONE:
                phoneCompany(company);
                break;
            case TABLET:
                tabletCompany(company);
                break;
            case TELEVISION:
                televisionCompany(company);
                break;
            case WATCH:
                watchCompany(company);
                break;
        }

    }

    //Device Company Selection
    private void laptopCompany(String company){
        switch (company){
            case APPLE:
                initAppleLaptopBitMaps();
                break;
            case DELL:
                initDellLaptopBitMap();
                break;
            case ASUS:
                initAsusLaptopBitMap();
                break;
            case HP:
                initHPLaptopBitMap();
                break;

        }
    }
    private void desktopompany(String company){
        switch (company){
            case APPLE:
                initAppleDesktopBitMap();
                break;
            case DELL:
                initDellDesktopBitMap();
                break;
            case ASUS:
                initAsusDesktopBitMap();
                break;
            case HP:
                initHPDesktopBitMap();
                break;
        }
    }
    private void phoneCompany(String company){
        Log.d(TAG, "Phone Company");
        switch (company){
            case APPLE:
                initApplePhoneBitMaps();
                break;
            case MICROSOFT:
                initMicrosoftPhoneBitMaps();
                break;
            case GOOGLE:
                initGooglePhoneBitMaps();
                break;
            case SAMSUNG:
                initSamsungPhoneBitMaps();
                break;
        }
    }
    private void tabletCompany(String company){
        switch (company){
            case APPLE:
                initAppleTabletBitMap();
                break;
            case MICROSOFT:
                initMicrosoftTabletBitMap();
                break;
            case LENOVO:
                initLenovoTabletBitMap();
                break;
            case SAMSUNG:
                initSamsungTabletBitMap();
                break;
            case AMAZON:
                initAmazonTabletBitMap();
                break;
        }
    }
    private void televisionCompany(String company){
        switch (company){
            case SAMSUNG:
                initSamsungTVBitMap();
                break;
            case LG:
                initLGTVBitMap();
                break;
            case TOSHIBA:
                initToshibaTVBitMap();
                break;
            case TCL:
                initTCLTVBitMap();
                break;
            case SONY:
                initSonyTVBitMap();
                break;
            case VIZIO:
                initVizioTVBitMap();
                break;
        }
    }
    private void watchCompany(String company){
        switch (company){
            case APPLE:
                initAppleWatchBitMap();
                break;
            case SAMSUNG:
                initSamsungWatchBitMap();
                break;
            case FITBIT:
                initFitbitBitMap();
                break;
        }
    }

    //Laptop Bitmaps
    private void initAppleLaptopBitMaps(){

        Log.d(TAG, "Apple Laptop BitMaps");

        mImageUrls.add(APPLEURL);
        mNames.add(IMAC);

        mImageUrls.add(APPLEURL);
        mNames.add(MACPRO);

        mImageUrls.add(APPLEURL);
        mNames.add(MACBOOKPRO13);

        mImageUrls.add(APPLEURL);
        mNames.add(MACBOOKPRO15);

        mImageUrls.add(APPLEURL);
        mNames.add(MACBOOKAIR);

        initRecyclerView();
    }
    private void initDellLaptopBitMap(){
        Log.d(TAG, "Dell Laptop Bitmaps");

        mImageUrls.add(DELLURL);
        mNames.add(XPS);

        mImageUrls.add(DELLURL);
        mNames.add(INSPIRON);

        mImageUrls.add(DELLURL);
        mNames.add(LATITUDE);

        initRecyclerView();
    }
    private void initAsusLaptopBitMap(){
        Log.d(TAG, "Asus Laptop Bitmaps");

        mImageUrls.add(ASUSURL);
        mNames.add(ZENBOOK);

        mImageUrls.add(ASUSURL);
        mNames.add(ROG);

        mImageUrls.add(ASUSURL);
        mNames.add(VIVOPRO);

        mImageUrls.add(ASUSURL);
        mNames.add(QSERIES);

        initRecyclerView();
    }
    private void initHPLaptopBitMap(){
        Log.d(TAG, "HP Laptop Init");

        mImageUrls.add(HPURL);
        mNames.add(ELITEBOOK);

        mImageUrls.add(HPURL);
        mNames.add(ENVY);

        mImageUrls.add(HPURL);
        mNames.add(SPECTRE);

        mImageUrls.add(HPURL);
        mNames.add(PAVILION);

        initRecyclerView();
    }

    //Desktop Bitmaps
    private void initAppleDesktopBitMap(){
        Log.d(TAG, "Apple Desktop Bitmaps");

        mImageUrls.add(APPLEURL);
        mNames.add(IMAC);

        initRecyclerView();
    }
    private void initDellDesktopBitMap(){
        Log.d(TAG, "Dell Desktop Bitmap");

        mImageUrls.add(DELLURL);
        mNames.add(XPS8930);

        mImageUrls.add(DELLURL);
        mNames.add(INSPIRON5675);

        mImageUrls.add(DELLURL);
        mNames.add(ALIENWARER7);

        initRecyclerView();
    }
    private void initAsusDesktopBitMap(){
        Log.d(TAG, "Asus Desktop Bitmap");

        mImageUrls.add(ASUSURL);
        mNames.add(ASUSG11);

        mImageUrls.add(ASUSURL);
        mNames.add(ASUSG20);

        mImageUrls.add(ASUSURL);
        mNames.add(ASUSGT51);

        mImageUrls.add(ASUSURL);
        mNames.add(ASUSVIVIO);

        initRecyclerView();
    }
    private void initHPDesktopBitMap(){
        Log.d(TAG, "HP Desktop Init");

        mImageUrls.add(HPURL);
        mNames.add(DESKTOPPAVILION);

        mImageUrls.add(HPURL);
        mNames.add(DESKTOPSLIMLIME);

        mImageUrls.add(HPURL);
        mNames.add(DESKTOPENVY);

        mImageUrls.add(HPURL);
        mNames.add(DESKTOPCOMPAQ);

        mImageUrls.add(HPURL);
        mNames.add(DESKTOPELITEDESK);

        initRecyclerView();
    }

    //Phone Bitmaps
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
    private void initSamsungPhoneBitMaps(){
        Log.d(TAG, "Samsung Phone Bitmap");

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGS6);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGS7);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGS7EDGE);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGS8);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGS8PLUS);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGS9PLUS);

        initRecyclerView();
    }
    private void initGooglePhoneBitMaps(){
        Log.d(TAG, "Google Phone Bitmap");

        mImageUrls.add(GOOGLEURL);
        mNames.add(PIXEL);

        mImageUrls.add(GOOGLEURL);
        mNames.add(PIXELXL);

        mImageUrls.add(GOOGLEURL);
        mNames.add(PIXEL2);

        mImageUrls.add(GOOGLEURL);
        mNames.add(PIXEL2XL);

        mImageUrls.add(GOOGLEURL);
        mNames.add(PIXEL3);

        mImageUrls.add(GOOGLEURL);
        mNames.add(PIXEL3XL);

        initRecyclerView();
    }
    private void initMicrosoftPhoneBitMaps(){
        Log.d(TAG, "");

        mImageUrls.add(MICROSOFTURL);
        mNames.add(LUMIA550);

        mImageUrls.add(MICROSOFTURL);
        mNames.add(LUMIA650);

        mImageUrls.add(MICROSOFTURL);
        mNames.add(LUMIA950);

        initRecyclerView();
    }

    //Tablet Bitmaps
    private void initAppleTabletBitMap(){
        Log.d(TAG, "Apple Tablet Bitmap");

        mImageUrls.add(APPLEURL);
        mNames.add(IPAD10);

        mImageUrls.add(APPLEURL);
        mNames.add(IPAD12);

        mImageUrls.add(APPLEURL);
        mNames.add(IPADMINI);

        initRecyclerView();
    }
    private void initMicrosoftTabletBitMap(){
        Log.d(TAG, "Microsoft Tablet Bitmap");

        mImageUrls.add(MICROSOFTURL);
        mNames.add(MICROSOFTPRO3);

        mImageUrls.add(MICROSOFTURL);
        mNames.add(MICROSOFTPRO4);

        mImageUrls.add(MICROSOFTURL);
        mNames.add(MICROSOFTGO);

        initRecyclerView();
    }
    private void initSamsungTabletBitMap(){
        Log.d(TAG, "Samsung Tablet Bitmap");

        mImageUrls.add(SAMSUNGURL);
        mNames.add(GALAXYTAB10);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(GALAXYBOOK2);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(GALAXYTABA10);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(GALAXYTABA8);

        initRecyclerView();
    }
    private void initAmazonTabletBitMap(){
        Log.d(TAG, "Amazon Tablet Bitmap");

        mImageUrls.add(AMAZONURL);
        mNames.add(AMAZONFIRE10);

        mImageUrls.add(AMAZONURL);
        mNames.add(AMAZONFIRE7);

        mImageUrls.add(AMAZONURL);
        mNames.add(AMAZONFIRE);

        mImageUrls.add(AMAZONURL);
        mNames.add(AMAZONKINDLE);

        initRecyclerView();
    }
    private void initLenovoTabletBitMap(){
        Log.d(TAG, "Lenovo Tablet Bitmap");

        mImageUrls.add(LENOVOURL);
        mNames.add(LENOVOTAB8);

        mImageUrls.add(LENOVOURL);
        mNames.add(LENOVOTAB10);

        mImageUrls.add(LENOVOURL);
        mNames.add(LENOVOYOGA);

        initRecyclerView();
    }

    //TV Bitmaps
    private void initSamsungTVBitMap(){
        Log.d(TAG, "Samsung TV Bitmap");

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGQ7F55);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGQ7F75);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGQ6F55);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGQ6F75);

        initRecyclerView();
    }
    private void initLGTVBitMap(){
        Log.d(TAG, "LG TV Bitmap");

        mImageUrls.add(LGURL);
        mNames.add(LGC7P);

        mImageUrls.add(LGURL);
        mNames.add(LGE7P);

        mImageUrls.add(LGURL);
        mNames.add(LGC8);

        initRecyclerView();
    }
    private void initToshibaTVBitMap(){
        Log.d(TAG, "Toshiba TV Bitmap");

        mImageUrls.add(TOSHIBAURL);
        mNames.add(TOSHIBAL9300);

        mImageUrls.add(TOSHIBAURL);
        mNames.add(TOSHIBAL7350);

        mImageUrls.add(TOSHIBAURL);
        mNames.add(TOSHIBAL7300);

        initRecyclerView();
    }
    private void initTCLTVBitMap(){
        Log.d(TAG, "TCL TV Bitmap");

        mImageUrls.add(TCLURL);
        mNames.add(TCL6SERIES);

        mImageUrls.add(TCLURL);
        mNames.add(TCLR615);

        mImageUrls.add(TCLURL);
        mNames.add(TCLS405);

        initRecyclerView();
    }
    private void initSonyTVBitMap(){
        Log.d(TAG, "Sony TV Bitmap");

        mImageUrls.add(SONYURL);
        mNames.add(SONYX900F);

        mImageUrls.add(SONYURL);
        mNames.add(SONYKDA1);

        mImageUrls.add(SONYURL);
        mNames.add(SONYX900E);

        initRecyclerView();
    }
    private void initVizioTVBitMap(){
        Log.d(TAG, "Vizio TV Bitmap");

        mImageUrls.add(VIZIOURL);
        mNames.add(VIZIODE0);

        mImageUrls.add(VIZIOURL);
        mNames.add(VIZIOMC1);

        initRecyclerView();
    }

    //Smart Watch Bitmap
    private void initFitbitBitMap(){
        Log.d(TAG, "Fitbit Bitmap");

        mImageUrls.add(FITBITURL);
        mNames.add(FITBITCHARGE2);

        mImageUrls.add(FITBITURL);
        mNames.add(FITBITCHARGE3);

        mImageUrls.add(FITBITURL);
        mNames.add(FITBITVERSA);

        initRecyclerView();
    }
    private void initSamsungWatchBitMap(){
        Log.d(TAG, "Samsung Smart Watch Bitmap");

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGGEARS2);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGGEARS3);

        mImageUrls.add(SAMSUNGURL);
        mNames.add(SAMSUNGGEARS);

        initRecyclerView();
    }
    private void initAppleWatchBitMap(){
        Log.d(TAG, "Apple Smart Watch Bitmap");

        mImageUrls.add(APPLEURL);
        mNames.add(APPLEWATCH);

        mImageUrls.add(APPLEURL);
        mNames.add(APPLEWATCH2);

        mImageUrls.add(APPLEURL);
        mNames.add(APPLEWATCH3);

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
