package com.example.vjoshi.wattsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySQLiteHelper myDB = new MySQLiteHelper(this);

        Intent intent;
        if (myDB.usernameExists()) {
            intent = new Intent(this, HomeActivity.class);
        } else {
            intent = new Intent(this, SignInActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
