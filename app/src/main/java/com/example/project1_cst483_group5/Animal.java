package com.example.project1_cst483_group5;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Animal.
 */
public class Animal {

    /**
     * The M id.
     */
    @SerializedName("id")
    Integer mID;

    /**
     * The M type.
     */
    @SerializedName("type")
    String mType;

    /**
     * The M breed.
     */
    @SerializedName("breed")
    String mBreed;

    /**
     * The M size.
     */
    @SerializedName("size")
    String mSize;

    /**
     * The M gender.
     */
    @SerializedName("gender")
    String mGender;

    /**
     * The M age.
     */
    @SerializedName("age")
    String mAge;

    /**
     * The M color.
     */
    @SerializedName("color")
    String mColor;

    /**
     * The M name.
     */
//ToDo: Break after first space
    @SerializedName("name")
    String mName;

    /**
     * The M description.
     */
    @SerializedName("description")
    String mDescription;


    /**
     * The M status.
     */
    @SerializedName("status")
    String mStatus;

    /**
     * The Url.
     */
    @SerializedName("url")
    String url;

    /**
     * The M photo.
     */
    @SerializedName("photos")
    List<Photos> mPhoto;


    /**
     * Sets id.
     *
     * @param mID the m id
     */
    public void setmID(Integer mID) {
        this.mID = mID;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getmStatus() {
        return mStatus;
    }

    /**
     * Sets status.
     *
     * @param mStatus the m status
     */
    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getmType() {
        return mType;
    }

    /**
     * Sets type.
     *
     * @param mType the m type
     */
    public void setmType(String mType) {
        this.mType = mType;
    }

    /**
     * Gets breed.
     *
     * @return the breed
     */
    public String getmBreed() {
        return mBreed;
    }

    /**
     * Sets breed.
     *
     * @param mBreed the m breed
     */
    public void setmBreed(String mBreed) {
        this.mBreed = mBreed;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public String getmSize() {
        return mSize;
    }

    /**
     * Sets size.
     *
     * @param mSize the m size
     */
    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getmGender() {
        return mGender;
    }

    /**
     * Sets gender.
     *
     * @param mGender the m gender
     */
    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public String getmAge() {
        return mAge;
    }

    /**
     * Sets age.
     *
     * @param mAge the m age
     */
    public void setmAge(String mAge) {
        this.mAge = mAge;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getmColor() {
        return mColor;
    }

    /**
     * Sets color.
     *
     * @param mColor the m color
     */
    public void setmColor(String mColor) {
        this.mColor = mColor;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getmName() {
        return mName;
    }

    /**
     * Sets name.
     *
     * @param mName the m name
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getmDescription() {
        return mDescription;
    }

    /**
     * Sets description.
     *
     * @param mDescription the m description
     */
    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public List<Photos> getmPhoto() {
        return mPhoto;
    }

    /**
     * Sets photo.
     *
     * @param mPhoto the m photo
     */
    public void setmPhoto(List<Photos> mPhoto) {
        this.mPhoto = mPhoto;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
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
