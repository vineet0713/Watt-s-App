package com.example.vjoshi.wattsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.vjoshi.wattsapp.addDeviceClasses.activities.DeviceSelectionActivity;
import com.example.vjoshi.wattsapp.profile.ProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

// For Redeem class
// https://www.youtube.com/watch?v=hl0AcuplFwE

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private DatabaseReference database;

    private ArrayList<Button> deviceButtons = new ArrayList<>();

    private static RelativeLayout rootLayout = null;
    private static GridLayout gridLayout = null;
    private static Context context = null;

    private static String device, company, model, longPressButton;

    private User user;

    ProgressBar loadingBar;

    public static String getDevice() {
        return device;
    }

    public static void setDevice(String device) {
        HomeActivity.device = device;
    }

    public static String getCompany() {
        return company;
    }

    public static void setCompany(String company) {
        HomeActivity.company = company;
    }

    public static String getModel() {
        return model;
    }

    public static void setModel(String model) {
        HomeActivity.model = model;
    }

    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Watt's App");

        // this is the root of the database
        database = FirebaseDatabase.getInstance().getReference();

        final Intent profileIntent = new Intent(this, ProfileActivityTwo.class);
        final Intent redeemIntent = new Intent(this, TestRedeemActivity.class);
        final Intent deviceIntent = new Intent(this, DeviceSelectionActivity.class);

        final ImageButton profileButton = findViewById(R.id.profileButton);
        final ImageButton redeemButtom = findViewById(R.id.redeemButton);
        final ImageButton leaderboardButton = findViewById(R.id.leaderboardButton);
        final Button addDeviceButton = findViewById(R.id.itemButton);

        rootLayout = findViewById(R.id.rootLayout);
        gridLayout = findViewById(R.id.gridLayout);
        context = this;

        loadingBar = findViewById(R.id.progressBar);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });

        redeemButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(redeemIntent);
            }
        });

        addDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(deviceIntent);
            }
        });

        loadDevices();

        rootLayout.setOnTouchListener(new OnSwipeTouchListener(HomeActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(HomeActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(HomeActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(HomeActivity.this, "left", Toast.LENGTH_SHORT).show();
                startActivity(profileIntent);
                //finish();
            }
            public void onSwipeBottom() {
                Toast.makeText(HomeActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void loadDevices() {
        loadingBar.setVisibility(ProgressBar.VISIBLE);

        String username = Backend.getInstance().getUsername();
        database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                addDeviceButtons(user.getDevices());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void addDeviceButtons(ArrayList<Device> devices) {
        for(int i =0; i < devices.size(); i++){
            final int position = i;
            Device d = devices.get(i);
            String status = d.getStatus();
            final ToggleButton newButton = new ToggleButton(context);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(20, 20, 20, 20);
            params.width = dpToPx(100);
            params.height = dpToPx(60);
            newButton.setLayoutParams(params);
            newButton.setText(d.getModel());

            if(status.equalsIgnoreCase("ON")){
                newButton.setTextOn(d.getModel() + " Active");
                newButton.setChecked(true);
                Log.d(TAG, "Device " + d.getModel() + " is Active");
            }else if(status.equalsIgnoreCase("OFF")){
                newButton.setTextOff(d.getModel() + " Inactive");
                newButton.setChecked(false);
                Log.d(TAG, "Device " + d.getModel() + " is Inactive");
            }
            newButton.setTextOff(d.getModel() + " Inactive");
            newButton.setTextOn(d.getModel() + " Active");
            newButton.setBackgroundResource(R.drawable.button_background);
            gridLayout.addView(newButton);
            deviceButtons.add(newButton);


            newButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longPressButton = newButton.getText().toString() + " " +position;
                    confirmDelete(position);
                    return false;
                }
            });

            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateUsageEntry(position);
                }
            });
        }

        loadingBar.setVisibility(ProgressBar.INVISIBLE);
    }

    private void confirmDelete(final int indexToDelete) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this device?");
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                final String username = Backend.getInstance().getUsername();
                database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        user.removeDevice(indexToDelete);
                        final User modifiedUser = user;
                        database.child(username).setValue(modifiedUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // delete the actual button:
                                gridLayout.removeView(deviceButtons.get(indexToDelete));
                            }
                        });
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) { }
                });
            }
        });

        builder.show();
    }

    private void updateUsageEntry(final int indexToUpdate) {
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final String username = Backend.getInstance().getUsername();
        database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Device device = user.getDevices().get(indexToUpdate);
                String status = device.getStatus();
                if (status.equals("OFF")) {
                    // turn the device on, so set the start time (epoch time in milliseconds)
                    device.setStartTime(System.currentTimeMillis());
                } else if (status.equals("ON")) {
                    // turn the device off, so save the UsageEntry
                    long timePeriod = (System.currentTimeMillis() - device.getStartTime()) / 1000;
                    double wattsUsed = device.getUsageRate() * timePeriod;
                    Date usageDate = new Date(device.getStartTime());
                    UsageEntry entry = new UsageEntry(device.getType(), device.getDeviceName(), usageDate, wattsUsed);
                    user.addUsageEntry(entry);
                }
                final String newStatus = (status.equals("OFF")) ? "ON" : "OFF";
                device.setStatus(newStatus);

                user.setDevice(indexToUpdate, device);

                final User modifiedUser = user;
                database.child(username).setValue(modifiedUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // UPDATE THE UI TO SHOW EITHER DEVICE IS ON OR OFF
//                        if (newStatus.equals("OFF")) {
//
//                        } else if (newStatus.equals("ON")) {
//
//                        }
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

}
