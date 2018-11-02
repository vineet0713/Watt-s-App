package com.example.vjoshi.wattsapp;

import java.util.Date;

public class UsageEntry {
    public Device deviceUsed;
    public Date dateUsed;
    public long minutesUsed;
    public long wattsUsed;

    public UsageEntry() {
        // Default constructor required for calls to DataSnapshot.getValue(UsageEntry.class)
    }

    public UsageEntry(Device deviceUsed, Date dateUsed, long minutesUsed, long wattsUsed) {
        this.deviceUsed = deviceUsed;
        this.dateUsed = dateUsed;
        this.minutesUsed = minutesUsed;
        this.wattsUsed = wattsUsed;
    }

    // USEFUL DATE CONSTANTS (since Date class just stores epoch time in MILLISECONDS - use long not int!):
    // # OF SECONDS IN A DAY: 86400
    // # OF SECONDS IN A WEEK: 604800
    // # OF SECONDS IN A MONTH: 2628000
}
