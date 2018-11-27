package com.example.vjoshi.wattsapp;

import android.util.Log;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Objects;

@IgnoreExtraProperties
public class Device {
    public int id;
    public String type, company, model;
    public double usageRate;
    public String status;  // this could be "OFF" or "ON"
    public long startTime;

    public Device() {
        // Default constructor required for calls to DataSnapshot.getValue(Device.class)
    }

    public Device(String type, String company, String model) {
        this.type = type;
        this.company = company;
        this.model = model;

        this.id = Backend.getInstance().getIdForDevice(this.getDeviceName());
        this.usageRate = Backend.getInstance().getUsageForDevice(this.getDeviceName());

        this.status = "OFF";
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
    public String getDeviceName() { return (company + " " + model); }

    @Exclude
    public double getUsageRate() {
        return usageRate;
    }

    @Exclude
    public void setUsageRate(double usageRate) {
        this.usageRate = usageRate;
    }

    @Exclude
    public String getStatus() { return status; }

    @Exclude
    public void setStatus(String status) { this.status = status; }

    @Exclude
    public long getStartTime() { return startTime; }

    @Exclude
    public void setStartTime(long startTime) { this.startTime = startTime; }

    @Exclude
    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", usageRate=" + usageRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return id == device.id &&
                Double.compare(device.usageRate, usageRate) == 0 &&
                Objects.equals(type, device.type) &&
                Objects.equals(company, device.company) &&
                Objects.equals(model, device.model);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, company, model, usageRate);
    }
}
