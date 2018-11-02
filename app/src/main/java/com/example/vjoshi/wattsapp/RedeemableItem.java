package com.example.vjoshi.wattsapp;

public class RedeemableItem {
    public String name, description;
    public double value;

    public RedeemableItem() {
        // Default constructor required for calls to DataSnapshot.getValue(RedeemableItem.class)
    }

    public RedeemableItem(String name, String description, double value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }
}
