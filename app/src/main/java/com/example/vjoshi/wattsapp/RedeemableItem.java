package com.example.vjoshi.wattsapp;

import com.google.firebase.database.Exclude;

public class RedeemableItem {
    public String name, store;
    public long pointsValue;

    public RedeemableItem() {
        // Default constructor required for calls to DataSnapshot.getValue(RedeemableItem.class)
    }

    public RedeemableItem(String name, String store, long pointsValue) {
        this.name = name;
        this.store = store;
        this.pointsValue = pointsValue;
    }

    @Exclude
    @Override
    public String toString() {
        return (store + " " + name + " " + pointsValue);
    }
}
