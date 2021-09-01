package com.example.project1_cst483_group5.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;
import java.util.jar.Attributes;
/*Todo:
- queries based on name, userID, type, gender, or id (seperate identifier to make it easier)
 */


@Dao
public interface PetDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pet pet);

    @Query("DELETE FROM pet_table") //not sure why this is an error
    void deleteAll();

    @Query("SELECT * FROM pet_table ORDER BY petID ASC")
    LiveData<List<Pet>> getPetsByIDs(); //livedata to update changed data that is being observed

//    @Query("SELECT * FROM pet_table WHERE userID == )
}
