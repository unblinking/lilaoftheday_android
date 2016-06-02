package com.lilaoftheday.lilaoftheday;


import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

// When the device reboots, reschedule the alarm if necessary
public class AlarmBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        /*Log.d("LilaOfTheDay","AlarmBootReceiver onReceive");*/

        // First figure out if the preference for notifications is selected or not,
        // and if the alarm for those notifications is scheduled or not.

        SharedPreferences sharedPref;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        // If the notification preference is set to true then notifyPref is true.
        int preferenceResource = R.string.preference_notifications_checkbox_key;
        String preferenceKey = context.getResources().getString(preferenceResource);
        boolean notifyPref = sharedPref.getBoolean(preferenceKey, false);

        Intent alarmReceiverIntent;
        alarmReceiverIntent = new Intent(
                context,
                AlarmReceiver.class
        );
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(
                context,
                1,
                alarmReceiverIntent,
                PendingIntent.FLAG_NO_CREATE
                /*
                FLAG_NO_CREATE - If the described PendingIntent does not already exists, then simply
                return null. We don't want to create a PendingIntent when we are only checking to
                see if it already exists.
                */
        );
        // If the pending intent exists (is not null) then alarmSet is true.
        boolean alarmSet = (pendingIntent != null);

        // Now, reschedule or cancel the alarm if necessary

        if (notifyPref && !alarmSet) { // If preference is checked but no alarm is set
            // Reschedule the alarm for the notification
            AlarmScheduler alarmScheduler;
            alarmScheduler = new AlarmScheduler();
            alarmScheduler.scheduleAlarm(context);

        }
        if (notifyPref && alarmSet) { // If preference is checked and alarm is set
            // Do nothing, everything is as it should be
        }
        if (!notifyPref && !alarmSet) { // If preference is unchecked and alarm is not set
            // Do nothing, everything is as it should be
        }
        if (!notifyPref && alarmSet) { // If preference is unchecked and alarm is set
            // Cancel the alarm for the notification, why is it set if preference is not checked?
            AlarmCanceler alarmCanceler;
            alarmCanceler = new AlarmCanceler();
            alarmCanceler.cancelAlarm(context);
        }

    }

}

