package com.example.project1_cst483_group5.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


/**
 * The type User view model.
 */
public class UserViewModel extends AndroidViewModel {

    private final UserRepository userRepo;

    /**
     * Instantiates a new User view model.
     *
     * @param application the application
     */
    public UserViewModel(@NonNull Application application) {
        super(application);

        userRepo = new UserRepository(application);
    }

    /**
     * Insert.
     *
     * @param user the user
     */
    public void insert(User user) {
        userRepo.insert(user);
    }

    /**
     * Gets name.
     *
     * @param userId the user id
     * @return the name
     */
    public String getName(int userId) {
        return userRepo.getName(userId);
    }

    /**
     * Gets user count.
     *
     * @return the user count
     */
    public int getUserCount() {
        return userRepo.getUserCount();
    }

    /**
     * Gets users by username and password.
     *
     * @param mUsername the m username
     * @param mPassword the m password
     * @return the users by username and password
     */
    public List<User> getUsersByUsernameAndPassword(String mUsername, String mPassword) {
        return userRepo.getUserByUsernameAndPassword(mUsername, mPassword);
    }

    /**
     * Change username.
     *
     * @param mUsername the m username
     * @param mUserID   the m user id
     */
    public void changeUsername(String mUsername, Integer mUserID) {
        userRepo.changeUsername(mUsername, mUserID);
    }
}
