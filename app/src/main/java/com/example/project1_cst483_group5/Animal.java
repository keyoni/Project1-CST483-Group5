package com.example.project1_cst483_group5;

import androidx.room.ColumnInfo;

public class Animal {
    //ToDO: Fill out with params we want from api

    @ColumnInfo(name = "id")
    Integer mID;

    @ColumnInfo(name = "type")
    String mType;

    @ColumnInfo(name = "breed")
    String mBreed;

    @ColumnInfo(name = "size")
    String mSize;

    @ColumnInfo(name = "gender")
    String mGender;

    @ColumnInfo(name = "age")
    String mAge;

    @ColumnInfo(name = "color")
    String mColor;

    @ColumnInfo(name = "name")
    String mName;

    @ColumnInfo(name = "description")
    String mDescription;

    @ColumnInfo(name = "photos")
    String mPhoto;

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmBreed() {
        return mBreed;
    }

    public void setmBreed(String mBreed) {
        this.mBreed = mBreed;
    }

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmAge() {
        return mAge;
    }

    public void setmAge(String mAge) {
        this.mAge = mAge;
    }

    public String getmColor() {
        return mColor;
    }

    public void setmColor(String mColor) {
        this.mColor = mColor;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmPhoto() {
        return mPhoto;
    }

    public void setmPhoto(String mPhoto) {
        this.mPhoto = mPhoto;
    }

    public Integer getmID() {
        return mID;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "mID=" + mID +
                ", mType='" + mType + '\'' +
                ", mBreed='" + mBreed + '\'' +
                ", mSize='" + mSize + '\'' +
                ", mGender='" + mGender + '\'' +
                ", mAge='" + mAge + '\'' +
                ", mColor='" + mColor + '\'' +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mPhoto='" + mPhoto + '\'' +
                '}';
    }
}
