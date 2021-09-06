package com.example.project1_cst483_group5.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepo;

    public UserViewModel(@NonNull Application application) {
        super(application);

         userRepo = new UserRepository(application);
    }

    public void insert(User user) {
        userRepo.insert(user);
    }

    public String getName(int userId) {
       return userRepo.getName(userId);
    }

     public int getUserCount() {
         return userRepo.getUserCount();
     }
}
