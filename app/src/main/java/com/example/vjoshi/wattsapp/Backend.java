package com.example.vjoshi.wattsapp;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.example.vjoshi.wattsapp.addDeviceClasses.DeviceConstants.*;


public class Backend {
    private static final String TAG = "Backend";

    private String username;

    private HashMap<String, Integer> deviceToIdMap;
    private HashMap<String, Double> deviceToUsageMap;

    private static final Backend INSTANCE = new Backend();
    public static Backend getInstance() {
        return INSTANCE;
    }

    private Backend() {
        deviceToIdMap = new HashMap<>();
        setDeviceToIdMap();

        deviceToUsageMap = new HashMap<>();
        setDeviceToUsageMap();
    }



    /*
        START OF BACKEND API
    */

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdForDevice(String deviceName) {
        return deviceToIdMap.get(deviceName).intValue();
    }

    public double getUsageForDevice(String deviceName) {
        return deviceToUsageMap.get(deviceName).doubleValue();
    }

    /*
        END OF BACKEND API
    */



    /*
        START OF TESTER FUNCTIONS
    */

//    public void addSampleUsers(int number) {
//        for (int i = 1; i <= number; i++) {
//            String username = "user" + i;
//            User u = new User(username, ("password" + i), 0);
//
//            for (int k = 1; k <= i; k++) {
//                Device d = new Device(i+k, "Laptop", "Apple", "MacBook Pro", 0.04);
//                u.addDevice(d);
//                u.addUsageEntry(new UsageEntry(d, new Date(), 100, 400));
//                u.addRedeemableItem(new RedeemableItem("test item", "test description", 9.98));
//            }
//
//            database.child(username).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) { onCompleteMethod(task); }
//            });
//        }
//    }

    /*
        END OF TESTER FUNCTIONS
    */



    /*
        START OF HELPER FUNCTIONS
    */

    private void setDeviceToIdMap() {
        //Apple Phones
        deviceToIdMap.put(APPLE + " " + IPHONE6, new Integer(1));
        deviceToIdMap.put(APPLE + " " + IPHONE6S, new Integer(2));
        deviceToIdMap.put(APPLE + " " + IPHONE8, new Integer(3));
        deviceToIdMap.put(APPLE + " " + IPHONE8S, new Integer(4));
        deviceToIdMap.put(APPLE + " " + IPHONEX, new Integer(5));
        deviceToIdMap.put(APPLE + " " + IPHONEXR, new Integer(6));
        deviceToIdMap.put(APPLE + " " + IPHONEXS, new Integer(7));

        //Apple Laptops
        deviceToIdMap.put(APPLE + " " + MACPRO, new Integer(9));
        deviceToIdMap.put(APPLE + " " + MACBOOKPRO13, new Integer(10));
        deviceToIdMap.put(APPLE + " " + MACBOOKPRO15, new Integer(11));
        deviceToIdMap.put(APPLE + " " + MACBOOKAIR, new Integer(12));

        //Samsung Phones
        deviceToIdMap.put(SAMSUNG +  " " + SAMSUNGS6, new Integer(13));
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGS7, new Integer(14));
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGS7EDGE, new Integer(15));
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGS8, new Integer(16));
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGS8PLUS, new Integer(17));
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGS9PLUS, new Integer(18));

        //Google Phones
        deviceToIdMap.put(GOOGLE +  " " + PIXEL, new Integer(19));
        deviceToIdMap.put(GOOGLE + " " + PIXELXL, new Integer(20));
        deviceToIdMap.put(GOOGLE + " " + PIXEL2, new Integer(21));
        deviceToIdMap.put(GOOGLE + " " + PIXEL2XL, new Integer(22));
        deviceToIdMap.put(GOOGLE + " " + PIXEL3, new Integer(23));
        deviceToIdMap.put(GOOGLE + " " + PIXEL3XL, new Integer(24));

        //Windows Phones
        deviceToIdMap.put(MICROSOFT +  " " + LUMIA550, new Integer(25));
        deviceToIdMap.put(MICROSOFT + " " + LUMIA650, new Integer(26));
        deviceToIdMap.put(MICROSOFT + " " + LUMIA950, new Integer(27));

        //Dell Laptops
        deviceToIdMap.put(DELL +  " " + XPS, new Integer(28));
        deviceToIdMap.put(DELL +  " " + INSPIRON, new Integer(29));
        deviceToIdMap.put(DELL +  " " + LATITUDE, new Integer(30));

        //Asus Laptops
        deviceToIdMap.put(ASUS +  " " + ZENBOOK, new Integer(31));
        deviceToIdMap.put(ASUS +  " " + ROG, new Integer(32));
        deviceToIdMap.put(ASUS +  " " + VIVOPRO, new Integer(33));
        deviceToIdMap.put(ASUS +  " " + QSERIES, new Integer(34));

        //HP Laptops
        deviceToIdMap.put(HP +  " " + ELITEBOOK, new Integer(35));
        deviceToIdMap.put(HP +  " " + ENVY, new Integer(36));
        deviceToIdMap.put(HP +  " " + SPECTRE, new Integer(37));
        deviceToIdMap.put(HP +  " " + PAVILION, new Integer(38));

        //Apple Desktop
        deviceToIdMap.put(APPLE + " " + IMAC, new Integer(8));

        //Dell Desktop
        deviceToIdMap.put(DELL + " " + XPS8930, new Integer(39));
        deviceToIdMap.put(DELL + " " + INSPIRON5675, new Integer(40));
        deviceToIdMap.put(DELL + " " + ALIENWARER7, new Integer(41));

        //Asus Desktop
        deviceToIdMap.put(ASUS + " " + ASUSG11, new Integer(42));
        deviceToIdMap.put(ASUS + " " + ASUSG20, new Integer(43));
        deviceToIdMap.put(ASUS + " " + ASUSGT51, new Integer(45));
        deviceToIdMap.put(ASUS + " " + ASUSVIVIO, new Integer(46));

        //HP Desktop
        deviceToIdMap.put(HP + " " + DESKTOPPAVILION, new Integer(47));
        deviceToIdMap.put(HP + " " + DESKTOPSLIMLIME, new Integer(48));
        deviceToIdMap.put(HP + " " + DESKTOPENVY, new Integer(49));
        deviceToIdMap.put(HP + " " + DESKTOPCOMPAQ, new Integer(50));
        deviceToIdMap.put(HP + " " + DESKTOPELITEDESK, new Integer(51));

        //Apple Tablets
        deviceToIdMap.put(APPLE + " " + IPAD10, new Integer(52));
        deviceToIdMap.put(APPLE + " " + IPAD12, new Integer(53));
        deviceToIdMap.put(APPLE + " " + IPADMINI, new Integer(54));

        //Microsoft Tablets
        deviceToIdMap.put(MICROSOFT + " " + MICROSOFTPRO3, new Integer(55));
        deviceToIdMap.put(MICROSOFT + " " + MICROSOFTPRO4, new Integer(56));
        deviceToIdMap.put(MICROSOFT + " " + MICROSOFTGO, new Integer(57));

        //Samsung Tablets
        deviceToIdMap.put(SAMSUNG + " " + GALAXYTAB10, new Integer(58));
        deviceToIdMap.put(SAMSUNG + " " + GALAXYBOOK2, new Integer(59));
        deviceToIdMap.put(SAMSUNG + " " + GALAXYTABA10, new Integer(60));
        deviceToIdMap.put(SAMSUNG + " " + GALAXYTABA8, new Integer(61));

        //Amazon Tablets
        deviceToIdMap.put(AMAZON + " " + AMAZONFIRE10, new Integer(62));
        deviceToIdMap.put(AMAZON + " " + AMAZONFIRE7, new Integer(63));
        deviceToIdMap.put(AMAZON + " " + AMAZONFIRE, new Integer(64));
        deviceToIdMap.put(AMAZON + " " + AMAZONKINDLE, new Integer(65));

        //Lenovo Tablets
        deviceToIdMap.put(LENOVO + " " + LENOVOTAB8, new Integer(66));
        deviceToIdMap.put(LENOVO + " " + LENOVOTAB10, new Integer(67));
        deviceToIdMap.put(LENOVO + " " + LENOVOYOGA, new Integer(68));

        //Samsung TVs
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGQ7F55, new Integer(69));
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGQ7F75, new Integer(70));
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGQ6F55, new Integer(71));
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGQ6F75, new Integer(72));

        //LG TVs
        deviceToIdMap.put(LG + " " + LGC7P, new Integer(73));
        deviceToIdMap.put(LG + " " + LGE7P, new Integer(74));
        deviceToIdMap.put(LG + " " + LGC8, new Integer(75));

        //Toshiba TVs
        deviceToIdMap.put(TOSHIBA + " " + TOSHIBAL9300, new Integer(76));
        deviceToIdMap.put(TOSHIBA + " " + TOSHIBAL7350, new Integer(77));
        deviceToIdMap.put(TOSHIBA + " " + TOSHIBAL7300, new Integer(78));

        //TCL TVs
        deviceToIdMap.put(TCL + " " + TCL6SERIES, new Integer(79));
        deviceToIdMap.put(TCL + " " + TCLR615, new Integer(80));
        deviceToIdMap.put(TCL + " " + TCLS405, new Integer(81));

        //Sony TVs
        deviceToIdMap.put(SONY + " " + SONYX900F, new Integer(82));
        deviceToIdMap.put(SONY + " " + SONYKDA1, new Integer(83));
        deviceToIdMap.put(SONY + " " + SONYX900E, new Integer(84));

        //Vizio TVs
        deviceToIdMap.put(VIZIO + " " + VIZIODE0, new Integer(85));
        deviceToIdMap.put(VIZIO + " " + VIZIOMC1, new Integer(86));

        //Fitbits
        deviceToIdMap.put(FITBIT + " " + FITBITCHARGE2, new Integer(87));
        deviceToIdMap.put(FITBIT + " " + FITBITCHARGE3, new Integer(88));
        deviceToIdMap.put(FITBIT + " " + FITBITVERSA, new Integer(89));

        //Samsung Smartwatch
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGGEARS2, new Integer(90));
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGGEARS3, new Integer(91));
        deviceToIdMap.put(SAMSUNG + " " + SAMSUNGGEARS, new Integer(92));

        //Apple Smart Watch
        deviceToIdMap.put(APPLE + " " + APPLEWATCH, new Integer(93));
        deviceToIdMap.put(APPLE + " " + APPLEWATCH2, new Integer(94));
        deviceToIdMap.put(APPLE + " " + APPLEWATCH3, new Integer(95));
    }


    private void setDeviceToUsageMap() {
        deviceToUsageMap.put(APPLE + " " + IPHONE6, new Double(3));
        deviceToUsageMap.put(APPLE + " " + IPHONE6S, new Double(3.5));
        deviceToUsageMap.put(APPLE + " " + IPHONE8, new Double(5));
        deviceToUsageMap.put(APPLE + " " + IPHONE8S, new Double(5.5));
        deviceToUsageMap.put(APPLE + " " + IPHONEX, new Double(8));
        deviceToUsageMap.put(APPLE + " " + IPHONEXR, new Double(9));
        deviceToUsageMap.put(APPLE + " " + IPHONEXS, new Double(8));

        deviceToUsageMap.put(APPLE + " " + IMAC, new Double(30));
        deviceToUsageMap.put(APPLE + " " + MACPRO, new Double(40));
        deviceToUsageMap.put(APPLE + " " + MACBOOKPRO13, new Double(20));
        deviceToUsageMap.put(APPLE + " " + MACBOOKPRO15, new Double(25));
        deviceToUsageMap.put(APPLE + " " + MACBOOKAIR, new Double(15));


        //Samsung Phones
        deviceToUsageMap.put(SAMSUNG +  " " + SAMSUNGS6, new Double(13));
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGS7, new Double(14));
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGS7EDGE, new Double(15));
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGS8, new Double(16));
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGS8PLUS, new Double(17));
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGS9PLUS, new Double(18));

        //Google Phones
        deviceToUsageMap.put(GOOGLE +  " " + PIXEL, new Double(19));
        deviceToUsageMap.put(GOOGLE + " " + PIXELXL, new Double(20));
        deviceToUsageMap.put(GOOGLE + " " + PIXEL2, new Double(21));
        deviceToUsageMap.put(GOOGLE + " " + PIXEL2XL, new Double(22));
        deviceToUsageMap.put(GOOGLE + " " + PIXEL3, new Double(23));
        deviceToUsageMap.put(GOOGLE + " " + PIXEL3XL, new Double(24));

        //Windows Phones
        deviceToUsageMap.put(MICROSOFT +  " " + LUMIA550, new Double(25));
        deviceToUsageMap.put(MICROSOFT + " " + LUMIA650, new Double(26));
        deviceToUsageMap.put(MICROSOFT + " " + LUMIA950, new Double(27));

        //Dell Laptops
        deviceToUsageMap.put(DELL +  " " + XPS, new Double(28));
        deviceToUsageMap.put(DELL +  " " + INSPIRON, new Double(29));
        deviceToUsageMap.put(DELL +  " " + LATITUDE, new Double(30));

        //Asus Laptops
        deviceToUsageMap.put(ASUS +  " " + ZENBOOK, new Double(31));
        deviceToUsageMap.put(ASUS +  " " + ROG, new Double(32));
        deviceToUsageMap.put(ASUS +  " " + VIVOPRO, new Double(33));
        deviceToUsageMap.put(ASUS +  " " + QSERIES, new Double(34));

        //HP Laptops
        deviceToUsageMap.put(HP +  " " + ELITEBOOK, new Double(35));
        deviceToUsageMap.put(HP +  " " + ENVY, new Double(36));
        deviceToUsageMap.put(HP +  " " + SPECTRE, new Double(37));
        deviceToUsageMap.put(HP +  " " + PAVILION, new Double(38));

        //Dell Desktop
        deviceToUsageMap.put(DELL + " " + XPS8930, new Double(39));
        deviceToUsageMap.put(DELL + " " + INSPIRON5675, new Double(40));
        deviceToUsageMap.put(DELL + " " + ALIENWARER7, new Double(41));

        //Asus Desktop
        deviceToUsageMap.put(ASUS + " " + ASUSG11, new Double(42));
        deviceToUsageMap.put(ASUS + " " + ASUSG20, new Double(43));
        deviceToUsageMap.put(ASUS + " " + ASUSGT51, new Double(45));
        deviceToUsageMap.put(ASUS + " " + ASUSVIVIO, new Double(46));

        //HP Desktop
        deviceToUsageMap.put(HP + " " + DESKTOPPAVILION, new Double(47));
        deviceToUsageMap.put(HP + " " + DESKTOPSLIMLIME, new Double(48));
        deviceToUsageMap.put(HP + " " + DESKTOPENVY, new Double(49));
        deviceToUsageMap.put(HP + " " + DESKTOPCOMPAQ, new Double(50));
        deviceToUsageMap.put(HP + " " + DESKTOPELITEDESK, new Double(51));

        //Apple Tablets
        deviceToUsageMap.put(APPLE + " " + IPAD10, new Double(52));
        deviceToUsageMap.put(APPLE + " " + IPAD12, new Double(53));
        deviceToUsageMap.put(APPLE + " " + IPADMINI, new Double(54));

        //Microsoft Tablets
        deviceToUsageMap.put(MICROSOFT + " " + MICROSOFTPRO3, new Double(55));
        deviceToUsageMap.put(MICROSOFT + " " + MICROSOFTPRO4, new Double(56));
        deviceToUsageMap.put(MICROSOFT + " " + MICROSOFTGO, new Double(57));

        //Samsung Tablets
        deviceToUsageMap.put(SAMSUNG + " " + GALAXYTAB10, new Double(58));
        deviceToUsageMap.put(SAMSUNG + " " + GALAXYBOOK2, new Double(59));
        deviceToUsageMap.put(SAMSUNG + " " + GALAXYTABA10, new Double(60));
        deviceToUsageMap.put(SAMSUNG + " " + GALAXYTABA8, new Double(61));

        //Amazon Tablets
        deviceToUsageMap.put(AMAZON + " " + AMAZONFIRE10, new Double(62));
        deviceToUsageMap.put(AMAZON + " " + AMAZONFIRE7, new Double(63));
        deviceToUsageMap.put(AMAZON + " " + AMAZONFIRE, new Double(64));
        deviceToUsageMap.put(AMAZON + " " + AMAZONKINDLE, new Double(65));

        //Lenovo Tablets
        deviceToUsageMap.put(LENOVO + " " + LENOVOTAB8, new Double(66));
        deviceToUsageMap.put(LENOVO + " " + LENOVOTAB10, new Double(67));
        deviceToUsageMap.put(LENOVO + " " + LENOVOYOGA, new Double(68));

        //Samsung TVs
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGQ7F55, new Double(69));
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGQ7F75, new Double(70));
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGQ6F55, new Double(71));
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGQ6F75, new Double(72));

        //LG TVs
        deviceToUsageMap.put(LG + " " + LGC7P, new Double(73));
        deviceToUsageMap.put(LG + " " + LGE7P, new Double(74));
        deviceToUsageMap.put(LG + " " + LGC8, new Double(75));

        //Toshiba TVs
        deviceToUsageMap.put(TOSHIBA + " " + TOSHIBAL9300, new Double(76));
        deviceToUsageMap.put(TOSHIBA + " " + TOSHIBAL7350, new Double(77));
        deviceToUsageMap.put(TOSHIBA + " " + TOSHIBAL7300, new Double(78));

        //TCL TVs
        deviceToUsageMap.put(TCL + " " + TCL6SERIES, new Double(79));
        deviceToUsageMap.put(TCL + " " + TCLR615, new Double(80));
        deviceToUsageMap.put(TCL + " " + TCLS405, new Double(81));

        //Sony TVs
        deviceToUsageMap.put(SONY + " " + SONYX900F, new Double(82));
        deviceToUsageMap.put(SONY + " " + SONYKDA1, new Double(83));
        deviceToUsageMap.put(SONY + " " + SONYX900E, new Double(84));

        //Vizio TVs
        deviceToUsageMap.put(VIZIO + " " + VIZIODE0, new Double(85));
        deviceToUsageMap.put(VIZIO + " " + VIZIOMC1, new Double(86));

        //Fitbits
        deviceToUsageMap.put(FITBIT + " " + FITBITCHARGE2, new Double(87));
        deviceToUsageMap.put(FITBIT + " " + FITBITCHARGE3, new Double(88));
        deviceToUsageMap.put(FITBIT + " " + FITBITVERSA, new Double(89));

        //Samsung Smartwatch
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGGEARS2, new Double(90));
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGGEARS3, new Double(91));
        deviceToUsageMap.put(SAMSUNG + " " + SAMSUNGGEARS, new Double(92));

        //Apple Smart Watch
        deviceToUsageMap.put(APPLE + " " + APPLEWATCH, new Double(93));
        deviceToUsageMap.put(APPLE + " " + APPLEWATCH2, new Double(94));
        deviceToUsageMap.put(APPLE + " " + APPLEWATCH3, new Double(95));
    }

    /*
        END OF HELPER FUNCTIONS
    */
}
