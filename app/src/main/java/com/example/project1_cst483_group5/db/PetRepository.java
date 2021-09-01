package com.example.project1_cst483_group5.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PetRepository {

    private PetDao mPetDao;
    private LiveData<List<Pet>> mAllPets;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    PetRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mPetDao = db.petDao();
        mAllPets = mPetDao.getPetsByIDs();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Pet>> getAllPets() {
        return mAllPets;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Pet pet) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mPetDao.insert(pet);
        });
    }

}
