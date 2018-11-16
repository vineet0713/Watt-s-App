package com.example.vjoshi.wattsapp.addDeviceClasses.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vjoshi.wattsapp.Backend;
import com.example.vjoshi.wattsapp.Device;
import com.example.vjoshi.wattsapp.HomeActivity;
import com.example.vjoshi.wattsapp.R;
import com.example.vjoshi.wattsapp.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;



public class ModelRecyclerViewAdapter extends RecyclerView.Adapter<ModelRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "CompanyAdapter";
    final static Bundle deviceBundle = new Bundle();

    private ArrayList<String> mImageNames;
    private ArrayList<String> mImages;
    private Context mContext;


    public ModelRecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mContext) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        final Intent mainIntent = new Intent(mContext, HomeActivity.class);


        Glide.with(mContext).asBitmap().load(mImages.get(i)).into(viewHolder.imageView);
        viewHolder.imageName.setText(mImageNames.get(i));


        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(i));
                String selectedModel = mImageNames.get(i);
                HomeActivity.setModel(selectedModel);
                createAlert();
                //Toast.makeText(mContext, mImageNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Tells adapter how many items are in the list
    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageView;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.circleImage);
            imageName = itemView.findViewById(R.id.imageName);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

    public void createAlert(){

        final Intent mainIntent = new Intent(mContext, HomeActivity.class);
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final String device = HomeActivity.getDevice();
        final String company = HomeActivity.getCompany();
        final String model = HomeActivity.getModel();

        builder.setTitle(R.string.app_name);
        final String message = "Device: " + device + " | " + "Company: " + company + " | " + "Model: " + model;
        builder.setMessage("Do you wish to add this device? \n" + company + " " + model);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int id) {
                final Device newDevice = new Device(device, company, model);
                final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                final String username = Backend.getInstance().getUsername();
                database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        user.addDevice(newDevice);
                        final User modifiedUser = user;
                        database.child(username).setValue(modifiedUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                dialog.dismiss();
                                mContext.startActivity(mainIntent);
                            }
                        });
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) { }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.i(TAG, "Device not selected");
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
