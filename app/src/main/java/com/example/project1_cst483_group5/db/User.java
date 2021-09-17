package com.example.project1_cst483_group5.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * The type User.
 */
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

    /**
     * Instantiates a new User.
     *
     * @param mName     the m name
     * @param mUsername the m username
     * @param mPassword the m password
     */
    public User(@NonNull String mName, @NonNull String mUsername, @NonNull String mPassword) {
        this.mName = mName;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
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
     * Gets m username.
     *
     * @return the m username
     */
    @NonNull
    public String getMUsername() {
        return mUsername;
    }

    /**
     * Sets m username.
     *
     * @param mUsername the m username
     */
    public void setMUsername(@NonNull String mUsername) {
        this.mUsername = mUsername;
    }

    /**
     * Gets m password.
     *
     * @return the m password
     */
    @NonNull
    public String getMPassword() {
        return mPassword;
    }

    /**
     * Sets m password.
     *
     * @param mPassword the m password
     */
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
