package com.example.vjoshi.wattsapp.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.example.vjoshi.wattsapp.R;

public class TopProfileFragment extends Fragment {
    private static ViewFlipper viewFlipper;
    private static Button flipperButton;
    private static Button flipperButton2;
    private static int count = 0;
    private static int count2 = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_profile, container, false);

        viewFlipper = view.findViewById(R.id.view_flipper);
        flipperButton = view.findViewById(R.id.flipperButton);
        flipperButton2 = view.findViewById(R.id.flipperButton2);

        final Button previousButton = view.findViewById(R.id.previousButton);
        Button nextButton = view.findViewById(R.id.nextButton);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });

        return view;
    }

    public static void setFlipperButton(String setting){
        flipperButton.setText("Flipper 1 " + setting);
    }

    public static void setFlipperButton2(String setting){
        flipperButton2.setText("Flipper 2 " +setting);
    }

}
