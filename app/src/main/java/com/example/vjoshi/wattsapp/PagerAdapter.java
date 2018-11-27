package com.example.vjoshi.wattsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                DeviceUsage tab1 = new DeviceUsage();
                return tab1;
            case 1:
                DeviceTypeUsage tab2 = new DeviceTypeUsage();
                return  tab2;
            case 2:
                UsageHistory tab3 = new UsageHistory();
                return  tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}