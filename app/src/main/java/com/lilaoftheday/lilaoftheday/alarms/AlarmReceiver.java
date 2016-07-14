package com.lilaoftheday.lilaoftheday.alarms;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.activities.MainActivity;

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
        pendingIntent = PendingIntent.getActivity(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );


        int smallIcon;
        smallIcon = R.drawable.icon_small;

        Bitmap largeIcon;
        largeIcon = BitmapFactory.decodeResource(
                context.getResources(),
                R.mipmap.ic_launcher
        );

        Bitmap largeIcon2;
        largeIcon2 = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.lila_under_leaf
        );

        String contentTitle;
        contentTitle = context.getString(
                R.string.notification_content_title
        );

        String contentText;
        contentText = context.getString(
                R.string.notification_content_text
        );

        android.support.v4.app.NotificationCompat.BigTextStyle contentTextBig;
        contentTextBig = new android.support.v4.app.NotificationCompat.BigTextStyle();
        contentTextBig.bigText(contentText);

        NotificationCompat.Builder ncb;
        ncb = new NotificationCompat.Builder(context);

        ncb.setWhen(System.currentTimeMillis());
        ncb.setContentTitle(contentTitle);
        ncb.setContentText(contentText);
        ncb.setTicker(contentText);
        ncb.setSmallIcon(smallIcon);
        /*ncb.setLargeIcon(largeIcon);*/
        ncb.setContentIntent(pendingIntent);
        ncb.setAutoCancel(true);

        /*ncb.setStyle(contentTextBig);*/
        android.support.v4.app.NotificationCompat.Style style = new android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(largeIcon2);
        ncb.setStyle(style);

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

