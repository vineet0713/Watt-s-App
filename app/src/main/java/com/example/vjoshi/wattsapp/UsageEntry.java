package com.example.vjoshi.wattsapp;

import com.google.firebase.database.Exclude;

import java.util.Date;

public class UsageEntry {
    public String deviceType, deviceName;
    public Date usageDate;
    public double wattsUsed;

    public UsageEntry() {
        // Default constructor required for calls to DataSnapshot.getValue(UsageEntry.class)
    }

    public UsageEntry(String deviceType, String deviceName, Date usageDate, double wattsUsed) {
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.usageDate = usageDate;
        this.wattsUsed = wattsUsed;
    }

    @Exclude
    public String getDeviceType() { return deviceType; }

    @Exclude
    public String getDeviceName() { return deviceName; }

    @Exclude
    public Date getUsageDate() { return usageDate; }

    @Exclude
    public double getWattsUsed() { return wattsUsed; }

    // USEFUL DATE CONSTANTS (since Date class just stores epoch time in MILLISECONDS - use long not int!):
    // # OF SECONDS IN A DAY: 86400
    // # OF SECONDS IN A WEEK: 604800
    // # OF SECONDS IN A MONTH: 2628000
}
