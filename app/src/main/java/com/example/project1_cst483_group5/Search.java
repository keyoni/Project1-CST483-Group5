package com.example.project1_cst483_group5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
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
    public Button searchBtn;
    public Spinner typeSpinner;
    public Spinner ageSpinner;
    public Spinner genderSpinner;
    public ImageView favAdd;


    //TODO: Return this an a thing later??
    List<Animal> animalList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String auth = getIntent().getStringExtra(ACTIVITY_LABEL_AUTH);

        favBtn = findViewById(R.id.btnFavSearchPage);
        searchBtn = findViewById(R.id.btnSearch);

        typeSpinner = findViewById(R.id.spType);
        ageSpinner = findViewById(R.id.spAge);
        genderSpinner = findViewById(R.id.spGender);

        populateSpinners();

        recyclerView = (RecyclerView)findViewById(R.id.rvSearch);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        //Default list an animals to populate the page first
        getBasicAnimals(auth);

//        favAdd = findViewById(R.id.ivFav);
//        favEvent(this);



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFilteredAnimals(auth);
            }
        });


        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toFavoritesPage(auth);
            }

        });
        
        
//        favAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Search.this, "CLICK ON", Toast.LENGTH_SHORT).show();
//            }
//        });




    }

    public void favEvent(Context context)
    {
        Log.d("API TEST", "In FAV EVENT");
        Toast.makeText(Search.this, "CLICK ON", Toast.LENGTH_SHORT).show();
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


        //ToDo: Maybe move out of this method?
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvSearch);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//

        // Call<AnimalResults> basicAnimalCall = petFinderApi.getBasicAnimalList(" Bearer " + auth);
      Call<AnimalResults> basicAnimalCall = PetFinderClient.getInstance().petFinderApi.getBasicAnimalList(" Bearer " + auth);
        Log.d("API TEST", "hello");
            basicAnimalCall.enqueue(new Callback <AnimalResults>(){
                @Override
                public void onResponse(Call<AnimalResults> call, Response<AnimalResults> response) {
                    Log.d("API TEST", "inside enqueue");
                    if (!response.isSuccessful()) {
                        Log.d("API TEST","Code: " + response.code());
                        return;
                    }

                    AnimalResults animalResults = response.body();
                    animalList =  animalResults.animals;
                    AnimalAdapter adapter = new AnimalAdapter(generateAnimalList());

                    recyclerView.setAdapter(adapter);

                    Animal tempAnimal;
                    tempAnimal = animalList.get(0);
                    Log.d("API TEST",""+ tempAnimal.toString());

                }

                @Override
                public void onFailure(Call<AnimalResults> call, Throwable t) {
                    Log.d("API TEST", "hello failure");
                    Log.d("API TEST", t.getMessage());

                }
            });

        }

    public void getFilteredAnimals(String auth) {
        Log.d("API TEST", auth);
        Log.d("API TEST", "In FILTERED ANIMALs");


        //ToDo: Maybe move out of this method?
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvSearch);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//

        // Call<AnimalResults> basicAnimalCall = petFinderApi.getBasicAnimalList(" Bearer " + auth);
        Call<AnimalResults> filteredAnimalCall = PetFinderClient.getInstance().petFinderApi.getFilteredAnimalList(" Bearer " + auth,"Female","Cat",null);
        Log.d("API TEST", "hello CALL");
        filteredAnimalCall.enqueue(new Callback <AnimalResults>(){
            @Override
            public void onResponse(Call<AnimalResults> call, Response<AnimalResults> response) {
                Log.d("API TEST", "inside FILTERED enqueue");
                if (!response.isSuccessful()) {
                    Log.d("API TEST","Code: " + response.code());
                    return;
                }

                AnimalResults animalResults = response.body();
                animalList =  animalResults.animals;
                AnimalAdapter adapter = new AnimalAdapter(generateAnimalList());

                recyclerView.setAdapter(adapter);

                Animal tempAnimal;
                tempAnimal = animalList.get(0);
                Log.d("API TEST",""+ tempAnimal.toString());

            }

            @Override
            public void onFailure(Call<AnimalResults> call, Throwable t) {
                Log.d("API TEST", "hello failure");
                Log.d("API TEST", t.getMessage());

            }
        });

    }

    private List<AnimalViewModel> generateAnimalList() {
        List<AnimalViewModel> animalViewModelList = new ArrayList<>();
        Log.d("API TEST", "ANIMAL LIST");

        for( Animal animal: animalList) {
            //simpleViewModelList.add(new AnimalViewModel(String.format(Locale.US, "This is item %d", i)));
            animalViewModelList.add(new AnimalViewModel(animal.getmName(), animal.getmType(), animal.getmAge(), animal.getmGender()));
        }

        return animalViewModelList;
    }


    private void populateSpinners(){
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        typeSpinner.setAdapter(typeAdapter);

        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,
                R.array.age_array, android.R.layout.simple_spinner_item);

        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ageSpinner.setAdapter(ageAdapter);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(genderAdapter);

    }

}


        //curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJ1cEliOUxHMFA2eWNmRTdlQVBWOTNoc3JTR0ZGQnZ3ZWp0MHNlSFJPdUxMWmVrdjVnUyIsImp0aSI6ImIyMjNkMzgwZDZmM2JmOGFkMWMwMTdkYTZiYThhNTE0YjQ2N2FlOTBhZmNiOWU3YTMxODc2ZThjMDAyNWFkNWVjNWRjMzU0ZmQ1Nzg0OGM4IiwiaWF0IjoxNjMxMjA3MDQ3LCJuYmYiOjE2MzEyMDcwNDcsImV4cCI6MTYzMTIxMDY0Nywic3ViIjoiIiwic2NvcGVzIjpbXX0.M9T3vjzEh-rvyzVx5KL4YR7uY_cL7v0K7CN6lUwEFV5YkD-psS6_L9dgNUEpA1JpSt_wKgKOgdv7Be7ouh5cumgB4vfUcoMsBJy0vDUYlFs7AHVwzT8NmLhgNdQQzzF0MA8ggBCypDaRAG8Z98GfZPeO73ivIgHh_Y2Ctv-2pLO5Oq6oKvB7T82H09I-55Ga_DBxUDW8Qe3cBTUjYgyGotuKyV2osme0RBSBORoo8CCE59e3LQ6oMuk9Tau3Tv5q8WSZ6XWzYMmHfTd8v2K_0OZi7SxgEM_xZm1C8te4d75hST689IcFDskE9jLK7QVhlaik7r0fYti7u-LYuWseWQ " GET https://api.petfinder.com/v2/animals

  //  Gson gson = new GsonBuilder().serializeNulls().create();
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
