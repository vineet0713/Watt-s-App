package com.example.vjoshi.wattsapp;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User {
    // username should be the key to a User object in Firebase
    public String username, password;
    public long totalPoints;

    public ArrayList<Device> devices;
    public ArrayList<UsageEntry> usageEntries;
    public ArrayList<RedeemableItem> itemsRedeemed;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String password, long totalPoints) {
        this.username = username;
        this.password = password;
        this.totalPoints = totalPoints;
    }

    @Exclude
    public void addDevice(Device d) {
        if (devices == null) {
            devices = new ArrayList<>();
        }
        if(devices.contains(d)){
            return;
        }
        devices.add(d);
    }

    @Exclude
    public void removeDevice(Device d) {
        devices.remove(d);
    }

    @Exclude
    public ArrayList<Device> getDevices() {
        return devices;
    }

    @Exclude
    public void addUsageEntry(UsageEntry ue) {
        if (usageEntries == null) {
            usageEntries = new ArrayList<>();
        }
        usageEntries.add(ue);
    }

    @Exclude
    public ArrayList<UsageEntry> getUsageEntries() {
        return usageEntries;
    }

    @Exclude
    public void addRedeemableItem(RedeemableItem ri) {
        if (itemsRedeemed == null) {
            itemsRedeemed = new ArrayList<>();
        }
        itemsRedeemed.add(ri);
    }

    @Exclude
    public ArrayList<RedeemableItem> getItemsRedeemed() {
        return itemsRedeemed;
    }
}
