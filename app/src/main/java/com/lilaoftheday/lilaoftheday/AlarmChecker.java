package com.lilaoftheday.lilaoftheday;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class AlarmChecker {

    public void checkAlarm(Context context) {

        Intent intent;
        intent = new Intent(
                context,
                AlarmReceiver.class
        );


        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(
                context,
                1,
                intent,
                PendingIntent.FLAG_NO_CREATE
                /*
                FLAG_NO_CREATE - If the described PendingIntent does not already exists, then simply
                return null. We don't want to create a PendingIntent when we are only checking to see if
                it already exists.
                */
        );

        // If the pending intent exists (is not null) then alarmSet is true.
        boolean alarmSet = (pendingIntent != null);

        // Let the user know if alarmSet is true or not (the alarm is set or not).
        if (alarmSet) {
            Toast.makeText(
                    context,
                    "The system alarm for Lila notifications is set.",
                    Toast.LENGTH_SHORT
            ).show();
        }
        else {
            Toast.makeText(
                    context,
                    "The system alarm for Lila notifications is not set.",
                    Toast.LENGTH_SHORT
            ).show();
        }

        SharedPreferences sharedPref;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        // If the notification preference is set to true then notifyPref is true.
        boolean notifyPref = sharedPref.getBoolean("receive_daily_notifications", false);

        // Let the user know if notifyPref is true or not (the notification preference is enabled or not).
        if (notifyPref) {
            Toast.makeText(
                    context,
                    "The user preference for Lila notifications is true.",
                    Toast.LENGTH_SHORT
            ).show();
        }
        else {
            Toast.makeText(
                    context,
                    "The user preference for Lila notifications is false.",
                    Toast.LENGTH_SHORT
            ).show();
        }

    }

}

