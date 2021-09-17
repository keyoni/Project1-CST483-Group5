package com.example.project1_cst483_group5.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type App database.
 */
@Database(entities = {User.class, Pet.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    /**
     * User dao user dao.
     *
     * @return the user dao
     */
    public abstract UserDao userDao();

    /**
     * Pet dao pet dao.
     *
     * @return the pet dao
     */
    public abstract PetDao petDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    /**
     * The Database write executor.
     */
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    /**
     * Gets database.
     *
     * @param context the context
     * @return the database
     */
    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}



