package com.example.project1_cst483_group5.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * The interface User dao.
 */
@Dao
public interface UserDao {
    /**
     * Insert.
     *
     * @param user the user
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    /**
     * Gets name.
     *
     * @param mUserID the m user id
     * @return the name
     */
    @Query("SELECT name FROM user_table WHERE userID = :mUserID")
    String getName(Integer mUserID);

    /**
     * Gets users by username and password.
     *
     * @param mUsername the m username
     * @param mPassword the m password
     * @return the users by username and password
     */
    @Query("SELECT * FROM user_table WHERE username = :mUsername AND password = :mPassword ")
    List<User> getUsersByUsernameAndPassword(String mUsername, String mPassword);

    /**
     * Gets user count.
     *
     * @return the user count
     */
    @Query("SELECT COUNT(userID) FROM user_table ")
    int getUserCount();

    /**
     * Change username.
     *
     * @param mUsername the m username
     * @param mUserID   the m user id
     */
    @Query("UPDATE user_table SET username = :mUsername WHERE userID = :mUserID")
    void changeUsername(String mUsername, Integer mUserID);
}
