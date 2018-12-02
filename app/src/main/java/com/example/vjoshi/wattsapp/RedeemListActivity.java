package com.example.vjoshi.wattsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;

import java.util.ArrayList;

public class RedeemListActivity extends AppCompatActivity {
    private ArrayList<String> redeemList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.activity_redeem_list)
                .setSwipeBackView(R.layout.swipeback_default);

        ListView list = findViewById(R.id.redeemList);

        redeemList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, redeemList);

        list.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadRedeemList();
    }

    private void loadRedeemList() {
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final String username = Backend.getInstance().getUsername();
        database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                ArrayList<RedeemableItem> itemsRedeemed = u.getItemsRedeemed();

                redeemList.clear();
                for (RedeemableItem item : itemsRedeemed) {
                    redeemList.add(item.toString());
                }

                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}
