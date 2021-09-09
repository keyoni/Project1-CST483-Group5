package com.example.project1_cst483_group5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiTestActivity extends AppCompatActivity {

    TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);

        testText = findViewById(R.id.tvApiTest);

        getAuth();
    }

    private void getAuth() {

//        Call<AuthApi> authCall = PetFinderClient.getInstance().getPetFinderApi().Auth();
//
//        authCall.enqueue(new Callback<AuthApi>() {
//            @Override
//            public void onResponse(Call<AuthApi> call, Response<AuthApi> response) {
//                AuthApi authorization = response.body();
//                Log.d("API TEST",authorization.getAccess_token());
//            }
//
//            @Override
//            public void onFailure(Call<AuthApi> call, Throwable t) {
//
//            }
//        });

    }

}