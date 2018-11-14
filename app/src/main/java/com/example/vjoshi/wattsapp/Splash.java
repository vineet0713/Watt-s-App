package com.example.vjoshi.wattsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        RotateAnimation animation = new RotateAnimation(0f, 360f);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(1000);

        TextView tv = (TextView) findViewById(R.id.mainText);
        tv.startAnimation(animation);
    }

}
