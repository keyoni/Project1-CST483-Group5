package com.example.project1_cst483_group5.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Pet.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract PetDao petDao();
    
}



