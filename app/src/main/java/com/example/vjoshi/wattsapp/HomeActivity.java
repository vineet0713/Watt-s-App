package com.example.vjoshi.wattsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants;
import com.example.vjoshi.wattsapp.addDeviceClasses.activities.DeviceSelectionActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

// For Redeem class
// https://www.youtube.com/watch?v=hl0AcuplFwE

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private DatabaseReference database;

    private ValueEventListener topRankHolderChanged;
    private boolean firstChange;

    private ArrayList<Button> deviceButtons = new ArrayList<>();

    private static RelativeLayout rootLayout = null;
    private static GridLayout gridLayout = null;
    private static Context context = null;

    private static String device, company, model, longPressButton;

    private User user;

    private ProgressBar loadingBar;

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

        topRankHolderChanged = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (firstChange == false) {
                    String newTopRankHolder = (String) dataSnapshot.getValue();
                    String username = Backend.getInstance().getUsername();
                    String message = "The new top rankholder is '" + newTopRankHolder + "'! You better up your game, " + username + "!";
                    displayAlert("New Top Rankholder!", message);
                }
                firstChange = false;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };

        final Intent profileIntent = new Intent(this, ProfileActivityTwo.class);
        final Intent redeemIntent = new Intent(this, RedeemActivity.class);
        final Intent deviceIntent = new Intent(this, DeviceSelectionActivity.class);
        final Intent leaderIntent = new Intent(this, Leaderboard.class);

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

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(leaderIntent);
            }
        });

        loadDevices();
    }

    @Override
    protected void onResume() {
        super.onResume();

        firstChange = true;
        database.child("topRankHolder").addValueEventListener(topRankHolderChanged);
    }

    @Override
    protected void onPause() {
        super.onPause();

        database.child("topRankHolder").removeEventListener(topRankHolderChanged);
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
            params.height = dpToPx(70);
            params.setGravity(Gravity.CENTER);
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

                long epochTimeMillis = System.currentTimeMillis();
//                long epochTimeMillis = System.currentTimeMillis() + 86400000;
                if (status.equals("OFF")) {
                    // turn the device on, so set the start time (epoch time in milliseconds)
                    device.setStartTime(epochTimeMillis);
                } else if (status.equals("ON")) {
                    ArrayList<UsageEntry> entries = user.getUsageEntries();
                    if (entries.size() > 0) {
                        Calendar calendar = Calendar.getInstance();

                        // sets the calendar to the current time
                        calendar.setTime(new Date(epochTimeMillis));
                        int todayIndex = calendar.get(Calendar.DAY_OF_WEEK);

                        // sets the calendar to the time of the last added UsageEntry
                        calendar.setTime(entries.get(entries.size() - 1).getUsageDate());
                        int lastAddedEntryIndex = calendar.get(Calendar.DAY_OF_WEEK);

                        if (lastAddedEntryIndex != todayIndex) {
                            // it is a new day, so we have to:
                            // 1. add previous day's points to the total points
                            // 2. reset the daily points and daily usage
                            user.addTotalPoints(user.getDailyPoints());
                            user.resetDailyPoints();
                            user.resetDailyWatts();
                        }
                    }

                    // turn the device off, so save the UsageEntry
                    long timePeriod = (epochTimeMillis - device.getStartTime()) / 1000;
                    double wattsUsed = device.getUsageRate() * timePeriod;
                    Date usageDate = new Date(device.getStartTime());
                    UsageEntry entry = new UsageEntry(device.getType(), device.getDeviceName(), usageDate, wattsUsed);
                    user.addUsageEntry(entry);

                    // add usage and points to the daily totals
                    user.addDailyWatts(wattsUsed);

                    double threshold = DeviceConstants.USAGE_THRESHOLD;
                    double step = DeviceConstants.USAGE_STEP;
                    double dailyWatts = user.getDailyWatts();

                    // calculates the new daily points value based on daily usage
                    long newPoints = 0;
                    if (dailyWatts < threshold) {
                        newPoints = (long)((threshold - dailyWatts) / step);
                        if (dailyWatts % step != 0) {
                            newPoints++;
                        }
                    }
//                    if (dailyWatts > 0 && dailyWatts < step) {
//                        newPoints = (long)((threshold - step) / step);
//                    } else if (dailyWatts >= step && dailyWatts < threshold) {
//
//                    }
                    user.setDailyPoints(newPoints);
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

    private void displayAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", null);
        builder.show();
    }

}
