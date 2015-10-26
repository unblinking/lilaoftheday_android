package com.lilaoftheday.lilaoftheday;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmScheduler {

    /*
    Schedule an alarm.
    This alarm happens to be a repeating alarm, used to trigger a daily notification.
    */
    public void scheduleAlarm(Context context) {

        /*
        Create an intent and set the class to be executed when the alarm triggers.
        An intent is an abstract description of an operation to be performed.
        */
        Intent intent;
        intent = new Intent(
                context, // App context
                AlarmReceiver.class // Class to be run
        );

        /* PendingIntent: A description of an Intent and target action to perform with it. */
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(
                context, // The Context in which this PendingIntent should perform the broadcast.
                1, // Private request code for the sender
                intent, // The Intent to be broadcast.
                PendingIntent.FLAG_UPDATE_CURRENT // May be FLAG_ONE_SHOT, FLAG_NO_CREATE, FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT, or any of the flags as supported by Intent.fillIn() to control which unspecified parts of the intent that can be supplied when the actual send happens.
        );


        /* AlarmManager provides access to the system alarm services. */
        AlarmManager alarmManager;
        alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        /*
        Setup the alarm type, trigger time, repeating interval time, and pending intent. Here we are
        using ELAPSED_REALTIME which fires the pending intent based on the amount of time since the
        device was booted, but doesn't wake up the device. The elapsed time includes any time during
        which the device was asleep.
        */
        alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME, // int type
                System.currentTimeMillis() + 1000, // long triggerAtMillis
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, // long intervalMillis
                pendingIntent // PendingIntent operation
        );

        /*Toast.makeText(
                context,
                "The alarm for notifications is now on.",
                Toast.LENGTH_SHORT
        ).show();*/

    }
}

