package com.example.project1_cst483_group5.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


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

    public Integer getPet(){return this.mPetID;}


    @NonNull
    public Integer getmID() {
        return mID;
    }

    public void setmID(@NonNull Integer mID) {
        this.mID = mID;
    }

    @NonNull
    public Integer getmPetID() {
        return mPetID;
    }

    @NonNull
    public Integer getmUserID() {
        return mUserID;
    }

    public void setmUserID(@NonNull Integer mUserID) {
        this.mUserID = mUserID;
    }

    @NonNull
    public String getmName() {
        return mName;
    }

    public void setmName(@NonNull String mName) {
        this.mName = mName;
    }

    @NonNull
    public String getmType() {
        return mType;
    }

    public void setmType(@NonNull String mType) {
        this.mType = mType;
    }

    @NonNull
    public String getmGender() {
        return mGender;
    }

    public void setmGender(@NonNull String mGender) {
        this.mGender = mGender;
    }
}
