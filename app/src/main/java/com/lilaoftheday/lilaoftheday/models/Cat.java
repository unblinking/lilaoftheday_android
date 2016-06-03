package com.lilaoftheday.lilaoftheday.models;

import android.content.Context;

public class Cat {

    // For now, only a photo, but more to be added in the future.
    public String photoName;

    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(
                    this.photoName,
                    "drawable",
                    context.getPackageName()
        );
    }

}

