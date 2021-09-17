package com.example.project1_cst483_group5.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * The type Pet.
 */
@Entity(tableName = "pet_table")
public class Pet {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "petID")
    private Integer mPetID;

    //@ForeignKey()
    @NonNull
    @ColumnInfo(name = "userID")
    private Integer mUserID;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "type")
    private String mType;

    @NonNull
    @ColumnInfo(name = "gender")
    private String mGender;

    @NonNull
    @ColumnInfo(name = "id") //from the api
    private Integer mID;


    /**
     * Instantiates a new Pet.
     *
     * @param mUserID the m user id
     * @param mName   the m name
     * @param mType   the m type
     * @param mGender the m gender
     * @param mID     the m id
     */
    public Pet(@NonNull Integer mUserID, @NonNull String mName, @NonNull String mType, @NonNull String mGender, @NonNull Integer mID) {
        Integer petID;
        this.mUserID = mUserID;
        this.mName = mName;
        this.mType = mType;
        this.mGender = mGender;
        this.mID = mID; //from api
    }

    /**
     * Sets m pet id.
     *
     * @param mPetID the m pet id
     */
    @NonNull
    public void setMPetID(Integer mPetID) {
        this.mPetID = mPetID;
    }

    /**
     * Gets m pet id.
     *
     * @return the m pet id
     */
    @NonNull
    public Integer getMPetID() {
        return mPetID;
    }

    /**
     * Gets mid.
     *
     * @return the mid
     */
    @NonNull
    public Integer getMID() {
        return mID;
    }

    /**
     * Sets mid.
     *
     * @param mID the m id
     */
    public void setMID(@NonNull Integer mID) {
        this.mID = mID;
    }


    /**
     * Gets m user id.
     *
     * @return the m user id
     */
    @NonNull
    public Integer getMUserID() {
        return mUserID;
    }

    /**
     * Sets m user id.
     *
     * @param mUserID the m user id
     */
    public void setMUserID(@NonNull Integer mUserID) {
        this.mUserID = mUserID;
    }

    /**
     * Gets m name.
     *
     * @return the m name
     */
    @NonNull
    public String getMName() {
        return mName;
    }

    /**
     * Sets m name.
     *
     * @param mName the m name
     */
    public void setMName(@NonNull String mName) {
        this.mName = mName;
    }

    /**
     * Gets m type.
     *
     * @return the m type
     */
    @NonNull
    public String getMType() {
        return mType;
    }

    /**
     * Sets m type.
     *
     * @param mType the m type
     */
    public void setMType(@NonNull String mType) {
        this.mType = mType;
    }

    /**
     * Gets m gender.
     *
     * @return the m gender
     */
    @NonNull
    public String getMGender() {
        return mGender;
    }

    /**
     * Sets m gender.
     *
     * @param mGender the m gender
     */
    public void setMGender(@NonNull String mGender) {
        this.mGender = mGender;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "mPetID=" + mPetID +
                ", mUserID=" + mUserID +
                ", mName='" + mName + '\'' +
                ", mType='" + mType + '\'' +
                ", mGender='" + mGender + '\'' +
                ", mID=" + mID +
                '}';
    }
}
