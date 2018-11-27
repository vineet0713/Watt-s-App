package com.example.vjoshi.wattsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TestBarGraph extends AppCompatActivity {
    private static String TAG = "TestBarGraph";

    // stores the abbreviated days {"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}
    private static final String[] DAYS = (new DateFormatSymbols(new Locale("en"))).getShortWeekdays();

    private ArrayList<String> dates;
    private ArrayList<Float> watts;

    BarChart barChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);

        barChart = (BarChart) findViewById(R.id.barChart);
        barChart.setScaleEnabled(true);
        barChart.setDragXEnabled(true);
        barChart.setDragYEnabled(true);

        dates = new ArrayList<>();
        watts = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadTimeData();
    }

    private void loadTimeData() {
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final String username = Backend.getInstance().getUsername();
        database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<UsageEntry> entries = user.getUsageEntries();
                if (entries.isEmpty()) {
                    return;
                }

                Calendar calendar = Calendar.getInstance();

//                int entryIndex = entries.size() - 1;
//                calendar.setTime(entries.get(entryIndex).getUsageDate());
                calendar.setTime(entries.get(0).getUsageDate());
                int previousDayIndex = calendar.get(Calendar.DAY_OF_WEEK);
                int currentDayIndex = -1;
                float wattsOnCurrentDay = 0;

                watts.clear();
                dates.clear();

                for (UsageEntry entry : entries) {
                    calendar.setTime(entry.getUsageDate());
                    currentDayIndex = calendar.get(Calendar.DAY_OF_WEEK);
                    if (previousDayIndex != currentDayIndex) {
                        // this is a new day!
                        watts.add(wattsOnCurrentDay);
                        wattsOnCurrentDay = 0;

                        dates.add(DAYS[previousDayIndex]);

                        // fill in the gap between days if it exists
                        while (previousDayIndex + 1 != currentDayIndex) {
                            previousDayIndex++;
                            if (previousDayIndex == 8) {
                                previousDayIndex = 0;
                                continue;
                            }
                            dates.add(DAYS[previousDayIndex]);
                            watts.add(new Float(0));
                        }

                        previousDayIndex = currentDayIndex;
                    }

                    wattsOnCurrentDay += entry.getWattsUsed();
                }

                dates.add(DAYS[currentDayIndex]);
                watts.add(wattsOnCurrentDay);

                barChart.invalidate();
                // updates the bar graph with data
                addDataSet();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void addDataSet() {
//        Log.d(TAG, "dates: " + dates.toString());
//        Log.d(TAG, "watts: " + watts.toString());

        ArrayList<BarEntry> data = new ArrayList<>();

//        data.add(new BarEntry(0,800f));
//        data.add(new BarEntry(1, 400));
//        data.add(new BarEntry(2, 200));
//        data.add(new BarEntry(3, 0));
//        data.add(new BarEntry(4,300));
//        data.add(new BarEntry(5,500));
//        data.add(new BarEntry(6,800));

        for (int i = 0; i < watts.size(); i++) {
            data.add(new BarEntry(i, watts.get(i).floatValue()));
        }

        BarDataSet barDataSet = new BarDataSet(data, "Data");

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dates.get((int)(value));
            }
        });
    }

}
