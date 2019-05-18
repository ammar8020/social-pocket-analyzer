package com.ammar.socialpocketa.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.ammar.socialpocketa.RetrofitClient;
import com.ammar.socialpocketa.data.SharedPrefManager;


public class MentionRestarterBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "SensorRestarterBR";

    String token = "";

    private static final String SHARED_PREF_NAME = "usersharedpref";

    private static final String KEY_USER_ID2 = "keyuserid2";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(MentionRestarterBroadcastReceiver.class.getSimpleName(), "Service Stops! Oooooooooooooppppssssss!!!!");

//        SharedPreferences sharedPreferences = context.getSharedPreferences("usersharedpref", Context.MODE_PRIVATE);



//        token = SharedPrefManager.getKeyToken();
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        String authToken4 = sharedPrefManager.getKeyToken2();

        RetrofitClient.getToken(authToken4);

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String userId = sharedPreferences.getString(KEY_USER_ID2, "");

        MentionService.setUserId(userId);


        String authToken = intent.getExtras().toString();
//        String authToken2 = intent.getExtras().getBundle("authToken").toString();
        String authToken3 = intent.getStringExtra("authToken");

//        Log.d(TAG, "onReceive: authToken at BR: " + authToken);

//        Log.d(TAG, "onReceive: authToken2 at BR: " + authToken2);

//        Log.d(TAG, "onReceive: authToken3 at BR: " + authToken3);

        Log.d(TAG, "onReceive: authToken4 at BR: " + authToken4);

        Log.d(TAG, "onReceive: userID " + userId );

        context.startService(new Intent(context, MentionService.class));
    }










}
