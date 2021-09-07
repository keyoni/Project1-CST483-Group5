package com.example.project1_cst483_group5;

import static java.sql.Types.NULL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.project1_cst483_group5.db.User;
import com.example.project1_cst483_group5.db.UserViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

      //getAuth();
    }



    public void createUsers() {
        for (int i = 0; i < 3; i++) {
            User tempUser = new User("User" + (i + 1),"user" + (i + 1), "password" + (i +1));
            userVM.insert(tempUser);
        }
    }

    private void getAuth() {
        Log.d("API TEST", "helloooo");
        Call<AuthApi> authCall = PetFinderClient.getInstance().getPetFinderApi().Auth();

        authCall.enqueue(new Callback<AuthApi>() {
            @Override
            public void onResponse(Call<AuthApi> call, Response<AuthApi> response) {
                AuthApi authorization = response.body();
                Log.d("API TEST", authorization.getAccess_token());
            }

            @Override
            public void onFailure(Call<AuthApi> call, Throwable t) {

            }
        });

    }
}