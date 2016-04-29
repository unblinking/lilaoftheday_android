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
                return null. We don't want to create a PendingIntent when we are only checking to
                see if it already exists.
                */
        );

        // If the pending intent exists (is not null) then alarmSet is true.
        boolean alarmSet = (pendingIntent != null);

        // Let the user know if alarmSet is true or not (the alarm is set or not).
        if (alarmSet) {
            Toast.makeText(
                    context,
                    R.string.notification_alarm_scheduled,
                    Toast.LENGTH_LONG
            ).show();
        }
        else {
            Toast.makeText(
                    context,
                    R.string.notification_alarm_canceled,
                    Toast.LENGTH_LONG
            ).show();
        }

        SharedPreferences sharedPref;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        // If the notification preference is set to true then notifyPref is true.
        String preferenceKey = context.getResources().getString(R.string.preference_notifications_checkbox_key);
        boolean notifyPref = sharedPref.getBoolean(preferenceKey, false);

        // Let the user know if notifyPref is true or not (the notification preference is enabled or not).
        if (notifyPref) {
            Toast.makeText(
                    context,
                    R.string.notification_preference_true,
                    Toast.LENGTH_LONG
            ).show();
        }
        else {
            Toast.makeText(
                    context,
                    R.string.notification_preference_false,
                    Toast.LENGTH_LONG
            ).show();
        }

    }

}

