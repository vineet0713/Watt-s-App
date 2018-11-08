package com.example.vjoshi.wattsapp.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vjoshi.wattsapp.R;

public class ProfileActivity extends AppCompatActivity implements BottomProfileFragment.TopSectionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    public void setViews(String setting) {
        TopProfileFragment topProfileFragment = (TopProfileFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTop);
        TopProfileFragment.setFlipperButton(setting);
        TopProfileFragment.setFlipperButton2(setting);

    }
}