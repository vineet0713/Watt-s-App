package com.example.vjoshi.wattsapp.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.vjoshi.wattsapp.MySQLiteHelper;
import com.example.vjoshi.wattsapp.R;
import com.example.vjoshi.wattsapp.SignInActivity;

public class ProfileActivity extends AppCompatActivity implements BottomProfileFragment.TopSectionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


    }

    private void logout() {
        Intent logoutIntent = new Intent(this, SignInActivity.class);
        MySQLiteHelper myDB = new MySQLiteHelper(this);
        myDB.clearUsername();
        myDB.close();

        startActivity(logoutIntent);
    }

    @Override
    public void setViews(String setting) {
        TopProfileFragment topProfileFragment = (TopProfileFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTop);
        TopProfileFragment.setFlipperButton(setting);
        TopProfileFragment.setFlipperButton2(setting);

    }
}
