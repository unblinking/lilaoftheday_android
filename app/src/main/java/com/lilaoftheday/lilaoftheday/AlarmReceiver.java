package com.lilaoftheday.lilaoftheday;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    /* Take this action when the alarm is triggered. */
    @Override
    public void onReceive(Context context, Intent intent) {
        buildNotification(context);
    }

    /* Here we build our notification */
    private void buildNotification(Context context){

        /*
        Create an intent and set the class to be executed when we check for an alarm.
        An intent is an abstract description of an operation to be performed.
        */
        Intent intent;
        intent = new Intent(
                context, // App context
                MainActivity.class // Class to be run
        );

        /* PendingIntent: A description of an Intent and target action to perform with it. */
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(
                context, // The Context in which this PendingIntent should perform the broadcast.
                1, // Private request code for the sender
                intent, // The Intent to be broadcast.
                PendingIntent.FLAG_UPDATE_CURRENT // May be FLAG_ONE_SHOT, FLAG_NO_CREATE, FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT, or any of the flags as supported by Intent.fillIn() to control which unspecified parts of the intent that can be supplied when the actual send happens.
        );

        NotificationCompat.Builder ncb;
        ncb = new NotificationCompat.Builder(context);

        int smallIcon;
        smallIcon = R.drawable.icon_small;

        Bitmap largeIcon;
        largeIcon = BitmapFactory.decodeResource(
                context.getResources(),
                R.mipmap.ic_launcher
        );

        String contentTitle;
        contentTitle = context.getString(
                R.string.notification_content_title
        );

        String contentText;
        contentText = context.getString(
                R.string.notification_content_text
        );

        ncb.setWhen(System.currentTimeMillis())
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setTicker(contentText)
                .setSmallIcon(smallIcon)
                .setLargeIcon(largeIcon)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager;
        notificationManager = (NotificationManager)context
                .getSystemService(
                        Context.NOTIFICATION_SERVICE
                );
        notificationManager.notify(
                1,
                ncb.build()
        );

    }

}

