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
    private final int OPERATION = 3;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_backend);

        // this is the root of the database
        database = FirebaseDatabase.getInstance().getReference();

        switch (OPERATION) {
            case 1:
                // add 3 sample users
                addSampleUsers(3);
                break;
            case 2:
                // add a new device to user1
                Device deviceToAdd = new Device(10, "Desktop", "Apple", "Mac Mini", 0.09);
                addDevice("user1", deviceToAdd);
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

    private void addSampleUsers(int number) {
        for (int i = 1; i <= number; i++) {
            String username = "user" + i;
            User u = new User(username, ("password" + i), 0);

            for (int k = 1; k <= i; k++) {
                Device d = new Device(i+k, "Laptop", "Apple", "MacBook Pro", 0.04);
                u.addDevice(d);
                u.addUsageEntry(new UsageEntry(d, new Date(), 100, 400));
                u.addRedeemableItem(new RedeemableItem("test item", "test description", 9.98));
            }

            saveUser(username, u, null);
        }
    }

    private void addDevice(final String username, final Device deviceToAdd) {
        database.child(username).addValueEventListener(new ValueEventListener() {
            // 'onDataChange' will be called immediately after this ValueEventListener is added
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, "onDataChange called!");

                User user = dataSnapshot.getValue(User.class);
                user.addDevice(deviceToAdd);
                final User modifiedUser = user;
                saveUser(username, modifiedUser, this);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void saveUser(String username, User user, ValueEventListener valueEventListener) {
        // removes the ValueEventListener if not null (which is from where this method was called!)
        if (valueEventListener != null) {
            database.child(username).removeEventListener(valueEventListener);
        }
        database.child(username).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) { onCompleteMethod(task); }
        });
    }

    private void userExists(String usernameToAdd, ValueEventListener valueEventListener) {
        database.child(usernameToAdd).removeEventListener(valueEventListener);
        Log.i(TAG, "The user '" + usernameToAdd + "' already exists!");
    }

    private void userDoesntExist(String usernameToAdd, ValueEventListener valueEventListener) {
        database.child(usernameToAdd).removeEventListener(valueEventListener);
        saveUser(usernameToAdd, new User(usernameToAdd, "password sample", 10), null);
        // adds a sample device to a newly created User
        addDevice(usernameToAdd, new Device(5, "Tablet", "Apple", "iPad Pro", 0.07));
    }

    // HELPER METHOD
    private void onCompleteMethod(@NonNull Task<Void> task) {
        if (task.isComplete()) {
            Log.d(TAG, "Task completed!");
            if (task.isSuccessful()) {
                Log.d(TAG, "Task completed successfully!");
            } else {
                Log.d(TAG, "Task completed with error.");
            }
        } else {
            Log.d(TAG, "Task did not complete.");
        }
    }
}
