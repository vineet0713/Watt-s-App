package com.example.vjoshi.wattsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class TestBarGraph extends AppCompatActivity {
    BarChart barChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);

        barChart = (BarChart) findViewById(R.id.barChart);
        ArrayList<BarEntry> data = new ArrayList<>();



        data.add(new BarEntry(0,800f));
        data.add(new BarEntry(1, 400));
        data.add(new BarEntry(2, 200));
        data.add(new BarEntry(3, 0));
        data.add(new BarEntry(4,300));
        data.add(new BarEntry(5,500));
        data.add(new BarEntry(6,800));

//        ArrayList<String> days = new ArrayList<>();
//
//        days.add("Monday");
//        days.add("Tuesday");
//        days.add("Wednesday");
//        days.add("Thursday");
//        days.add("Friday");
//        days.add("Saturday");
//        days.add("Sunday");

        BarDataSet barDataSet = new BarDataSet(data, "Data");

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        barChart.setScaleEnabled(true);
    }

}
