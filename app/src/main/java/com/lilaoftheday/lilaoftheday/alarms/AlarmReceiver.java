package com.lilaoftheday.lilaoftheday.alarms;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v7.app.NotificationCompat;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.activities.MainActivity;
import com.lilaoftheday.lilaoftheday.data.CatArray;
import com.lilaoftheday.lilaoftheday.models.Cat;
import com.lilaoftheday.lilaoftheday.utilities.Utilities;

import java.util.ArrayList;
import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {

    // onReceive is used when the alarm is triggered.
    @Override
    public void onReceive(Context context, Intent intent) {
        buildNotification(context);
    }

    private Bitmap formatPhotoForNotification(Bitmap photo) {

        float aspectRatio = photo.getWidth() / (float) photo.getHeight();
        Bitmap photoFinal;

        // Scale the photo to a max height of 256
        Bitmap photoScaled;
        int scaledHeight = 256;
        int scaledWidth = Math.round(scaledHeight * aspectRatio);
        photoScaled = Bitmap.createScaledBitmap(photo, scaledWidth, scaledHeight, false);

        if (photoScaled.getWidth() > 512) {
            int scaledWidth2 = 512;
            int scaledHeight2 = Math.round(scaledWidth2 / aspectRatio);
            photoScaled = Bitmap.createScaledBitmap(photoScaled, scaledWidth2, scaledHeight2, false);
        }

        // Create a full width cropped background for the photo to overlay
        Bitmap photoBackground;
        int backgroundWidth = 512;
        int backgroundHeight = Math.round(backgroundWidth / aspectRatio);
        photoBackground = Bitmap.createScaledBitmap(photo, backgroundWidth, backgroundHeight, false);

        // Colorize the background
        photoBackground = Utilities.makeTintedBitmap(photoBackground);

        if (photoBackground.getHeight() < 256) {
            int backgroundHeight2 = 256;
            int backgroundWidth2 = Math.round(backgroundHeight2 * aspectRatio);
            photoBackground = Bitmap.createScaledBitmap(photoBackground, backgroundWidth2, backgroundHeight2, false);
        }

        // Overlay the scaled photo onto the background, centered
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        Canvas canvas = new Canvas(photoBackground);
        canvas.drawBitmap(photoScaled, ( (photoBackground.getWidth() - photoScaled.getWidth()) / 2 ), ( ( photoBackground.getHeight() - photoScaled.getHeight() ) / 2 ), paint);

        photoFinal = photoBackground;

        return photoFinal;

    }

    private void buildNotification(Context context) {

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

        photo = formatPhotoForNotification(photo);

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

