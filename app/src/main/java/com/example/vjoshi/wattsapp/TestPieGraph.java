package com.example.vjoshi.wattsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class TestPieGraph extends AppCompatActivity{

    private static String TAG = "TestPieGraph";
    private String[] devices = {"D1","D2","D3","D4","D5","D6"};
    private float[] watts = {10,2,13,43,13,85};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Creating Pie Graph");

    }
}
