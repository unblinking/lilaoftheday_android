package com.lilaoftheday.lilaoftheday.models;

public class Cat {

    public String photoName;

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    /*public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(
                    this.photoName,
                    "drawable",
                    context.getPackageName()
        );
    }*/

}

