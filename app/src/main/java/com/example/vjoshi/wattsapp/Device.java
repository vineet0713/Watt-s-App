package com.example.vjoshi.wattsapp;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Objects;

@IgnoreExtraProperties
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

    @Exclude
    public int getId() {
        return id;
    }

    @Exclude
    public void setId(int id) {
        this.id = id;
    }

    @Exclude
    public String getType() {
        return type;
    }

    @Exclude
    public void setType(String type) {
        this.type = type;
    }

    @Exclude
    public String getCompany() {
        return company;
    }

    @Exclude
    public void setCompany(String company) {
        this.company = company;
    }

    @Exclude
    public String getModel() {
        return model;
    }

    @Exclude
    public void setModel(String model) {
        this.model = model;
    }

    @Exclude
    public double getUsageRatePerMinute() {
        return usageRatePerMinute;
    }

    @Exclude
    public void setUsageRatePerMinute(double usageRatePerMinute) {
        this.usageRatePerMinute = usageRatePerMinute;
    }

    @Exclude
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return id == device.id &&
                Double.compare(device.usageRatePerMinute, usageRatePerMinute) == 0 &&
                Objects.equals(type, device.type) &&
                Objects.equals(company, device.company) &&
                Objects.equals(model, device.model);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, company, model, usageRatePerMinute);
    }
}
