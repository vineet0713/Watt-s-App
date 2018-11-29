package com.example.vjoshi.wattsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Leaderboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        ArrayList<String> userList;
        //ArrayList<Integer> points;
        ListView lv = findViewById(R.id.leaderList);

        userList = new ArrayList<>(Arrays.asList("Ken", "Vineet", "Eric", "Dominic"));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                userList );

        lv.setAdapter(arrayAdapter);

    }
}
