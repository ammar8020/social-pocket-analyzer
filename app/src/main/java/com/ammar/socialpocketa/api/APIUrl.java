package com.ammar.socialpocketa.api;

import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static android.content.Context.WIFI_SERVICE;

public class APIUrl {


//    InetAddress ip;
//    String hostname;
//    try {
//    ip = InetAddress.getLocalHost();
//    hostname = ip.getHostName();
//    System.out.println("Your current IP address : " + ip);
//    System.out.println("Your current Hostname : " + hostname);
//
//    } catch(UnknownHostException e) {
//
//        e.printStackTrace();
//    }

//    WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
//    String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());


    public static final String BASE_URL = "http://192.168.1.7:4000/";

}