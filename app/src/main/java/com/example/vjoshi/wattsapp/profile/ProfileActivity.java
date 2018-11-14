package com.example.vjoshi.wattsapp.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.vjoshi.wattsapp.Backend;
import com.example.vjoshi.wattsapp.MySQLiteHelper;
import com.example.vjoshi.wattsapp.R;
import com.example.vjoshi.wattsapp.SignInActivity;
import com.example.vjoshi.wattsapp.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements BottomProfileFragment.TopSectionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Confirm Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                });

                builder.show();
            }
        });


    }

    private void logout() {
        final Intent logoutIntent = new Intent(this, SignInActivity.class);

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
