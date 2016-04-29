package com.lilaoftheday.lilaoftheday;

import android.content.Context;

public class Cat {

    // For now, only a photo, but more to be added in the future.
    String photoName;

    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(
                    this.photoName,
                    "drawable",
                    context.getPackageName()
        );
    }

}

