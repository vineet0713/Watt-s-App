package com.example.vjoshi.wattsapp;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Backend {
    private static final String TAG = "Backend";

    private String username;

    private HashMap<String, Integer> deviceToIdMap;
    private HashMap<String, Double> deviceToUsageMap;

    private static final Backend INSTANCE = new Backend();
    public static Backend getInstance() {
        return INSTANCE;
    }

    private Backend() {
        deviceToIdMap = new HashMap<>();
        setDeviceToIdMap();

        deviceToUsageMap = new HashMap<>();
        setDeviceToUsageMap();
    }



    /*
        START OF BACKEND API
    */

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdForDevice(String deviceName) {
        return deviceToIdMap.get(deviceName).intValue();
    }

    public double getUsageForDevice(String deviceName) {
        return deviceToUsageMap.get(deviceName).doubleValue();
    }

    /*
        END OF BACKEND API
    */



    /*
        START OF TESTER FUNCTIONS
    */

//    public void addSampleUsers(int number) {
//        for (int i = 1; i <= number; i++) {
//            String username = "user" + i;
//            User u = new User(username, ("password" + i), 0);
//
//            for (int k = 1; k <= i; k++) {
//                Device d = new Device(i+k, "Laptop", "Apple", "MacBook Pro", 0.04);
//                u.addDevice(d);
//                u.addUsageEntry(new UsageEntry(d, new Date(), 100, 400));
//                u.addRedeemableItem(new RedeemableItem("test item", "test description", 9.98));
//            }
//
//            database.child(username).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) { onCompleteMethod(task); }
//            });
//        }
//    }

    /*
        END OF TESTER FUNCTIONS
    */



    /*
        START OF HELPER FUNCTIONS
    */

    private void setDeviceToIdMap() {
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONE6, new Integer(1));
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONE6S, new Integer(2));
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONE8, new Integer(3));
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONE8S, new Integer(4));
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONEX, new Integer(5));
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONEXR, new Integer(6));
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONEXS, new Integer(7));

        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IMAC, new Integer(8));
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.MACPRO, new Integer(9));
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.MACBOOKPRO13, new Integer(10));
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.MACBOOKPRO15, new Integer(11));
        deviceToIdMap.put(DeviceConstants.APPLE + " " + DeviceConstants.MACBOOKAIR, new Integer(12));
    }

    private void setDeviceToUsageMap() {
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONE6, new Double(3));
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONE6S, new Double(3.5));
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONE8, new Double(5));
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONE8S, new Double(5.5));
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONEX, new Double(8));
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONEXR, new Double(9));
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IPHONEXS, new Double(8));

        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.IMAC, new Double(30));
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.MACPRO, new Double(40));
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.MACBOOKPRO13, new Double(20));
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.MACBOOKPRO15, new Double(25));
        deviceToUsageMap.put(DeviceConstants.APPLE + " " + DeviceConstants.MACBOOKAIR, new Double(15));
    }

    /*
        END OF HELPER FUNCTIONS
    */
}
