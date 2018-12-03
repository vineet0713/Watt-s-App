package com.example.vjoshi.wattsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Leaderboard extends AppCompatActivity {
    private ArrayList<String> userList;
    private ArrayAdapter<String> arrayAdapter;
    private TextView currentRankingView;

    class Pair implements Comparable<Pair> {
        public String username;
        public long wattsUsed;
        public Pair(String username, long wattsUsed) {
            this.username = username;
            this.wattsUsed = wattsUsed;
        }
        public int compareTo(Pair otherPair) {
            // reverse sort because we want to sort in descending order
            return (int)(otherPair.wattsUsed - wattsUsed);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Leaderboard");

        SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.activity_leaderboard)
                .setSwipeBackView(R.layout.swipeback_default);
        //setContentView(R.layout.activity_leaderboard);

        currentRankingView = findViewById(R.id.textView4);

        ListView lv = findViewById(R.id.leaderList);

        userList = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                userList);

        lv.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadLeaderboard();
    }

    private void loadLeaderboard() {
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Pair> pairs = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // this avoids casting the topRankHolder child to a User
                    try {
                        User user = snapshot.getValue(User.class);
                        pairs.add(new Pair(user.getUsername(), user.getDailyPoints()));
                    } catch (Exception e) {
                        continue;
                    }
                }

                Collections.sort(pairs);
                String currentTopRankHolder = (String) dataSnapshot.child("topRankHolder").getValue();
                if (pairs.size() > 0) {
                    // check if the top rankholder has changed
                    String newTopRankHolder = pairs.get(0).username;
                    if (newTopRankHolder.equals(currentTopRankHolder) == false) {
                        database.child("topRankHolder").setValue(newTopRankHolder);
                    }
                }

                userList.clear();
                final String myUsername = Backend.getInstance().getUsername();
                boolean myUsernameFound = false;
                for (int i = 0; i < pairs.size(); i++) {
                    userList.add((i + 1) + ". " + pairs.get(i).username + "\n\t\t\t" + pairs.get(i).wattsUsed + " points");
                    if (myUsernameFound == false && myUsername.equals(pairs.get(i).username)) {
                        currentRankingView.setText("Current Ranking: " + (i + 1));
                        myUsernameFound = true;
                    }
                }

                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}
