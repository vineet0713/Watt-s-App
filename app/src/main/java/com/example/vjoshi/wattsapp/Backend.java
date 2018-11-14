package com.example.vjoshi.wattsapp;

import android.support.annotation.NonNull;
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

public class Backend {
    private static final String TAG = "Backend";

    private DatabaseReference database;

    private String username;

    private static final Backend INSTANCE = new Backend();
    public static Backend getInstance() {
        return INSTANCE;
    }

    private Backend() {
        // this is the root of the database
        database = FirebaseDatabase.getInstance().getReference();
    }



    /*
        START OF BACKEND API
    */

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addUser(String username, String password) {
        this.username = username;
        User u = new User(username, password, 0);
        database.child(username).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                onCompleteMethod(task);
            }
        });
    }

    /*
        END OF BACKEND API
    */



    /*
        START OF TESTER FUNCTIONS
    */

    public void addSampleUsers(int number) {
        for (int i = 1; i <= number; i++) {
            String username = "user" + i;
            User u = new User(username, ("password" + i), 0);

            for (int k = 1; k <= i; k++) {
                Device d = new Device(i+k, "Laptop", "Apple", "MacBook Pro", 0.04);
                u.addDevice(d);
                u.addUsageEntry(new UsageEntry(d, new Date(), 100, 400));
                u.addRedeemableItem(new RedeemableItem("test item", "test description", 9.98));
            }

            database.child(username).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) { onCompleteMethod(task); }
            });
        }
    }

    /*
        END OF TESTER FUNCTIONS
    */



    /*
        START OF HELPER FUNCTIONS
    */

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

    /*
        END OF HELPER FUNCTIONS
    */
}
