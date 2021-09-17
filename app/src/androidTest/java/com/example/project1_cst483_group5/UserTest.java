package com.example.project1_cst483_group5;


import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;


import static org.junit.Assert.*;

//import Retro.Post;
import com.example.project1_cst483_group5.db.AppDatabase;
import com.example.project1_cst483_group5.db.User;
import com.example.project1_cst483_group5.db.UserDao;
import com.example.project1_cst483_group5.db.UserViewModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UserTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.project1_cst483_group5", appContext.getPackageName());
    }
    public AppDatabase db;
    public UserDao userDao;
    public UserViewModel userVM;
    @Before
    public void initDb() throws Exception {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        AppDatabase db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                AppDatabase.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();

        userDao = db.userDao();



    }

    @Test
    public void addUser(){
        User tempUser = new User("User","User","Password");
        userDao.insert(tempUser);
        assertEquals(tempUser.getMName(),userDao.getName(1));
    }

    @Test
    public void getUserCount(){
        User tempUser = new User("User","User","Password");
        userDao.insert(tempUser);
        assertEquals(1,userDao.getUserCount());
    }

    @Test
    public void getUserByLogin() {
        User tempUser = new User ("User", "user","Password");
        userDao.insert(tempUser);
        userDao.getUsersByUsernameAndPassword("user","password");
        assertEquals(tempUser.getMPassword(),userDao.getUsersByUsernameAndPassword("user", "Password").get(0).getMPassword());
    }



}