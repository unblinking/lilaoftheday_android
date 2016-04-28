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

    // onReceive is used when the alarm is triggered.
    @Override
    public void onReceive(Context context, Intent intent) {
        buildNotification(context);
    }

    private void buildNotification(Context context){

        Intent intent;
        intent = new Intent(
                context,
                MainActivity.class
        );

        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
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

