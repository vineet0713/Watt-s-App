package com.example.vjoshi.wattsapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class TestPieGraph extends AppCompatActivity{
    private static String TAG = "TestPieGraph";

//    private String[] devices = {"D1","D2","D3","D4","D5","D6"};
//    private float[] watts = {10,2,13,43,13,85};


    // this is for testing purposes (to switch between grouping by devices, and grouping by device type)
    private static final String PIE_CHART_TYPE = "DEVICES";
//    private static final String PIE_CHART_TYPE = "DEVICE_TYPES";

    private ArrayList<String> devices;
    private ArrayList<Float> watts;

    // stores the abbreviated days {"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}
    private static final String[] DAYS = (new DateFormatSymbols(new Locale("en"))).getShortWeekdays();

    PieChart piechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        Log.d(TAG, "onCreate: Creating Pie Graph");

        piechart = (PieChart) findViewById(R.id.PieChart);

        piechart.setRotationEnabled(false);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        piechart.setTransparentCircleAlpha(0);
        piechart.setCenterText("Super Cool Chart");
        piechart.setCenterTextSize(10);
        piechart.setHoleRadius(0f);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!


        devices = new ArrayList<>();
        watts = new ArrayList<>();
    }


    @Override
    protected void onResume() {
        super.onResume();

        loadData();
        loadTimeData();
    }

    private void loadData() {
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final String username = Backend.getInstance().getUsername();
        database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<UsageEntry> entries = user.getUsageEntries();
                HashMap<String, Float> deviceToWatts = new HashMap<>();
                for (UsageEntry entry : entries) {
                    String device = (PIE_CHART_TYPE.equals("DEVICES")) ? entry.getDeviceName() : entry.getDeviceType();
                    double entryWatts = entry.getWattsUsed();
                    if (deviceToWatts.containsKey(device)) {
                        entryWatts += deviceToWatts.get(device).doubleValue();
                    }
                    deviceToWatts.put(device, (float)(entryWatts));
                }

                devices.clear();
                watts.clear();
                for (String key : deviceToWatts.keySet()) {
                    devices.add(key);
                    watts.add(deviceToWatts.get(key));
                }

                piechart.invalidate();
                // updates the pie chart with data
                addDataSet();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void loadTimeData() {
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final String username = Backend.getInstance().getUsername();
        database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<UsageEntry> entries = user.getUsageEntries();

                ArrayList<String> dates = new ArrayList<>();
                ArrayList<Float> watts = new ArrayList<>();

                Calendar calendar = Calendar.getInstance();

                int entryIndex = entries.size() - 1;
                calendar.setTime(entries.get(entryIndex).getUsageDate());
                int previousDayIndex = calendar.get(Calendar.DAY_OF_WEEK);
                int currentDayIndex = -1;
                float wattsOnCurrentDay = 0;

                while (entryIndex >= 0 && dates.size() <= 7) {
                    calendar.setTime(entries.get(entryIndex).getUsageDate());
                    currentDayIndex = calendar.get(Calendar.DAY_OF_WEEK);
                    if (previousDayIndex != currentDayIndex) {
                        // this is a new day!
                        watts.add(wattsOnCurrentDay);
                        wattsOnCurrentDay = 0;

                        dates.add(DAYS[previousDayIndex]);

                        // fill in the gap between days if it exists
                        while (dates.size() <= 7 && previousDayIndex - 1 != currentDayIndex) {
                            previousDayIndex--;
                            if (previousDayIndex == 0) {
                                previousDayIndex = 8;
                                continue;
                            }
                            dates.add(DAYS[previousDayIndex]);
                            watts.add(new Float(0));
                        }

                        previousDayIndex = currentDayIndex;
                    }

                    wattsOnCurrentDay += entries.get(entryIndex).getWattsUsed();
                    entryIndex--;
                }

                dates.add(DAYS[currentDayIndex]);
                watts.add(wattsOnCurrentDay);

                Log.d(TAG, "dates: " + dates.toString());
                Log.d(TAG, "watts: " + watts.toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        String noDataMessage ="Sorry there is no data to display at this time.";
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < watts.size(); i++){
            yEntrys.add(new PieEntry(watts.get(i).floatValue(), devices.get(i)));
        }


        for(int i = 0; i < devices.size(); i++){
            xEntrys.add(devices.get(i));
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Device Usage");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(24);


        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(Color.BLUE);
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

//        //add legend to chart
//        Legend legend = piechart.getLegend();
//        legend.setForm(Legend.LegendForm.CIRCLE);
//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
//
//        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        piechart.setData(pieData);
        piechart.setNoDataText(noDataMessage);
        piechart.invalidate();
    }
}
