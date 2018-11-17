package com.example.vjoshi.wattsapp;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User {
    // username should be the key to a User object in Firebase
    public String username;
    public long totalPoints;
    public double totalWatts;

    public ArrayList<Device> devices;
    public ArrayList<UsageEntry> usageEntries;
    public ArrayList<RedeemableItem> itemsRedeemed;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, long totalPoints) {
        this.username = username;
        this.totalPoints = totalPoints;
        totalWatts = 0.0;
    }

    @Exclude
    public void addDevice(Device d) {
        if (devices == null) {
            devices = new ArrayList<>();
        }
        devices.add(d);
    }

    @Exclude
    public void removeDevice(int index) {
        devices.remove(index);
    }

    @Exclude
    public void setDevice(int indexToSet, Device updatedDevice) { devices.set(indexToSet, updatedDevice); }

    @Exclude
    public ArrayList<Device> getDevices() {
        if (devices == null) {
            devices = new ArrayList<>();
        }
        return devices;
    }

    @Exclude
    public void addUsageEntry(UsageEntry ue) {
        if (usageEntries == null) {
            usageEntries = new ArrayList<>();
        }
        if (usageEntries == null || usageEntries.size() == 0) {
            totalWatts = 0.0;
        }
        usageEntries.add(ue);
        totalWatts += ue.wattsUsed;
    }

    @Exclude
    public ArrayList<UsageEntry> getUsageEntries() {
        if (usageEntries == null) {
            usageEntries = new ArrayList<>();
        }
        return usageEntries;
    }

    @Exclude
    public double getTotalWatts() { return totalWatts; }

    @Exclude
    public void addRedeemableItem(RedeemableItem ri) {
        if (itemsRedeemed == null) {
            itemsRedeemed = new ArrayList<>();
        }
        itemsRedeemed.add(ri);
    }

    @Exclude
    public ArrayList<RedeemableItem> getItemsRedeemed() {
        if (itemsRedeemed == null) {
            itemsRedeemed = new ArrayList<>();
        }
        return itemsRedeemed;
    }
}
