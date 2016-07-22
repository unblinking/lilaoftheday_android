package com.lilaoftheday.lilaoftheday.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmScheduler {

    /**
     * Schedules a daily alarm for a notification.
     *
     * @param context Interface to global information about an application environment.
     * @see android.app.AlarmManager#setInexactRepeating(int, long, long, PendingIntent)
     */
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

        AlarmManager alarmManager;
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        /**
         * Setup the alarm type, trigger time, repeating interval time, and pending intent.
         *
         * Here we are using ELAPSED_REALTIME which fires the pending intent based on the amount of
         * time since the device was booted, but doesn't wake up the device. The elapsed time
         * includes any time during which the device was asleep.
         *
         * @see android.app.AlarmManager#setInexactRepeating(int, long, long, PendingIntent)
         */
        alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME, // int type
                AlarmManager.INTERVAL_DAY, // long triggerAtMillis
                AlarmManager.INTERVAL_DAY, // long intervalMillis
                pendingIntent
        );

        /**
         * Used for testing or debugging the alarm.
         *
         * Commented out until needed during testing. This  {@link  alarmManager} setting will
         * generate a notification quickly, without waiting a full day like normal.
         *
         * @see android.app.AlarmManager#set(int, long, PendingIntent)
         *
         */
        /*alarmManager.set(
                AlarmManager.RTC, // int type
                System.currentTimeMillis(), // long triggerAtMillis
                pendingIntent
        );*/

    }

}

