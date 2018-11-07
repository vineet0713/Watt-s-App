package com.example.vjoshi.wattsapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class TestBackendActivity extends AppCompatActivity {
    private static final String TAG = "TestBackendActivity";

    // we will have to store the User's username on the device so we can access this user's User object!
    // https://stackoverflow.com/questions/3584267/android-equivalent-of-nsuserdefaults-in-ios
    // private static final String USERNAME = ;

    // this is for testing purposes:
    private final int OPERATION = 1;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_backend);

        // this is the root of the database
        database = FirebaseDatabase.getInstance().getReference();

        switch (OPERATION) {
            // CASE 1 AND CASE 2 ARE IN HomeActivity.java FOR TESTING PURPOSES
            case 1:
                // add 3 sample users
                Backend.getInstance().addSampleUsers(3);
                break;
            case 2:
                // add a new device to user1
                Device deviceToAdd = new Device(10, "Desktop", "Apple", "Mac Mini", 0.09);
                Backend.getInstance().addDevice(deviceToAdd);
                break;
            case 3:
                // test whether trying to add an existing username works or not
                final String usernameToAdd = "usernameToAdd";
                database.child(usernameToAdd).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            userExists(usernameToAdd, this);
                        } else {
                            userDoesntExist(usernameToAdd, this);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) { }
                });
                break;
        }
    }

    private void userExists(String usernameToAdd, ValueEventListener valueEventListener) {
        database.child(usernameToAdd).removeEventListener(valueEventListener);
        Log.i(TAG, "The user '" + usernameToAdd + "' already exists!");
    }

    private void userDoesntExist(String usernameToAdd, ValueEventListener valueEventListener) {
        database.child(usernameToAdd).removeEventListener(valueEventListener);
        // (the function below doesn't exist anymore)
        // saveUser(usernameToAdd, new User(usernameToAdd, "password sample", 10), null);

        // adds a sample device to a newly created User

        // (the function below doesn't exist anymore)
        // addDevice(usernameToAdd, new Device(5, "Tablet", "Apple", "iPad Pro", 0.07));
    }
}
