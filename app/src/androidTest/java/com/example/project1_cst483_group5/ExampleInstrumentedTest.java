package com.example.project1_cst483_group5;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.project1_cst483_group5.db.AppDatabase;
import com.example.project1_cst483_group5.db.User;
import com.example.project1_cst483_group5.db.UserDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.project1_cst483_group5", appContext.getPackageName());
    }

    @RunWith(AndroidJUnit4.class)
    public class SimpleEntityReadWriteTest {
        private UserDao userDao;
        private AppDatabase db;

        @Before
        public void createDb() {
            Context context = ApplicationProvider.getApplicationContext();
            db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
            userDao = db.userDao();
        }

        @After
        public void closeDb() throws IOException {
            db.close();
        }

        @Test
        public void writeUserAndReadInList() throws Exception {
            User user = new User("bob","bobby123","12345");
            userDao.insert(user);
            String byName = userDao.getName(1);
            assertEquals(byName, "bob");
        }
    }
}