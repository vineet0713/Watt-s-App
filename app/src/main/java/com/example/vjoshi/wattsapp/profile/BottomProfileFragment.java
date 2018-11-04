package com.example.vjoshi.wattsapp.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.vjoshi.wattsapp.R;

public class BottomProfileFragment extends Fragment {

    private static Button dayButton;
    private static Button weekButton;
    private static Button monthButton;


    TopSectionListener activityCommander;

    public interface TopSectionListener{
        public void setViews(String setting);
    }

    /**
     * Called when we attach the fragment to the activity
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (TopSectionListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_fragment, container, false);

        dayButton = view.findViewById(R.id.dayButton);
        weekButton = view.findViewById(R.id.weekButton);
        monthButton = view.findViewById(R.id.monthButton);


        dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked("Day");
            }
        });
        weekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked("Week");
            }
        });
        monthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked("Month");
            }
        });

        return view;
    }
    public void buttonClicked(String setting){
        activityCommander.setViews(setting);
    }
}
