package com.example.vjoshi.wattsapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;

import java.util.ArrayList;
import java.util.List;

public class TestRedeemActivity extends AppCompatActivity{

    AlertDialog dealDialog;

    final CharSequence[] targetItems = {" 20oz Soda "," Pop Rocks Candy "," Jansport Backpack  "," XBox One "," Play Station 4 "};
    final int[] targetCosts = {200,200,4000,100000,100000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_redeem);

        SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.activity_redeem)
                .setSwipeBackView(R.layout.swipeback_default);
    }
    public void showPopUp(View view){
        String title = "Target";
        String message = "Would you like to redeem your points to get discounts at target?";
        showDialog(title,message,targetItems, targetCosts);
    }
    public void showDialog(String title, String Message, final CharSequence[] itemList, final int[] costsLists ){
        final CharSequence[] displayList = new CharSequence[costsLists.length];
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
                        confirmationDialog(itemList,costsLists,0);
                        break;
                    case 1:
                        confirmationDialog(itemList,costsLists,1);
                        break;
                    case 2:
                        confirmationDialog(itemList,costsLists,2);
                        break;
                    case 3:
                        confirmationDialog(itemList,costsLists,3);
                        break;
                    case 4:
                        confirmationDialog(itemList,costsLists,4);
                        break;

                }
                dealDialog.dismiss();
            }
        });
        dealDialog = builder.create();
        dealDialog.show();
    }

    private void confirmationDialog(CharSequence[] array,int[] costArray, int i){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        int pointsCost = 200;
        builder.setCancelable(true);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to redeem " + costArray[i] + " points for a " +array[i]+ "?");

        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        builder.show();
    }
}
