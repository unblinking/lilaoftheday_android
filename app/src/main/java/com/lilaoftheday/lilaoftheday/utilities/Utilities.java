package com.lilaoftheday.lilaoftheday.utilities;


import android.content.Context;

public class Utilities {

    public static int getDrawableResourceId(Context context, String imageName) {
        return context.getResources().getIdentifier(
                imageName,
                "drawable",
                context.getPackageName()
        );
    }

}

