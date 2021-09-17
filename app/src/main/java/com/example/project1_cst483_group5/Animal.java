package com.example.project1_cst483_group5;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Animal {

    @SerializedName("id")
    Integer mID;

    @SerializedName("type")
    String mType;

    @SerializedName("breed")
    String mBreed;

    @SerializedName("size")
    String mSize;

    @SerializedName("gender")
    String mGender;

    @SerializedName("age")
    String mAge;

    @SerializedName("color")
    String mColor;

    //ToDo: Break after first space
    @SerializedName("name")
    String mName;

    @SerializedName("description")
    String mDescription;


    @SerializedName("status")
    String mStatus;

    @SerializedName("photos")
    List<Photos> mPhoto;


    public void setmID(Integer mID) {
        this.mID = mID;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

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

    public List<Photos> getmPhoto() {
        return mPhoto;
    }

    public void setmPhoto(List<Photos> mPhoto) {
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
