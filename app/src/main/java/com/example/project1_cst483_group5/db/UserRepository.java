package com.example.project1_cst483_group5.db;

import android.app.Application;
import android.os.AsyncTask;

public class UserRepository {
    private UserDao mUserDao;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDao();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(mUserDao).execute(user);

    }


}

 class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {

    private UserDao mUserDao;

    InsertUserAsyncTask(UserDao mUserDao) {
        this.mUserDao = mUserDao;

    }

    @Override
    protected Void doInBackground(User... user) {
        mUserDao.insert(user[0]);
        return null;
    }


}