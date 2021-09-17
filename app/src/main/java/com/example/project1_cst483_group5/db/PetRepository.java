package com.example.project1_cst483_group5.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * The type Pet repository.
 */
public class PetRepository {

    private final PetDao mPetDao;
    private LiveData<List<Pet>> mAllPets;
    private List<Pet> mAllFavorites;


    /**
     * Instantiates a new Pet repository.
     *
     * @param application the application
     */
// Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    PetRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mPetDao = db.petDao();
        //mAllPets = mPetDao.getPetsByIDs();

    }

    /**
     * Gets all pets.
     *
     * @return the all pets
     */
// Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Pet>> getAllPets() {
        return mAllPets;
    }

    /**
     * Insert.
     *
     * @param pet the pet
     */
// You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Pet pet) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mPetDao.insert(pet);
        });
    }

    /**
     * Delete pet.
     *
     * @param mID the m id
     */
    void deletePet(Integer mID) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mPetDao.deletePet(mID);
        });
    }


    /**
     * Gets all favorites.
     *
     * @param mUserID the m user id
     * @return the all favorites
     */
    List<Pet> getAllFavorites(Integer mUserID) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mAllFavorites = mPetDao.getPetsByUserID(mUserID);
        });

        return mAllFavorites;
    }

}
