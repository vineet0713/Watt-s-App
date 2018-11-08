package com.example.vjoshi.wattsapp.addDeviceClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vjoshi.wattsapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.DEVICENAME;
import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.INDEX;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    final static Bundle deviceBundle = new Bundle();

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;


    public RecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mContext) {
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
        final Intent companyIntent = new Intent(mContext, CompanySelectionActivity.class);


        Glide.with(mContext).asBitmap().load(mImages.get(i)).into(viewHolder.imageView);
        viewHolder.imageName.setText(mImageNames.get(i));
        
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(i));
                String selectedDevice = getDeviceName(i);
                deviceBundle.putString(DEVICENAME, selectedDevice);
                deviceBundle.putInt(INDEX, i);
                companyIntent.putExtras(deviceBundle);
                mContext.startActivity(companyIntent);
                //Toast.makeText(mContext, mImageNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public String getDeviceName(int i){
        String selectedDevice = "";
        switch (i){
            case 1:
                selectedDevice = "Laptop";
                break;
            case 2:
                selectedDevice = "Desktop";
                break;
            case 3:
                selectedDevice = "Phone";
                break;
            case 4:
                selectedDevice = "Tablet";
                break;
            case 5:
                selectedDevice = "TV";
                break;
            case 6:
                selectedDevice = "Smart Watch";
                break;
        }
        return selectedDevice;
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
}
