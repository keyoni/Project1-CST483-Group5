package com.example.project1_cst483_group5.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

public class UserRepository {
    private UserDao mUserDao;

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
          return  mUserDao.getName(userId);
    }

    int getUserCount() {
        return mUserDao.getUserCount();
    }
}