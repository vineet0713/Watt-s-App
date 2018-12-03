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


public class TestRedeemActivity extends AppCompatActivity{

    AlertDialog dealDialog;
    private Button redeemListButton;


    //electronics - row 1
    final CharSequence[] frysItems = {"20 inch Sony TV ","30 inch LG TV ","40 inch Panasonic TV ","50 inch Apple TV ","60 inch Samsung TV "};
    final CharSequence[] bestbuyItems = {"20 inch Sony TV ","30 inch LG TV ","40 inch Panasonic TV ","50 inch Apple TV ","60 inch Samsung TV "};
    final CharSequence[] ebayItems = {"20 inch Sony TV ","30 inch LG TV ","40 inch Panasonic TV ","50 inch Apple TV ","60 inch Samsung TV "};
    final CharSequence[] walmartItems = {"20 inch Sony TV ","30 inch LG TV ","40 inch Panasonic TV ","50 inch Apple TV ","60 inch Samsung TV "};
    final CharSequence[] amazonItems = {"20 inch Sony TV ","30 inch LG TV ","40 inch Panasonic TV ","50 inch Apple TV ","60 inch Samsung TV "};
    final CharSequence[] targetItems = {"20 inch Sony TV ","30 inch LG TV ","40 inch Panasonic TV ","50 inch Apple TV ","60 inch Samsung TV "};
    final int[] frysCosts = {100000,200000,300000,400000,500000};
    final int[] bestbuyCosts = {100000,200000,300000,400000,500000};
    final int[] ebayCosts = {100000,200000,300000,400000,500000};
    final int[] walmartCosts = {100000,200000,300000,400000,500000};
    final int[] amazonCosts = {100000,200000,300000,400000,500000};
    final int[] targetCosts = {100000,200000,300000,400000,500000};

    //food - row 2
    final CharSequence[] wholefoodsItems = {"12oz Chicken Breast ","12oz Steak ","20oz Soda ","Ice Cream 4 Pack ","KitKat Bar "};
    final CharSequence[] safewayItems = {"12oz Chicken Breast ","12oz Steak ","20oz Soda ","Ice Cream 4 Pack ","KitKat Bar "};
    final CharSequence[] tacobellItems = {"Soft Taco ","Crunchy Taco ","Bean Burrito ","Super Burrito ","Ultra Super Burrito "};
    final CharSequence[] chickfilaItems = {"Chicken Sandwich ","Spicy Chicken Sandwich ","6 piece Chicken Nuggets  ","8 piece Chicken Nuggets ","Family Meal "};
    final CharSequence[] pizzahutItems = {"20oz Soda ","1 piece Pizza ","Small Pizza ","Medium Pizza ","Large Pizza "};
    final CharSequence[] burgerkingItems = {"Chicken Sandwich ","Spicy Chicken Sandwich ","6 piece Chicken Nuggets  ","8 piece Chicken Nuggets ","Family Meal "};
    final int[] wholefoodsCosts = {600,600,200,8000,100};
    final int[] safewayCosts = {600,600,200,8000,100};
    final int[] tacobellCosts = {200,200,400,600,800};
    final int[] chickfilaCosts = {200,200,400,600,800};
    final int[] pizzahutCosts = {200,200,400,600,800};
    final int[] burgerkingCosts = {200,200,400,600,800};

    //electronics - row 3
    final CharSequence[] macysItems = {"Black T Shirt ","White T Shirt ","Sweater ","Light Jacket ","Heavy Jacket "};
    final CharSequence[] levisItems = {"Black T Shirt ","White T Shirt ","Sweater ","Light Jacket ","Heavy Jacket "};
    final CharSequence[] costcoItems = {"Black T Shirt ","White T Shirt ","Sweater ","Light Jacket ","Heavy Jacket "};
    final CharSequence[] oldnavyItems = {"Black T Shirt ","White T Shirt ","Sweater ","Light Jacket ","Heavy Jacket "};
    final CharSequence[] nikeItems = {"Black T Shirt ","White T Shirt ","Sweater ","Light Jacket ","Heavy Jacket "};
    final CharSequence[] kohlsItems = {"Black T Shirt ","White T Shirt ","Sweater ","Light Jacket ","Heavy Jacket "};
    final int[] macysCosts = {2000,2000,4000,4000,6000};
    final int[] levisCosts = {2000,2000,4000,4000,6000};
    final int[] costcoCosts = {2000,2000,4000,4000,6000};
    final int[] oldnavyCosts = {2000,2000,4000,4000,6000};
    final int[] nikeCosts = {2000,2000,4000,4000,6000};
    final int[] kohlsCosts = {2000,2000,4000,4000,6000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_redeem);
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
