package com.example.project1_cst483_group5;

import static java.sql.Types.NULL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.project1_cst483_group5.db.User;
import com.example.project1_cst483_group5.db.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public UserViewModel userVM;
    public Button loginBtn;
    AuthApi authorization;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userVM = new ViewModelProvider(this).get(UserViewModel.class);
        loginBtn = findViewById(R.id.btnLogin);

        if (userVM.getUserCount() == 0) {
            createUsers();
        }

      getAuth();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }

        });



    }



    public void createUsers() {
        for (int i = 0; i < 3; i++) {
            User tempUser = new User("User" + (i + 1),"user" + (i + 1), "password" + (i +1));
            userVM.insert(tempUser);
        }
    }

    private void getAuth() {
        Log.d("API TEST", "helloooo");

//        Gson gson = new GsonBuilder().serializeNulls().create();
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.petfinder.com/v2/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//        PetFinderApi petFinderApi = retrofit.create(PetFinderApi.class);



//    Log.d(;"API TEST", petFinderApi.Auth("client_credential",
//            "upIb9LG0P6ycfE7eAPV93hsrSGFFBvwejt0seHROuLLZekv5gS",
//            "PNUfd2VyDeiulTuuwpOAv0pvYo3fO8LS1e9eHHfu").url())

        AuthRequest authRequest = new AuthRequest("client_credentials",
                "upIb9LG0P6ycfE7eAPV93hsrSGFFBvwejt0seHROuLLZekv5gS",
                "PNUfd2VyDeiulTuuwpOAv0pvYo3fO8LS1e9eHHfu");

        Call<AuthApi> authCall = PetFinderClient.getInstance().getPetFinderApi().Auth(authRequest);


        authCall.enqueue(new Callback<AuthApi>() {
            @Override
            public void onResponse(Call<AuthApi> call, Response<AuthApi> response) {

                if (!response.isSuccessful()) {
                    Log.d("API TEST","Code: " + response.code());
                    return;
                }
                authorization = response.body();

                Log.d("API TEST", authorization.getAccess_token());
            }

            @Override
            public void onFailure(Call<AuthApi> call, Throwable t) {

            }
        });

    }

    public void login() {
        Intent intent = Favorites.getIntent(getApplicationContext(),authorization.getAccess_token());
       intent.putExtra("FAVORITES_COM_PROJ1_G5_AUTH",authorization.getAccess_token());
        startActivity(intent);

    }
}