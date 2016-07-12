package com.lilaoftheday.lilaoftheday.utilities;


import android.content.Context;

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

}

