package com.example.vjoshi.wattsapp.addDeviceClasses;

public interface DeviceConstants {
    String TAG = "TAG";
    String DEVICENAME = "device";
    String INDEX = "index";
    String COMPANYNAME = "company";
    String MODELNAME = "model";

    //Devices
    String LAPTOP = "Laptop";
    String DESKTOP = "Desktop";
    String PHONE = "Phone";
    String TABLET = "Tablet";
    String TELEVISION = "TV";
    String WATCH = "Smart Watch";
    String[] deviceArray = {LAPTOP, DESKTOP, PHONE, TABLET, TELEVISION, WATCH};

    //Companies
    String APPLE = "Apple";
    String DELL = "Dell";
    String WINDOWS = "Windows";
    String ASUS = "Asus";
    String HP = "HP";
    String GOOGLE = "Google";
    String NOKIA = "Nokia";
    String HUAWEI = "Huawei";
    String SAMSUNG = "Samsung";
    String AMAZON = "Amazon";
    String LENOVO = "Lenovo";
    String LG = "LG";
    String TOSHIBA = "Toshiba";
    String TCL = "TCL";
    String SONY = "Sony";
    String VIZIO = "Vizio";
    String FITBIT = "Fitbit";
    String[] companieArray = {APPLE, DELL, WINDOWS, ASUS, HP, GOOGLE, NOKIA, HUAWEI,
                                SAMSUNG, AMAZON, LENOVO, LG, TOSHIBA, TCL, SONY, VIZIO,FITBIT};


    //Apple Phones
    String IPHONE6 = "iPhone 6";
    String IPHONE6S = "iPhone 6s";
    String IPHONE8 = "iPhone 8";
    String IPHONE8S = "iPhone 8s";
    String IPHONEX = "iPhone X";
    String IPHONEXS = "iPhone XS";
    String IPHONEXR = "iPhone XR";

    //Samsung Phones
    String SAMSUNGS6 = "Samsung S6";
    String SAMSUNGS7 = "Samsung S7";
    String SAMSUNGS7EDGE = "Samsung S7EDGE";
    String SAMSUNGS8 = "Samsung S8";
    String SAMSUNGS8PLUS = "Samsung S8+";
    String SAMSUNGS9PLUS = "Samsung S9+";


    //Google Phones
    String PIXEL = "Pixel";
    String PIXELXL = "Pixel XL";
    String PIXEL2 = "Pixel 2";
    String PIXEL2XL = "Pixel 2XL";
    String PIXEL3 = "Pixel 3";
    String PIXEL3XL = "Pixel 3XL";

    //Windows Phones
    String LUMIA550 = "Lumia 550";
    String LUMIA650 = "Lumia 650";
    String LUMIA950 = "Lumia 950";



    //Apple Laptops
    String IMAC = "iMac";
    String MACPRO = "Mac Pro";
    String MACBOOKPRO13 = "Macbook Pro 13'";
    String MACBOOKPRO15 = "Macbook Pro 15'";
    String MACBOOKAIR = "Macbook Air";

    //String Dell Laptops
    String XPS = "XPS";
    String INSPIRON = "Inspiron";
    String LATITUDE = "Latitude";

    //Windows Laptops
    String ZENBOOK = "Zenbook Series";
    String ROG = "Republic of Gamers";
    String VIVOPRO = "VivoBook Pro";
    String QSERIES = "Q Series";

    //HP Laptop
    String ELITEBOOK = "Elite Book";
    String ENVY = "Envy";
    String SPECTRE = "Specture";
    String PAVILION = "Pavilion";


    //URLS
    String APPLEURL = "https://cdn3.iconfinder.com/data/icons/picons-social/57/16-apple-512.png";
    String DELLURL = "https://cdn2.iconfinder.com/data/icons/metro-uinvert-dock/256/Dell_alt.png";
    String WINDOWSURL = "https://cdn3.iconfinder.com/data/icons/flat-icons-web/40/Windows-512.png";
    String ASUSURL = "https://cdn2.iconfinder.com/data/icons/metro-uinvert-dock/256/Asus.png";
    String HPURL = "https://cdn2.iconfinder.com/data/icons/metro-uinvert-dock/256/HP.png";
    String GOOGLEURL = "https://cdn0.iconfinder.com/data/icons/social-network-7/50/2-512.png";
    String NOKIAURL = "https://cdn4.iconfinder.com/data/icons/flat-brand-logo-2/512/nokia-512.png";
    String HUAWEIURL = "data:image/png;base64,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";
    String SAMSUNGURL = "https://cdn4.iconfinder.com/data/icons/flat-brand-logo-2/512/samsung-128.png";
    String AMAZONURL = "https://cdn2.iconfinder.com/data/icons/social-icons-color/512/amazon-512.png";
    String LENOVOURL = "https://cdn4.iconfinder.com/data/icons/flat-brand-logo-2/512/lenovo-512.png";
    String LGURL = "https://cdn4.iconfinder.com/data/icons/flat-brand-logo-2/512/lg-512.png";
    String TOSHIBAURL = "http://iconagency.ru/en/i/brands/20/res_Toshiba.jpg";
    String TCLURL = "https://pbs.twimg.com/profile_images/751669083901353984/MU6THdUV_400x400.jpg";
    String SONYURL = "https://cdn4.iconfinder.com/data/icons/flat-brand-logo-2/512/sony-512.png";
    String VIZIOURL = "http://propresenters.com/main/wp-content/uploads/2017/08/vizio.jpg";
    String FITBITURL = "Fithttps://cdn-the5krunner-com.r.worldssl.net/wp-content/uploads/2017/11/1452480358_fitbit1.pngbit";

}
