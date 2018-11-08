package com.example.vjoshi.wattsapp.addDeviceClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.vjoshi.wattsapp.R;

import java.util.ArrayList;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;

public class DeviceSelectionActivity extends AppCompatActivity {

    final static Bundle deviceBundle = new Bundle();

    private static final String TAG = "DeviceSelectionActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    public void cleanBundle(){
        deviceBundle.remove(INDEX);
        deviceBundle.remove(DEVICENAME);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_selection);

        Log.d(TAG, "onCreate: started");

        initImageBitMaps();

//
//        setTitle("Select Device");
//
//        final Intent companyIntent = new Intent(this, CompanySelectionActivity.class);
//
//        String[] devices = {"Laptop", "Desktop","Phone","Tablet","TV","Smart Watch"};
//
//        final ListView deviceList = findViewById(R.id.deviceList);
//        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, devices);
//        deviceList.setAdapter(listAdapter);
//
//        deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedDevice = String.valueOf(parent.getItemAtPosition(position));
//                deviceBundle.putString(DEVICENAME, selectedDevice);
//                deviceBundle.putInt(INDEX, position);
//                companyIntent.putExtras(deviceBundle);
//                startActivity(companyIntent);
//            }
//        });

    }
    private void initImageBitMaps(){
        Log.d(TAG, "initImageBitMaps: preparing bitMaps");

        mImageUrls.add("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAANlBMVEX///8AAAAJCQno6OgrKyv39/c3NzfLy8sUFBR0dHQvLy/29vaOjo6Li4syMjLi4uIbGxtNTU1OiIltAAABSElEQVR4nO3cTW+CQBSGUQTEL0T9/3+2ppsuqk2G+mam9py9zn1CmITN7ToAAAAAAAAAAIg7nZd+05B+mbdrOi5j7cm/Gy8rnkeDHfeS8mdyrj3zY3NxyFJ75MeW4pCm3vMvfXFI7YmfEVL8wxAhQkKECAkRIiREiJAQIUJChAgJESIkRIiQECFCQoQICREiJESIkBAhQkKECAkRIiREiJAQIUJChAgJESIkRIiQECFCQoQICREiJESIkBAhQkKECAkRsmnU/w15m+UVja4TmYpD5tojP1a+4GXb5Mqd44o1SC0uQTquWIJ0fyZzY2uppnVrqQCalLkqK5wY+tt3CRmedwyZE0Mht6clwy1zYqvfHaX6bqo9wmtMrS6WLHXuTtfaM7zC9dR1u33tKX5vv/u8Rw7jn37j+/Hww20PAAAAAAAAAAClPgCsWhfLZmIuBQAAAABJRU5ErkJggg==");
        mNames.add("Laptop");

        mImageUrls.add("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAAV1BMVEX///8AAAD39/cFBQUJCQkeHh4aGhpRUVGhoaHU1NRpaWmoqKhUVFTPz8/MzMzX19dycnJoaGgrKysxMTHx8fGSkpI0NDR1dXVKSkqVlZXi4uKLi4sQEBCe6BppAAADB0lEQVR4nO2c7XaiMBBAJYCIX2WrWFv7/s+5gssa7GDMANHqvb96DpkJtyVl7OnMZAIAAAAAAAAAAAAAAAAAAAAAAK+BmWVpHDmI02xmlLFD0LG/zWJ6a7LpQh87BD/3t/njk2reI3YI5rKD4l7mPWKHoNNk4ZtpcRGbfKwcj+4wmFWRXOzfuuz9jKemFZu+hbA4kafHDafyt23m6xFFMzs2CehxNInt/Vtk/iJLO/YjmERNYe/fIvUXSe3YVTCHmrW9fwvFuyy2Y4Oc8zPG3r9Fc3e3ZLlca39tDpvR3u/x5mCkPccQ+Xofy+LE+1cYETOyx9HEBBE5jO0RRYcgIpvxRXZBRALU8UkQkfE97J1eQ8Q/JSJKEHGlRETJq4mIFx1rEUEEEUQQQUSfEhEliLhSIqIEEVdKRJQMIhJ/brfl/7/C/WKRz2pp2U75K0W21dJtOyUiSgYRKZ/l0YrLJznsUkpElCDiSomIEkRcKRFRgogrJSJKEHGlREQJIq6UiChBxJUSESWIuFIiogQRV0pElCDiSomIklcR6cM56QP8k/8wIg/QdjGMSIBGmDKIyPitSRtXa9IwIqM3i22czWIDiUzMYZeMZZHsytvb93xoYh+yoVIj8lAtrn1EqqbjIphDTXfTcR+Rug08DyZxZFU9zXJnfh+RU2N+QJNVvaF8LPuI/BuVUKzDjEpYF3Ul1DEqQb7Vao5DM2Phishkrvk+9KNreIW4uJnjkMtt+1Z4cJPOIRzS4vMch1z8mdjxYQe8pN0DXqTl1hyHQrreSmBmy1Ajd5aLK4dRCrFecmvpene2OyLdqOVtEAnN0zxa0jm16ifpsMvV572R3hTn+imXPOXq896II3ea+kl+IcrVp0y+9/q4lezVo4jkIUhV/dTUNj+4OnXswuPbR6PiW2vSZyyVm71v8ijKlCK9BoU5UXyMT7QivnXflSFwdxbxM/HyCPpoTXwq2CvVp8hbuMNec1MF66g+O0wyv1+/WdBJcAAAAAAAAAAAAAAAAAAAAAAAAHAjfwGgA0BiFJQvLgAAAABJRU5ErkJggg==");
        mNames.add("Desktop");

        mImageUrls.add("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAAclBMVEX///8AAACXl5ebm5szMzNnZ2cvLy/7+/sMDAzv7+87OzvCwsKenp7w8PDFxcW9vb1aWlpgYGDp6ena2tqBgYEREREiIiJ5eXk+Pj4aGhoJCQni4uI2Njampqa3t7dHR0fS0tJxcXFRUVGOjo4nJyd+fn4MI4ImAAAD7klEQVR4nO2d62KiMBBGBatUURTvN1Rq+/6vuJvgCoNgbDNjoPudX+1KCad1QnaYOJ0OAAAAAAAAAAAAfjGz48j/EaPj3vW15wT+m2dB3w9cG2SEKxsNxSp07aAY2WooRq4tOp2Ew8Pzjq495u88IsuZY5ETj4fn9dx6nLk8vN3AqciaTcRLnIoc+ETcvreWfCInpyKGiyOTqiGeDq4cNAaRc/HY6PGxb24MrhhEvgNEIAIRiEAEIu0W2SWWyYMg2TVCZG1/mrQRIl370/gQ4QIiFIiwAREKRNiACAUibECEAhE2IEKBCBsQoUCEDYhQIMIGRCivEkmX65qsqLxIsF4ypDIz9mqYeeVL4iJzVdPGVXATqMqy98qSKmmRrkpvf7JV2I2n6oKnFWUvsiKDDz3u2H6AG4kqy6qo2BMVmWzVO4G52Gauy2fuYl5QJNCVR0P2ItTgS533UIp5OREd5d6XRAGqriYt/aXFREY172UWBtO72BMSGdTOLkxkv6co/wcZkXBbO99zkcV8XoYlIpJURSM3ei5Z3L4VEfmqmh/5CTdxHiUiIoPp5uU15i1b/dYDEQpE2IAIBSJstEckGvYJm4i8LCZSHncY1fzoswy9EhvyspiIYdzvMy2fkO6OEBOJy+PGlkOMky6hVPYnJhKUx+XMQFTQnmA3ABEKRNiACAUiVkzO+dcyIueJ/SmNqMx8Po6IyJE5A19JtHpNOmhlu0h8TLBQV37IU5kiIjOdv14IZrb2ek1azJ3JxIjUE4V/JGpn7pbkzqRmLZFnPFfGemX9QTPkYtNv9tQtFoj5qPKJqOB9RD8H5Y/5LMrvMuSSN8S5QMzvN+UovyJ6Zw9S/X9cxpjPorzqdiu8RNExv2T7qJHZ7j7Kr0ivtWYq5tk+12K89N5rrlZ+0ejvvCXb3BWmdc/BXrD6Paeyq5WMVi/ji0CEAhE2IEKBCBsQoUCEDYhQIMIGRCgQYQMiFIiwAREKRNiACAUibECEAhE2IEKBCBsQoUCEDYhQIMLGrxLx7U9zaYQIQ1OETSNEvNPPOj/duOiSnQaI8AARiPy3Ioc4fqYDTtNFYl3Jt7/b/NU2kdvd0m+3SJof2muzSLGIb2xoptZoEbIL8m5vZotELsVjLy0WIQvjNouQD11LHx/rtiGXIYD7xWM/Hx9ruz3ajr7hT1KoBje1srTdHm2H6eawvVXoz0397dy2DZ0Yru62AUiX6z/CcWPHjrmj7qkbhl1zR07HrTY7ofEKn8N589Nsq489ztvRGm8Pz9GABsF/F+jWrXUrNws5YN+zUtkuxDfmPs34uEh7PyK9RA3pBQ4AAAAAAAAAAAAR/gCE4DCAvA8+KQAAAABJRU5ErkJggg==");
        mNames.add("Phone");

        mImageUrls.add("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAAYFBMVEX///8AAAC+vr65ublKSkoyMjIHBwcPDw/BwcGKiopHR0cQEBBLS0sWFha1tbVwcHD29vbu7u6oqKhra2tSUlKUlJTKyspYWFjb29uBgYFiYmJAQEAjIyPp6emHh4fy8vI7Am+LAAAB3klEQVR4nO3X207CQBSF4R6pLShgEVBO7/+WlovGuah1IntlD8n/37uyv0yaSJZNVlTLPMmarpi+eLrW+9652nhH4X3rfPFvUnmfOl8XDUn0+xhroiHel/6VG6Q7bnvLPTfIZdg8G+55QRabYbM2HHR7kWuWlZZ7bpC8PpnO+UGMA5JaQFLr35DoP5QFBIgoIEBEAQEiCggQUUCAiAICRBQQIKKAABEFBIgoIEBEAQEiCggQUUCAiAICRBQQIKKAABEFBIgoIEBEAQEiCggQUUCAiAICRBQQIKKAABEFBIgoIEBEAQEiCggQUUCAiAICRBQQIKKAABEFBIgoIEBEAQEiCggQUUCAiAICRBQQIKKAABEFBIgoIEBEAQEiCggQUUCAiAICRBQQIKKAABEFBIgoIEBEAUk7IKkFJLWApBaQRzuvdrXlnhOk2t83L+uF2aIP5DquFo3VpAuk/5l9s9r0gCxvwe7KaNQD0oe7Vk/iAXkPd7dGox6QUjHMizzQOtx95m/kdRPs7oxGPSDhk5RWmy6Q/GNc3S+tJn0g+eF437x9Pvv/WkP14etkx+D3SHoBSa1oiNkvIE1NNKTzPnW+KhpSeJ8630s0JGu9b52rjXcMb9Il+p001S/v8Q0XUTNLHC+ydwAAAABJRU5ErkJggg==");
        mNames.add("Tablet");

        mImageUrls.add("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAAdVBMVEX///8AAAALCwsHBwfW1tYxMTFZWVk1NTXz8/Pa2to/Pz8QEBDk5OTFxcWPj49SUlLQ0NAjIyOXl5d4eHi5ubmzs7NpaWlHR0empqZgYGBubm4sLCyHh4dKSkonJyc6Ojp/f3+Tk5Pt7e2jo6MXFxfJyckbGxtqBnZGAAAEa0lEQVR4nO2d6XaqShBGCWIz0wzNTAOK+P6PeBlycsyNGgcMlbO+/UuJxto2w0eyVpWiAAAAAAAAAAAAAADwO+kjaaVJlbnO2pXch6GJMGJNne6M6Wn+9sEmsPI2FJqxdo3XMEw3qwq+tf/WXZvDdv52BrVO82g3rBEdJcePwzaR28O5eo97JTu3/QS7tnjShpmr7X+++L3pxmFVMKs8W/4JkZJ+84rPUg3Pi2qXCVd7zVoZjtmLLKyinFv1d8WfEij29y+66BWUTTqYRZUXdrFwe9909sZtgsbeMbXeFXEW7qqoyJlstsFh83gxyuNvvchmY9uHYxDUZbndbvWZ4VFZ10FwPNjqE/Ve5BUiqwARakCEGv+kyOZSACDIFDhON5yKqNOVaohkXjSEgieulC9CDXSZR14n/PdYcXo9+iJygtOLzotyqQerSdmBnrKiHUKRfyYT3SrySUpz485riyEDPRclrqMe6yZlSVSFmejN72LqIyJfopLpD0GpC712DEpcWs22vD1+bIYIU5e6lXI2R7Usdvv7c+gCItctjf3ecRzTNDVN82eGR5ppDltvTZW38GqRHwMi1IAINSBCDYhQAyLUgAg1IEINiFADItSACDUgQg2IUAMi1IAINSBCDYhQAyLUgAg1IEINiFADItSACDUgQg2IUAMi1IAINSBCDYhQAyLUgAg1IEINiFADItSACDUgQg2IUAMi1IAINSBCDYhQAyLUgAg1IEINiFADItSACDUgQg2IUAMi1IDIBQzH9HsRZ13oVW1UJDljXKapZTVTv+I/6HrTNFaaSs5ZnidFFFXVLuyyWIytmx9psfekiOH44k/PRr0+qm8LoR5q3eKsiLzwfH/JBUSMqVt4lMvmro7bT3IoLZ6MTd8vNUi/UWTsarobO4AeX9Yo82Y2R50nbfe/1vzXRZy5KWu92C6zLHYpP6YnXBLZFFz/LW1y3za1LE6f/5ONi381EKEGRKixgIh6CLbN3Eu5rbwpPcVCuG7fT/2KzZmph3Hfu66I4+wjljGeNttFYo5y/1V7arj93rZ6sTEkhqPNg0cSlj7STFxV5G3F1xYrfnIujKO5WdgmvAluWy1LEdc0p0k9cb/upJ6pk33BmuM1kUxRiq9bjw2LwthfYSjPdZx+MJLlmTWS44/bj8PE3vIoFOYd37/hCnOJEjXR3/Gp+z6rcutkhdj8ZqfiDWs79+4VMNrxqAwi8czaOcJjY012e/fHa/Hgo1uJ+8THT7+o+fhKAnnvvjjcZnZVIsuTs6a11qHIvu6rh7KRw6m5rf5eTmbEcE8/Dkgab5Avnl/zdTyune8e5Nl95DHOzlZ7DraKyNXz+mMEq4i8YCjJYRURfXkRaxWRcHmRcBURxVraI13H49NsyyXI18umprfYgaJ7i4S2x3G6RH/yT6pqU2RE5q6a8a4Y7oHuFBrv1oa0TcThE4bju++jlJI8Z5xLmc5IKTlj439Fxhw2zepcu1gAAAAAAAAAAAAAANbnP8e1Y7sWtrRnAAAAAElFTkSuQmCC");
        mNames.add("TV");

        mImageUrls.add("data:image/png;base64,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");
        mNames.add("Smart Watch");

        initRecyclerView();

    }


    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, mImageUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
