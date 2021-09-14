package com.example.project1_cst483_group5.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "userID")
    private Integer mUserID;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "username")
    private String mUsername;

    @NonNull
    @ColumnInfo(name = "password")
    private String mPassword;

    public User(@NonNull String mName, @NonNull String mUsername, @NonNull String mPassword) {
        this.mName = mName;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
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
    public String getMUsername() {
        return mUsername;
    }

    public void setMUsername(@NonNull String mUsername) {
        this.mUsername = mUsername;
    }

    @NonNull
    public String getMPassword() {
        return mPassword;
    }

    public void setMPassword(@NonNull String mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return mUserID.equals(user.mUserID) && mName.equals(user.mName) && mUsername.equals(user.mUsername) && mPassword.equals(user.mPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mUserID, mName, mUsername, mPassword);
    }
}
