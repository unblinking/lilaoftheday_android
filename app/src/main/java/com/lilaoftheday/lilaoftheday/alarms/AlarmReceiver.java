package com.lilaoftheday.lilaoftheday.alarms;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v7.app.NotificationCompat;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.activities.MainActivity;
import com.lilaoftheday.lilaoftheday.data.CatArray;
import com.lilaoftheday.lilaoftheday.models.Cat;

import java.util.ArrayList;
import java.util.Random;

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
        smallIcon = R.drawable.ic_cat;

        Bitmap largeIcon;
        largeIcon = BitmapFactory.decodeResource(
                context.getResources(),
                R.mipmap.ic_launcher
        );

        Bitmap photo;
        ArrayList<Cat> catArrayList;
        catArrayList = new CatArray().getCatArray(context);
        Cat cat = catArrayList.get(new Random().nextInt(catArrayList.size()));
        photo = BitmapFactory.decodeResource(
                context.getResources(),
                cat.getDbRecordId()
        );

        String contentTitle;
        contentTitle = context.getString(
                R.string.notification_content_title
        );

        String contentText;
        contentText = context.getString(
                R.string.notification_content_text
        );

        NotificationCompat.Builder ncb;
        ncb = new NotificationCompat.Builder(context);

        ncb.setWhen(System.currentTimeMillis());
        ncb.setContentTitle(contentTitle);
        ncb.setContentText(contentText);
        ncb.setTicker(contentText);
        ncb.setSmallIcon(smallIcon);
        ncb.setLargeIcon(largeIcon);
        ncb.setContentIntent(pendingIntent);
        ncb.setAutoCancel(true);

        Style style = new BigPictureStyle()
                .bigPicture(photo)
                .setBigContentTitle(contentTitle)
                .setSummaryText(contentText);
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

