package com.example.project1_cst483_group5.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;


import java.util.List;
import java.util.jar.Attributes;

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
     * Gets pets by user id.
     *
     * @param mUserID the m user id
     * @return the pets by user id
     */
    @Query("SELECT * FROM pet_table WHERE userID = :mUserID")
    List<Pet> getPetsByUserID(Integer mUserID);

}
