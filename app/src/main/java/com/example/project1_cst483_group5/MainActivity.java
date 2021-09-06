package com.example.project1_cst483_group5;

import static java.sql.Types.NULL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.project1_cst483_group5.db.User;
import com.example.project1_cst483_group5.db.UserViewModel;

public class MainActivity extends AppCompatActivity {

    public UserViewModel userVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userVM = new ViewModelProvider(this).get(UserViewModel.class);

        if (userVM.getUserCount() == 0) {
            createUsers();
        }
    }



    public void createUsers() {
        for (int i = 0; i < 3; i++) {
            User tempUser = new User("User" + (i + 1),"user" + (i + 1), "password" + (i +1));
            userVM.insert(tempUser);
        }
    }

}