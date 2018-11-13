package com.example.vjoshi.wattsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySQLiteHelper myDB = new MySQLiteHelper(this);

        Intent intent;
        String username = myDB.getUsername();
        if (username != null) {
            Backend.getInstance().setUsername(username);
            intent = new Intent(this, HomeActivity.class);
        } else {
            intent = new Intent(this, SignInActivity.class);
        }

        myDB.close();

        startActivity(intent);
        finish();
    }
}
