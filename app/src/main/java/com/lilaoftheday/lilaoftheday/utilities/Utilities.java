package com.lilaoftheday.lilaoftheday.utilities;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;

import java.util.concurrent.atomic.AtomicInteger;

public class Utilities {

    public static int getDrawableResourceId(Context context, String imageName) {
        return context.getResources().getIdentifier(
                imageName,
                "drawable",
                context.getPackageName()
        );
    }

    public static int generateViewId() {
        final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public static Bitmap makeTintedBitmap(Bitmap src) {
        Bitmap result = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        Canvas c = new Canvas(result);
        Paint paint = new Paint();
        // Lighten the bitmap
        paint.setColorFilter(new LightingColorFilter(0xffffff, 0xffaaaaaa));
        c.drawBitmap(src, 0, 0, paint);
        // Pinken the bitmap
        Paint paint2 = new Paint();
        paint2.setColorFilter(new LightingColorFilter(0xffc6ff, 0));
        c.drawBitmap(result, 0, 0, paint2);
        return result;
    }

}

