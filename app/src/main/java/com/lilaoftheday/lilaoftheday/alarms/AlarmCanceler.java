package com.lilaoftheday.lilaoftheday.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmCanceler {

    public void cancelAlarm(Context context) {

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

        // Cancel alarm with matching intent.
        alarmManager.cancel(pendingIntent);
        // Cancel a currently active PendingIntent.
        pendingIntent.cancel();

        // Notify the operator with a toast message.
        /*Toast.makeText(
                context,
                R.string.notification_alarm_canceled,
                Toast.LENGTH_LONG
        ).show();*/

    }

}

