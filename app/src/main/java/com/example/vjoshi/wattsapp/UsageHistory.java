package com.example.vjoshi.wattsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UsageHistory.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UsageHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsageHistory extends Fragment {
    private static String TAG = "TestBarGraph";

    // stores the abbreviated days {"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}
    private static final String[] DAYS = (new DateFormatSymbols(new Locale("en"))).getShortWeekdays();

    private ArrayList<String> dates;
    private ArrayList<Float> watts;

    BarChart barChart;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UsageHistory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsageHistory.
     */
    // TODO: Rename and change types and number of parameters
    public static UsageHistory newInstance(String param1, String param2) {
        UsageHistory fragment = new UsageHistory();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_usage_history, container, false);

        barChart = (BarChart) view.findViewById(R.id.barChart);
        barChart.setScaleEnabled(true);
        barChart.setDragXEnabled(true);
        barChart.setDragYEnabled(true);

        dates = new ArrayList<>();
        watts = new ArrayList<>();

        loadTimeData();

        return view;

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
        ArrayList<BarEntry> data = new ArrayList<>();

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
