package com.lilaoftheday.lilaoftheday;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmCanceler {

    /*
    Cancel a scheduled alarm.
    */
    public void cancelAlarm(Context context) {

        /*
        Create an intent and set the class to be executed when we check for an alarm.
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

        /* Remove any alarms with a matching Intent. */
        alarmManager.cancel(pendingIntent);
        /* Cancel a currently active PendingIntent. */
        pendingIntent.cancel();

        Toast.makeText(
                context, // The context to use. Usually your Application or Activity object.
                "The alarm for Lila notifications is now off.", // The resource id of the string resource to use. Can be formatted text.
                Toast.LENGTH_SHORT // How long to display the message. Either LENGTH_SHORT or LENGTH_LONG
        ).show(); // Show the view for the specified duration.

    }

}

