package com.example.vjoshi.wattsapp;

public class Device {
    public int id;
    public String type, company, model;
    public double usageRatePerMinute;

    public Device() {
        // Default constructor required for calls to DataSnapshot.getValue(Device.class)
    }

    public Device(int id, String type, String company, String model, double usageRatePerMinute) {
        this.id = id;
        this.type = type;
        this.company = company;
        this.model = model;
        this.usageRatePerMinute = usageRatePerMinute;
    }
}
