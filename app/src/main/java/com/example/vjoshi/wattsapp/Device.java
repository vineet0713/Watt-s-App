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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getUsageRatePerMinute() {
        return usageRatePerMinute;
    }

    public void setUsageRatePerMinute(double usageRatePerMinute) {
        this.usageRatePerMinute = usageRatePerMinute;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", usageRatePerMinute=" + usageRatePerMinute +
                '}';
    }
}
