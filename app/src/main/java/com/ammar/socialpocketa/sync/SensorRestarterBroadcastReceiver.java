package com.ammar.socialpocketa.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ammar.socialpocketa.RetrofitClient;
import com.ammar.socialpocketa.data.SharedPrefManager;


public class SensorRestarterBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "SensorRestarterBR";

    String token = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(SensorRestarterBroadcastReceiver.class.getSimpleName(), "Service Stops! Oooooooooooooppppssssss!!!!");

//        SharedPreferences sharedPreferences = context.getSharedPreferences("usersharedpref", Context.MODE_PRIVATE);



//        token = SharedPrefManager.getKeyToken();
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        String authToken4 = sharedPrefManager.getKeyToken2();

        RetrofitClient.getToken(authToken4);


        String authToken = intent.getExtras().toString();
//        String authToken2 = intent.getExtras().getBundle("authToken").toString();
        String authToken3 = intent.getStringExtra("authToken");

        Log.d(TAG, "onReceive: authToken at BR: " + authToken);

//        Log.d(TAG, "onReceive: authToken2 at BR: " + authToken2);

        Log.d(TAG, "onReceive: authToken3 at BR: " + authToken3);

        Log.d(TAG, "onReceive: authToken4 at BR: " + authToken4);

        context.startService(new Intent(context, SensorService.class));
    }










}
