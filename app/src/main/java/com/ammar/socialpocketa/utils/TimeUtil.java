package com.ammar.socialpocketa.utils;


import android.text.format.DateUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimeUtil {

    private static String TAG = "TimeUtil";

    public static CharSequence getTimeAgo(String dateAndTime) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        long time = 0;

        try {
             time = sdf.parse(dateAndTime).getTime();
        } catch (ParseException ex) {
            Log.d(TAG, "getTimeAgo: Parse Exception Occured.");
        }

        long now = System.currentTimeMillis();

        CharSequence ago =
                DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);


        return ago;
    }


}
