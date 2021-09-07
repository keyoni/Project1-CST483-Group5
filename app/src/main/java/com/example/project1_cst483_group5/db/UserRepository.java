package com.example.project1_cst483_group5.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private String name;
    private int userCount;
    private User currentUser;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDao();
    }


    void insert(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }

    String getName(int userId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            name = mUserDao.getName(userId);
        });
        return name;
    }

    int getUserCount() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userCount = mUserDao.getUserCount();
        });
        return userCount;
    }

    User getUserByUsernameAndPassword(String mUsername, String mPassword) {

        AppDatabase.databaseWriteExecutor.execute(() -> {
           currentUser = mUserDao.getUsersByUsernameAndPassword(mUsername,mPassword).get(0);
        });
        return currentUser;
    }
}