package com.example.vjoshi.wattsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;


public class RedeemActivity extends AppCompatActivity{

    AlertDialog dealDialog;
    private Button redeemListButton;


    //electronics - row 1
    final CharSequence[] frysItems = {" 20 inch Sony TV "," 30 inch LG TV "," 40 inch Panasonic TV "," 50 inch Apple TV "," 60 inch Samsung TV "};
    final CharSequence[] bestbuyItems = {" 20 inch Sony TV "," 30 inch LG TV "," 40 inch Panasonic TV "," 50 inch Apple TV "," 60 inch Samsung TV "};
    final CharSequence[] ebayItems = {" 20 inch Sony TV "," 30 inch LG TV "," 40 inch Panasonic TV "," 50 inch Apple TV "," 60 inch Samsung TV "};
    final CharSequence[] walmartItems = {" 20 inch Sony TV "," 30 inch LG TV "," 40 inch Panasonic TV "," 50 inch Apple TV "," 60 inch Samsung TV "};
    final CharSequence[] amazonItems = {" 20 inch Sony TV "," 30 inch LG TV "," 40 inch Panasonic TV "," 50 inch Apple TV "," 60 inch Samsung TV "};
    final CharSequence[] targetItems = {" 20 inch Sony TV "," 30 inch LG TV "," 40 inch Panasonic TV "," 50 inch Apple TV "," 60 inch Samsung TV "};
    final int[] frysCosts = {100000,200000,300000,400000,500000};
    final int[] bestbuyCosts = {100000,200000,300000,400000,500000};
    final int[] ebayCosts = {100000,200000,300000,400000,500000};
    final int[] walmartCosts = {100000,200000,300000,400000,500000};
    final int[] amazonCosts = {100000,200000,300000,400000,500000};
    final int[] targetCosts = {100000,200000,300000,400000,500000};

    //food - row 2
    final CharSequence[] wholefoodsItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final CharSequence[] safewayItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final CharSequence[] tacobellItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final CharSequence[] chickfilaItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final CharSequence[] pizzahutItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final CharSequence[] burgerkingItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final int[] wholefoodsCosts = {200,200,4000,100000,100000};
    final int[] safewayCosts = {200,200,4000,100000,100000};
    final int[] tacobellCosts = {200,200,4000,100000,100000};
    final int[] chickfilaCosts = {200,200,4000,100000,100000};
    final int[] pizzahutCosts = {200,200,4000,100000,100000};
    final int[] burgerkingCosts = {200,200,4000,100000,100000};

    //electronics - row 3
    final CharSequence[] macysItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final CharSequence[] levisItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final CharSequence[] costcoItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final CharSequence[] oldnavyItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final CharSequence[] nikeItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final CharSequence[] kohlsItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final int[] macysCosts = {200,200,4000,100000,100000};
    final int[] levisCosts = {200,200,4000,100000,100000};
    final int[] costcoCosts = {200,200,4000,100000,100000};
    final int[] oldnavyCosts = {200,200,4000,100000,100000};
    final int[] nikeCosts = {200,200,4000,100000,100000};
    final int[] kohlsCosts = {200,200,4000,100000,100000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_redeem);
        setTitle("Redeem Items");

        SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.activity_redeem)
                .setSwipeBackView(R.layout.swipeback_default);

        redeemListButton = findViewById(R.id.RedeemButton);

        final Intent redeemListIntent = new Intent(this, RedeemListActivity.class);
        redeemListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(redeemListIntent);
            }
        });


    }

    public void showPopUpFrys(View view){
        String title = "Frys";
        showDialog(title,frysItems, frysCosts);
    }

    public void showPopUpBestbuy(View view){
        String title = "Best Buy";
        showDialog(title,bestbuyItems, bestbuyCosts);
    }

    public void showPopUpEbay(View view){
        String title = "Ebay";
        showDialog(title,ebayItems, ebayCosts);
    }

    public void showPopUpWalmart(View view){
        String title = "Walmart";
        showDialog(title,walmartItems, walmartCosts);
    }

    public void showPopUpAmazon(View view){
        String title = "Amazon";

        showDialog(title,amazonItems, amazonCosts);
    }

    public void showPopUpTarget(View view){
        String title = "Target";
        showDialog(title,targetItems, targetCosts);
    }


    public void showPopUpWholefoods(View view){
        String title = "Whole Foods";
        showDialog(title,wholefoodsItems, wholefoodsCosts);
    }

    public void showPopUpSafeway(View view){
        String title = "Safeway";
        showDialog(title,safewayItems, safewayCosts);
    }

    public void showPopUpTacobell(View view){
        String title = "Taco Bell";
        showDialog(title,tacobellItems, tacobellCosts);
    }

    public void showPopUpChickfila(View view){
        String title = "Chick-fil-A";
        showDialog(title,chickfilaItems, chickfilaCosts);
    }

    public void showPopUpPizzahut(View view){
        String title = "Pizza Hut";
        showDialog(title,pizzahutItems, pizzahutCosts);
    }

    public void showPopUpBurgerking(View view){
        String title = "Burger King";
        showDialog(title,burgerkingItems, burgerkingCosts);
    }


    public void showPopUpMacys(View view){
        String title = "Macy's";
        showDialog(title,macysItems, macysCosts);
    }

    public void showPopUpLevis(View view){
        String title = "Levi's";
        showDialog(title,levisItems, levisCosts);
    }

    public void showPopUpCostco(View view){
        String title = "Costco";
        showDialog(title,costcoItems, costcoCosts);
    }

    public void showPopUpOldNavy(View view){
        String title = "Old Navy";
        showDialog(title,oldnavyItems, oldnavyCosts);
    }

    public void showPopUpNike(View view){
        String title = "Nike";
        showDialog(title,nikeItems, nikeCosts);
    }

    public void showPopUpKohls(View view){
        String title = "Kohl's";
        showDialog(title,kohlsItems, kohlsCosts);
    }


    public void showDialog(String inputTitle, final CharSequence[] itemList, final int[] costsLists ){
        final CharSequence[] displayList = new CharSequence[costsLists.length];
        final String title = inputTitle;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setCancelable(true);
        builder.setTitle(title);
        for(int i = 0 ; i < costsLists.length; i++)
        {
            displayList[i] = Integer.toString(costsLists[i]) +" pts - "+ itemList[i];
        }
        builder.setSingleChoiceItems(displayList, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int targetItems ) {


                switch(targetItems)
                {
                    case 0:
                        confirmationDialog(title,itemList,costsLists,0);
                        break;
                    case 1:
                        confirmationDialog(title,itemList,costsLists,1);
                        break;
                    case 2:
                        confirmationDialog(title,itemList,costsLists,2);
                        break;
                    case 3:
                        confirmationDialog(title,itemList,costsLists,3);
                        break;
                    case 4:
                        confirmationDialog(title,itemList,costsLists,4);
                        break;

                }
                dealDialog.dismiss();
            }
        });
        dealDialog = builder.create();
        dealDialog.show();
    }

    private void confirmationDialog(final String title, final CharSequence[] array, final int[] costArray, final int i){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to redeem " + costArray[i] + " points for a " +array[i]+ "?");

        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                final String username = Backend.getInstance().getUsername();
                database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User u = dataSnapshot.getValue(User.class);
                        String item = array[i].toString();
                        int points = costArray[i];
                        String toastText;

                        if (u.canRedeemItem(points)) {
                            u.subtractTotalPoints(points);
                            u.addRedeemableItem(new RedeemableItem(item, title, points));

                            final User modifiedUser = u;
                            database.child(username).setValue(modifiedUser);

                            toastText = "Successful!";
                        } else {
                            // user doesn't have enough points to redeem the item!
                            long pointsNeeded = points - u.getTotalPoints();
                            toastText = "You need " + pointsNeeded + " more points to get " + item + "!";
                        }

                        Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) { }
                });
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        builder.show();
    }
}
