package com.example.vjoshi.wattsapp;

import android.content.Intent;
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


        RotateAnimation animation = new RotateAnimation(180f, 360f,400,2000);
        //animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(2000);
        final TextView subtv =(TextView) findViewById(R.id.subtext);
        subtv.setText("Loading.");
        subtv.setText("Loading..");
        subtv.setText("Loading...");

        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                subtv.setText("Ready!");
                nextPage();
            }
        });
        animation.setDuration(1000);
        animation.start();

    }
    public void nextPage(){
        Intent launcherActivity = new Intent(this, LauncherActivity.class);
        startActivity(launcherActivity);
    }

}
