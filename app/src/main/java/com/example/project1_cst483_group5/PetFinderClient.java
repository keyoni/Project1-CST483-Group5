package com.example.project1_cst483_group5;



import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PetFinderClient {
    //help from https://www.section.io/engineering-education/making-api-requests-using-retrofit-android/
    private static PetFinderClient instance = null;
    // private PetFinderApi petFinderApi;


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.petfinder.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static synchronized PetFinderClient getInstance() {
        if (instance == null) {
            instance = new PetFinderClient();
        }
        return instance;
    }

    PetFinderApi petFinderApi = retrofit.create(PetFinderApi.class);

    public PetFinderApi getPetFinderApi() {
        return petFinderApi;
    }

}
