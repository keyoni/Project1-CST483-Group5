package com.example.project1_cst483_group5.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * The type User repository.
 */
public class UserRepository {
    private final UserDao mUserDao;
    private String name;
    private int userCount;
    private List<User> currentUser;

    /**
     * Instantiates a new User repository.
     *
     * @param application the application
     */
    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDao();
    }


    /**
     * Insert.
     *
     * @param user the user
     */
    void insert(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }

    /**
     * Gets name.
     *
     * @param userId the user id
     * @return the name
     */
    String getName(int userId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            name = mUserDao.getName(userId);
        });
        return name;
    }

    /**
     * Gets user count.
     *
     * @return the user count
     */
    int getUserCount() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userCount = mUserDao.getUserCount();
        });
        return userCount;
    }

    /**
     * Gets user by username and password.
     *
     * @param mUsername the m username
     * @param mPassword the m password
     * @return the user by username and password
     */
    List<User> getUserByUsernameAndPassword(String mUsername, String mPassword) {

        AppDatabase.databaseWriteExecutor.execute(() -> {
            currentUser = mUserDao.getUsersByUsernameAndPassword(mUsername, mPassword);
        });
        return currentUser;
    }

    /**
     * Change username.
     *
     * @param mUsername the m username
     * @param mUserID   the m user id
     */
    void changeUsername(String mUsername, Integer mUserID) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.changeUsername(mUsername, mUserID);
        });

    }
}