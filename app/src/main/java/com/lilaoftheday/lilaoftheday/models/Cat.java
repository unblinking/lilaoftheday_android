package com.lilaoftheday.lilaoftheday.models;

public class Cat {

    public int dbRecordId;
    public String photoName;

    public String getPhotoName() {
        return photoName;
    }
    public int getDbRecordId() {
        return dbRecordId;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
    public void setDbRecordId(int dbRecordId) {
        this.dbRecordId = dbRecordId;
    }

}

