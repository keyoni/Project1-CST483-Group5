package com.example.project1_cst483_group5.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


/**
 * The type Pet view model.
 */
public class PetViewModel extends AndroidViewModel {

    private final PetRepository petRepo;


    /**
     * Instantiates a new Pet view model.
     *
     * @param application the application
     */
    public PetViewModel(@NonNull Application application) {
        super(application);

        petRepo = new PetRepository(application);

    }

    /**
     * Insert.
     *
     * @param pet the pet
     */
    public void insert(Pet pet) {
        petRepo.insert(pet);
    }

    /**
     * Gets pets by user id.
     *
     * @param mUserID the m user id
     * @return the pets by user id
     */
    public List<Pet> getPetsByUserID(Integer mUserID) {
        return petRepo.getAllFavorites(mUserID);
    }

    /**
     * Delete pet.
     *
     * @param mID the m id
     */
    public void deletePet(Integer mID) {
        petRepo.deletePet(mID);
    }
}
