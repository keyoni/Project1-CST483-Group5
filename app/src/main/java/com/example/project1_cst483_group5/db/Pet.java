package com.example.project1_cst483_group5.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//TODO:
//PetViewModel needed.

//pet table should have
//petID(primary key), userID(foreign key), name, type, gender, id
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


    public Pet(@NonNull Integer mUserID, @NonNull String mName, @NonNull String mType, @NonNull String mGender, @NonNull Integer mID) {
        Integer petID;
        this.mUserID = mUserID;
        this.mName = mName;
        this.mType = mType;
        this.mGender = mGender;
        this.mID = mID; //from api
    }

    @NonNull
    public void setMPetID(Integer mPetID){this.mPetID =mPetID; }

    @NonNull
    public Integer getMPetID() {
        return mPetID;
    }

    @NonNull
    public Integer getMID() {
        return mID;
    }

    public void setMID(@NonNull Integer mID) {
        this.mID = mID;
    }


    @NonNull
    public Integer getMUserID() {
        return mUserID;
    }

    public void setMUserID(@NonNull Integer mUserID) {
        this.mUserID = mUserID;
    }

    @NonNull
    public String getMName() {
        return mName;
    }

    public void setMName(@NonNull String mName) {
        this.mName = mName;
    }

    @NonNull
    public String getMType() {
        return mType;
    }

    public void setMType(@NonNull String mType) {
        this.mType = mType;
    }

    @NonNull
    public String getMGender() {
        return mGender;
    }

    public void setMGender(@NonNull String mGender) {
        this.mGender = mGender;
    }
}
