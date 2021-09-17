package com.example.project1_cst483_group5;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;


import static org.junit.Assert.*;

//import Retro.Post;
import com.example.project1_cst483_group5.db.AppDatabase;
import com.example.project1_cst483_group5.db.User;
import com.example.project1_cst483_group5.db.UserDao;
import com.example.project1_cst483_group5.db.UserViewModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
    public class APITest {
    /**
     * Use app context.
     */
    @Test
        public void useAppContext() {
            // Context of the app under test.
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            assertEquals("com.example.project1_cst483_group5", appContext.getPackageName());
        }

    /**
     * The Retrofit.
     */
    public Retrofit retrofit;
    /**
     * The Pet finder api.
     */
    public PetFinderApi petFinderApi;
    /**
     * The Authorization.
     */
    AuthApi authorization;
    /**
     * The Auth.
     */
    String auth;


    /**
     * Create api.
     */
    @Before
        public void createAPI(){

           retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.petfinder.com/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

           petFinderApi = retrofit.create(PetFinderApi.class);


        }

    /**
     * Auth.
     * <p>
     * Test API CAll 1
     */
    @Test
        public void auth() {

            AuthRequest authRequest = new AuthRequest("client_credentials",
                    "upIb9LG0P6ycfE7eAPV93hsrSGFFBvwejt0seHROuLLZekv5gS",
                    "PNUfd2VyDeiulTuuwpOAv0pvYo3fO8LS1e9eHHfu");

            Call<AuthApi> authCall = petFinderApi.Auth(authRequest);


            authCall.enqueue(new Callback<AuthApi>() {
                @Override
                public void onResponse(Call<AuthApi> call, Response<AuthApi> response) {

                    if (!response.isSuccessful()) {
                        Log.d("API TEST", "Code: " + response.code());
                        return;
                    }
                    authorization = response.body();
                    assertEquals("Bearer",authorization.getTokenType());
                }

                @Override
                public void onFailure(Call<AuthApi> call, Throwable t) {

                }
            });


        }


    /**
     * Basic animal.
     * <p>
     * Test API CALL 2
     */
    @Test
        public void basicAnimal() {

            AuthRequest authRequest = new AuthRequest("client_credentials",
                    "upIb9LG0P6ycfE7eAPV93hsrSGFFBvwejt0seHROuLLZekv5gS",
                    "PNUfd2VyDeiulTuuwpOAv0pvYo3fO8LS1e9eHHfu");

            Call<AuthApi> authCall = petFinderApi.Auth(authRequest);


            authCall.enqueue(new Callback<AuthApi>() {
                @Override
                public void onResponse(Call<AuthApi> call, Response<AuthApi> response) {

                    if (!response.isSuccessful()) {
                        Log.d("API TEST", "Code: " + response.code());
                        return;
                    }
                    authorization = response.body();
                    assertEquals("Bearer",authorization.getTokenType());
                    auth = authorization.getAccess_token();
                }

                @Override
                public void onFailure(Call<AuthApi> call, Throwable t) {

                }
            });


            Call<AnimalResults> basicAnimalCall = petFinderApi.getBasicAnimalList(" Bearer " +auth);

            basicAnimalCall.enqueue(new Callback<AnimalResults>() {
                @Override
                public void onResponse(Call<AnimalResults> call, Response<AnimalResults> response) {
                    Log.d("API TEST", "inside enqueue");
                    if (!response.isSuccessful()) {
                        Log.d("API TEST", "Code: " + response.code());
                        return;
                    }


                    AnimalResults animalResults = response.body();
                    List<Animal> animalList = animalResults.animals;

                    Animal tempAnimal;
                    tempAnimal = animalList.get(0);
                    assertNotNull(tempAnimal);

                }

                @Override
                public void onFailure(Call<AnimalResults> call, Throwable t) {
                    Log.d("API TEST", "hello failure");
                    Log.d("API TEST", t.getMessage());

                }
            });



        }

    /**
     * Filter animal.
     *
     * Test API CALL 3
     */
    @Test
    public void filterAnimal() {
            AuthRequest authRequest = new AuthRequest("client_credentials",
                    "upIb9LG0P6ycfE7eAPV93hsrSGFFBvwejt0seHROuLLZekv5gS",
                    "PNUfd2VyDeiulTuuwpOAv0pvYo3fO8LS1e9eHHfu");

            Call<AuthApi> authCall = petFinderApi.Auth(authRequest);


            authCall.enqueue(new Callback<AuthApi>() {
                @Override
                public void onResponse(Call<AuthApi> call, Response<AuthApi> response) {

                    if (!response.isSuccessful()) {
                        Log.d("API TEST", "Code: " + response.code());
                        return;
                    }
                    authorization = response.body();
                    assertEquals("Bearer",authorization.getTokenType());
                    auth = authorization.getAccess_token();
                }

                @Override
                public void onFailure(Call<AuthApi> call, Throwable t) {

                }
            });


            Call<AnimalResults> filteredAnimalCall = petFinderApi.getFilteredAnimalList(" Bearer " + auth, "Female", "Cat", "Young");
            Log.d("API TEST", "hello CALL");
            filteredAnimalCall.enqueue(new Callback<AnimalResults>() {
                @Override
                public void onResponse(Call<AnimalResults> call, Response<AnimalResults> response) {
                    Log.d("API TEST", "inside FILTERED enqueue");
                    if (!response.isSuccessful()) {
                        Log.d("API TEST", "Code: " + response.code());
                        return;
                    }

                    AnimalResults animalResults = response.body();
                    List<Animal> animalList = animalResults.animals;

                    Animal tempAnimal;
                    tempAnimal = animalList.get(0);
                    assertEquals("Female",tempAnimal.getmGender());
                    assertEquals("Cat",tempAnimal.getmType());
                    assertEquals("Young",tempAnimal.getmAge());

                }

                @Override
                public void onFailure(Call<AnimalResults> call, Throwable t) {
                    Log.d("API TEST", "hello failure");
                    Log.d("API TEST", t.getMessage());

                }
            });
        }
}
