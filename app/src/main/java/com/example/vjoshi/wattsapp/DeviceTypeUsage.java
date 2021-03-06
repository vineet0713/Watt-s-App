package com.example.vjoshi.wattsapp;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeviceTypeUsage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeviceTypeUsage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeviceTypeUsage extends Fragment {


    private static String TAG = "TestPieGraph";


    private ArrayList<String> devices;
    private ArrayList<Float> watts;

    PieChart piechart;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DeviceTypeUsage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeviceTypeUsage.
     */
    // TODO: Rename and change types and number of parameters
    public static DeviceTypeUsage newInstance(String param1, String param2) {
        DeviceTypeUsage fragment = new DeviceTypeUsage();
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
        View view = inflater.inflate(R.layout.fragment_device_type_usage, container, false);

        piechart = (PieChart) view.findViewById(R.id.PieChart);
        piechart.setRotationEnabled(false);
        piechart.setTransparentCircleAlpha(0);
        piechart.setCenterText("Super Cool Chart");
        piechart.setCenterTextSize(10);
        piechart.setHoleRadius(0f);

        piechart.getDescription().setEnabled(false);
        piechart.getLegend().setEnabled(false);

        devices = new ArrayList<>();
        watts = new ArrayList<>();

        loadData();
        Log.d(TAG, "addDataSet started");
        String noDataMessage = "Sorry there is no data to display at this time.";
//        ArrayList<PieEntry> yEntrys = new ArrayList<>();
//        ArrayList<String> xEntrys = new ArrayList<>();
//
//        for (int i = 0 ; i < watts.length; i ++ ){
//            yEntrys.add(new PieEntry(watts[i],i));
//        }
//        for (int i = 0 ; i < devices.length; i ++ ){
//            xEntrys.add(devices[i]);
//        }
//
//        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Device Usage");
//        pieDataSet.setSliceSpace(2);
//        pieDataSet.setValueTextSize(24);
//
//        ArrayList<Integer> colors = new ArrayList<>();
//        colors.add(Color.BLUE);
//        colors.add(Color.GRAY);
//        colors.add(Color.RED);
//        colors.add(Color.GREEN);
//        colors.add(Color.CYAN);
//        colors.add(Color.YELLOW);
//        colors.add(Color.MAGENTA);
//
//        pieDataSet.setColors(colors);
//
//        PieData pieData = new PieData(pieDataSet);
//        piechart.setData(pieData);
//        piechart.invalidate();

        return view;
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
                    String device =  entry.getDeviceType();
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
