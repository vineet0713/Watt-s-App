package com.example.vjoshi.wattsapp.addDeviceClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.vjoshi.wattsapp.R;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

public class CompanySelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_selection);

        final Intent modelIntent = new Intent(this, ModelSelectionActivity.class);
        final Intent passedCompanyIntent = getIntent();
        final Bundle companyBundle = passedCompanyIntent.getExtras();

        int index = companyBundle.getInt(INDEX);
        String device = companyBundle.getString(DEVICENAME);


        Log.i(TAG, "Device " + device + " at index: " + index);

        String[] laptopCompanies = {"Apple", "Dell", "Windows", "Asus", "HP", "Google"};
        String[] desktopCompanies = {"Apple", "Dell", "Windows", "Asus", "HP"};
        String[] phoneCompanies = {"Apple", "Windows", "Nokia", "Huawei", "Samsung", "Google"};
        String[] tabletCompanies = {"Apple", "Windows", "Samsung", "Amazon", "Lenovo"};
        String[] tvCompanies = {"Samsung", "LG", "Toshiba", "TCL", "Sony", "Vizio"};
        String[] smartWatchCompanies = {"Fitbit", "Samsung", "Apple"};


        final ListView companyList = findViewById(R.id.companyList);
        ListAdapter listAdapter = null;


        switch (index){
            case 0:
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, laptopCompanies);
                break;
            case 1:
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, desktopCompanies);
                break;
            case 2:
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phoneCompanies);
                break;
            case 3:
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tabletCompanies);
                break;
            case 4:
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tvCompanies);
                break;
            case 5:
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, smartWatchCompanies);
                break;
            default:
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, laptopCompanies);
                break;

        }
        companyList.setAdapter(listAdapter);

        companyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCompny = String.valueOf(parent.getItemAtPosition(position));
                companyBundle.remove(INDEX);
                companyBundle.putInt(INDEX, position);
                companyBundle.putString(COMPANYNAME, selectedCompny);
                modelIntent.putExtras(companyBundle);
                startActivity(modelIntent);
            }
        });
    }
}
