package com.lilaoftheday.lilaoftheday;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmScheduler {

    public void scheduleAlarm(Context context) {

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
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        // AlarmManager provides access to the system alarm services.
        AlarmManager alarmManager;
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        /*
        Setup the alarm type, trigger time, repeating interval time, and pending intent. Here we are
        using ELAPSED_REALTIME which fires the pending intent based on the amount of time since the
        device was booted, but doesn't wake up the device. The elapsed time includes any time during
        which the device was asleep.
        */

        alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME, // int type
                AlarmManager.INTERVAL_DAY, // long triggerAtMillis
                AlarmManager.INTERVAL_DAY, // long intervalMillis
                pendingIntent
        );

        // TESTING section for a quick notification (without waiting a full day like normal)
        /*alarmManager.set(
                AlarmManager.RTC, // int type
                System.currentTimeMillis(), // long triggerAtMillis
                pendingIntent
        );*/

        // Notify the operator with a toast message.
        /*Toast.makeText(
                context,
                R.string.notification_alarm_scheduled,
                Toast.LENGTH_LONG
        ).show();*/

    }

}

