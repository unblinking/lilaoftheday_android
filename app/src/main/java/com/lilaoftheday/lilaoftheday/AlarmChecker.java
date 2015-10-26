package com.lilaoftheday.lilaoftheday;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class AlarmChecker {

    /*
    Check to see if an alarm is currently scheduled.
    */
    public void checkAlarm(Context context) {

        /*
        Create an intent and set the class to be executed when we check for an alarm.
        An intent is an abstract description of an operation to be performed.
        */
        Intent intent;
        intent = new Intent(
                context, // App context
                AlarmReceiver.class // Class to be run
        );

        /*
        PendingIntent: A description of an Intent and target action to perform with it.
        FLAG_NO_CREATE - If the described PendingIntent does not already exists, then simply
        return null. We don't want to create a PendingIntent when we are only checking-in on things.
        */
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(
                context, // The Context in which this PendingIntent should perform the broadcast.
                1, // Private request code for the sender
                intent, // The Intent to be broadcast.
                PendingIntent.FLAG_NO_CREATE // May be FLAG_ONE_SHOT, FLAG_NO_CREATE, FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT, or any of the flags as supported by Intent.fillIn() to control which unspecified parts of the intent that can be supplied when the actual send happens.
        );

        /* If the pending intent exists (is not null) then alarmSet is true. */
        boolean alarmSet = (pendingIntent != null);
        /* Let the user know if alarmSet is true or not (the alarm is set or not). */
        if (alarmSet) {
            Toast.makeText(
                    context, // The context to use. Usually your Application or Activity object.
                    "The system alarm for Lila notifications is set.", // The resource id of the string resource to use. Can be formatted text.
                    Toast.LENGTH_SHORT // How long to display the message. Either LENGTH_SHORT or LENGTH_LONG
            ).show(); // Show the view for the specified duration.
        }
        else {
            Toast.makeText(
                    context, // The context to use. Usually your Application or Activity object.
                    "The system alarm for Lila notifications is not set.", // The resource id of the string resource to use. Can be formatted text.
                    Toast.LENGTH_SHORT // How long to display the message. Either LENGTH_SHORT or LENGTH_LONG
            ).show(); // Show the view for the specified duration.
        }


        SharedPreferences sharedPref;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context); // Gets a SharedPreferences instance that points to the default file that is used by the preference framework in the given context.
        // If the notification preference is set to true then notifyPref is true.
        boolean notifyPref = sharedPref.getBoolean("receive_daily_notifications", false);
        // Let the user know if notifyPref is true or not (the notification preference is enabled or not).
        if (notifyPref) {
            Toast.makeText(
                    context, // The context to use. Usually your Application or Activity object.
                    "The user preference for Lila notifications is true.", // The resource id of the string resource to use. Can be formatted text.
                    Toast.LENGTH_SHORT // How long to display the message. Either LENGTH_SHORT or LENGTH_LONG
            ).show(); // Show the view for the specified duration.
        }
        else {
            Toast.makeText(
                    context, // The context to use. Usually your Application or Activity object.
                    "The user preference for Lila notifications is false.", // The resource id of the string resource to use. Can be formatted text.
                    Toast.LENGTH_SHORT // How long to display the message. Either LENGTH_SHORT or LENGTH_LONG
            ).show(); // Show the view for the specified duration.
        }

    }

}

