package com.example.project1_cst483_group5.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;


import java.util.List;
import java.util.jar.Attributes;
/*Todo:
- queries based on name, userID, type, gender, or id (seperate identifier to make it easier)
 */


/**
 * The interface Pet dao.
 */
@Dao
public interface PetDao {

    /**
     * Insert.
     *
     * @param pet the pet
     */
// allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pet pet);

    /**
     * Delete all.
     */
    @Query("DELETE FROM pet_table")
    //not sure why this is an error
    void deleteAll();

    /**
     * Delete pet.
     *
     * @param mID the m id
     */
    @Query("DELETE FROM pet_table WHERE id = :mID")
    //not sure why this is an error
    void deletePet(Integer mID);

    /**
     * Gets pets by i ds.
     *
     * @return the pets by i ds
     */
    @Query("SELECT * FROM pet_table ORDER BY petID ASC")
    LiveData<List<Pet>> getPetsByIDs(); //livedata to update changed data that is being observed

    /**
     * Gets pets by user id.
     *
     * @param mUserID the m user id
     * @return the pets by user id
     */
    @Query("SELECT * FROM pet_table WHERE userID = :mUserID")
    List<Pet> getPetsByUserID(Integer mUserID);
    //TODO:update to live data
    //* these querries are only needed in the user_table
//    @Query ("SELECT * FROM pet_table WHERE type = :mType")
//    LiveData<List<Pet>> getPetsByType(String mType);
//
//    @Query ("SELECT * FROM pet_table WHERE gender = :mGender")
//    LiveData<List<Pet>> getPetsBygender(String mGender);

}
