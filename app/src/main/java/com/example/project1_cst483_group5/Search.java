package com.example.project1_cst483_group5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

public class Search extends AppCompatActivity {

    public static final String ACTIVITY_LABEL_AUTH = "SEARCH_COM_PROJ1_G5_AUTH";
    public Button favBtn;
    List<Animal> animalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String auth = getIntent().getStringExtra(ACTIVITY_LABEL_AUTH);

        favBtn = findViewById(R.id.btnFavSearchPage);
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toFavoritesPage(auth);
            }

        });

        getBasicAnimals(auth);
    }

    public static Intent getIntent(Context context, String auth) {
        Intent intent = new Intent(context,Search.class);

        intent.putExtra(Search.ACTIVITY_LABEL_AUTH,auth);

        return intent;

    }

    public void toFavoritesPage(String auth) {
        Intent intent = Favorites.getIntent(getApplicationContext(),auth);
        intent.putExtra("SEARCH_COM_PROJ1_G5_AUTH",auth);
        startActivity(intent);
    }


    public void getBasicAnimals(String auth) {
        Log.d("API TEST", auth);
        Log.d("API TEST", "helloooo");

        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.petfinder.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        PetFinderApi petFinderApi = retrofit.create(PetFinderApi.class);

       Call<AnimalResults> basicAnimalCall = petFinderApi.getBasicAnimalList(" Bearer " + auth);

      // Call<List<Animal>> basicAnimalCall = PetFinderClient.getInstance().petFinderApi.getBasicAnimalList("Authorization: Bearer " + auth);
        Log.d("API TEST", "hello");
            basicAnimalCall.enqueue(new Callback <AnimalResults>(){
                @Override
                public void onResponse(Call<AnimalResults> call, Response<AnimalResults> response) {
                    Log.d("API TEST", "inside enqueue");
                    if (!response.isSuccessful()) {
                        Log.d("API TEST","Code: " + response.code());
                        return;
                    }

//                    try {
//
//                        JSONObject json = new JSONObject(String.valueOf(response.body()));
//                        JSONArray array = json.getJSONArray("GetCitiesResult");
//                        for (int i = 0; i < array.length(); i++) {
//                            list.add(array.getString(i));
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    AnimalResults animalResults = response.body();
//                    animalList = response.body();
//
                    Animal tempAnimal = new Animal();
                    tempAnimal = animalResults.getAnimals().get(0);
                    Log.d("API TEST",""+ tempAnimal.toString());
                }

                @Override
                public void onFailure(Call<AnimalResults> call, Throwable t) {
                    Log.d("API TEST", "hello failure");
                }
            });

        }

}
        //curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJ1cEliOUxHMFA2eWNmRTdlQVBWOTNoc3JTR0ZGQnZ3ZWp0MHNlSFJPdUxMWmVrdjVnUyIsImp0aSI6ImIyMjNkMzgwZDZmM2JmOGFkMWMwMTdkYTZiYThhNTE0YjQ2N2FlOTBhZmNiOWU3YTMxODc2ZThjMDAyNWFkNWVjNWRjMzU0ZmQ1Nzg0OGM4IiwiaWF0IjoxNjMxMjA3MDQ3LCJuYmYiOjE2MzEyMDcwNDcsImV4cCI6MTYzMTIxMDY0Nywic3ViIjoiIiwic2NvcGVzIjpbXX0.M9T3vjzEh-rvyzVx5KL4YR7uY_cL7v0K7CN6lUwEFV5YkD-psS6_L9dgNUEpA1JpSt_wKgKOgdv7Be7ouh5cumgB4vfUcoMsBJy0vDUYlFs7AHVwzT8NmLhgNdQQzzF0MA8ggBCypDaRAG8Z98GfZPeO73ivIgHh_Y2Ctv-2pLO5Oq6oKvB7T82H09I-55Ga_DBxUDW8Qe3cBTUjYgyGotuKyV2osme0RBSBORoo8CCE59e3LQ6oMuk9Tau3Tv5q8WSZ6XWzYMmHfTd8v2K_0OZi7SxgEM_xZm1C8te4d75hST689IcFDskE9jLK7QVhlaik7r0fYti7u-LYuWseWQ " GET https://api.petfinder.com/v2/animals
