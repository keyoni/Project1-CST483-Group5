package com.example.project1_cst483_group5.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("SELECT name FROM user_table WHERE userID = :mUserID")
    String getName(Integer mUserID);

    @Query("SELECT * FROM user_table WHERE username = :mUsername AND password = :mPassword ")
    List<User> getUsersByUsernameAndPassword(String mUsername, String mPassword);

    @Query("SELECT COUNT(userID) FROM user_table ")
    int getUserCount();
}
