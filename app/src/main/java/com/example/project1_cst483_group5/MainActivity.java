package com.example.project1_cst483_group5;

import static java.sql.Types.NULL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project1_cst483_group5.db.User;
import com.example.project1_cst483_group5.db.UserViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public UserViewModel userVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up a click listener for the Create Account button on the welcome(home) page.
        View createAccountButton = findViewById(R.id.btnCreatePage);
        createAccountButton.setOnClickListener(this);

        View loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(this);

        userVM = new ViewModelProvider(this).get(UserViewModel.class);

        if (userVM.getUserCount() == 0) {
            createUsers();
        }

      //getAuth();
        // getUserByUsernameAndPassword();
    }


    @Override
    public void onClick(View view) {
        //test login verification
        EditText username, password;
        username = findViewById(R.id.etLoginEmail); // this is already null because there's no text
        password = findViewById(R.id.etLoginPassword);

        String user = (String) username.getText().toString();
        String passW = (String) password.getText().toString();

        if (user.isEmpty() && passW.isEmpty() && (view.getId() == R.id.btnLogin)) {
            Toast.makeText(this, "Not a match. Enter the correct login info", Toast.LENGTH_SHORT).show();

        }

        if (view.getId() == R.id.btnCreatePage) {
            Intent i = new Intent(this, CreateAnAccount.class);
            startActivity(i);
        } else if (user.matches("admin") && passW.matches("admin") && view.getId() == R.id.btnLogin) {
            Intent i = new Intent(this, Favorites.class);
            startActivity(i);
        }


    }

    public void createUsers() {
        for (int i = 0; i < 3; i++) {
            User tempUser = new User("User" + (i + 1),"user" + (i + 1), "password" + (i +1));
            userVM.insert(tempUser);
        }
    }

    public void getUsersbyUsernameAndPassword(EditText username, EditText password){

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