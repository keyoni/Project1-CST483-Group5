package com.example.project1_cst483_group5.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class PetViewModel extends AndroidViewModel {

    private final PetRepository petRepo;


    public PetViewModel(@NonNull Application application) {
        super(application);

        petRepo = new PetRepository(application);

    }

    public void insert(Pet pet) {
        petRepo.insert(pet);
    }

    public List<Pet> getPetsByUserID(Integer mUserID) {
        return petRepo.getAllFavorites(mUserID);
    }

    public void deletePet(Integer mID) {
        petRepo.deletePet(mID);
    }
}
