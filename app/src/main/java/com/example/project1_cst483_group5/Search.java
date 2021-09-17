package com.example.project1_cst483_group5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1_cst483_group5.db.PetViewModel;
import com.example.project1_cst483_group5.db.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The type Search.
 */
public class Search extends AppCompatActivity {

    /**
     * The constant ACTIVITY_LABEL_AUTH.
     */
    public static final String ACTIVITY_LABEL_AUTH = "SEARCH_COM_PROJ1_G5_AUTH";
    /**
     * The constant ACTIVITY_LABEL_ID.
     */
    public static final String ACTIVITY_LABEL_ID = "SEARCH_COM_PROJ1_G5_ID";
    private Integer userId;
    /**
     * The Fav btn.
     */
    public Button favBtn;
    /**
     * The Search btn.
     */
    public Button searchBtn;
    /**
     * The Type spinner.
     */
    public Spinner typeSpinner;
    /**
     * The Age spinner.
     */
    public Spinner ageSpinner;
    /**
     * The Gender spinner.
     */
    public Spinner genderSpinner;
    /**
     * The Fav add.
     */
    public ImageView favAdd;
    /**
     * The Pet vm.
     */
    public PetViewModel petVM;
    private String typeChoice;
    private String ageChoice;
    private String genderChoice;
    /**
     * The Yip yip btn.
     */
    public Button yipYipBtn;
    private Button logoutBtn;
    private SingleAnimal animalResult;
    private Animal randomAnimal;

    /**
     * The Animal list.
     */
//TODO: Return this an a thing later??
    List<Animal> animalList;
    /**
     * The Recycler view.
     */
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String auth = getIntent().getStringExtra(ACTIVITY_LABEL_AUTH);
        userId = getIntent().getIntExtra(ACTIVITY_LABEL_ID, 0);
        petVM = new ViewModelProvider(this).get(PetViewModel.class);


        favBtn = findViewById(R.id.btnFavSearchPage);
        searchBtn = findViewById(R.id.btnSearch);
        yipYipBtn = findViewById(R.id.btnYipYip);

        typeSpinner = findViewById(R.id.spType);
        ageSpinner = findViewById(R.id.spAge);
        genderSpinner = findViewById(R.id.spGender);

        populateSpinners();
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeChoice = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ageChoice = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genderChoice = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        recyclerView = findViewById(R.id.rvSearch);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Default list an animals to populate the page first
        getBasicAnimals(auth);

//        favAdd = findViewById(R.id.ivFav);
//        favEvent(this);

        yipYipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yipYip(auth, userId, this);
                //showAlertDialog(view.getContext(), this);
            }
        });


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

        logoutBtn = findViewById(R.id.btnLogoutSearchPage);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSettingsPage();
//                Intent intent = new Intent(Search.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

//        favAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Search.this, "CLICK ON", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

//    private void showAlertDialog(Context context, View.OnClickListener onClickListener) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("More Info!");
//        builder.setMessage(randomAnimal.getmName());
//        builder.show();
//

    //}

    /**
     * To settings page.
     */
    public void toSettingsPage() {
        Intent intent = Settings.getIntent(getApplicationContext(), userId);
        intent.putExtra("SETTINGS_COM_PROJ1_G5_ID", userId);
        startActivity(intent);
    }

    /**
     * Gets intent.
     *
     * @param context the context
     * @param auth    the auth
     * @return the intent
     */
    public static Intent getIntent(Context context, String auth) {
        Intent intent = new Intent(context, Search.class);

        intent.putExtra(Search.ACTIVITY_LABEL_AUTH, auth);

        return intent;

    }

    /**
     * To favorites page.
     *
     * @param auth the auth
     */
    public void toFavoritesPage(String auth) {
        Intent intent = Favorites.getIntent(getApplicationContext(), auth);
        intent.putExtra("FAVORITES_COM_PROJ1_G5_AUTH", auth);
        intent.putExtra("FAVORITES_COM_PROJ1_G5_ID", userId);
        startActivity(intent);
    }

    /**
     * Yip yip.
     *
     * @param auth            the auth
     * @param userId          the user id
     * @param onClickListener the on click listener
     */
    public void yipYip(String auth, Integer userId, View.OnClickListener onClickListener) {

        Random randId = new Random();

        // look away and send help
        int upperbound = 42;
        int base = 52992500;

        int int_rand = randId.nextInt(upperbound);


        Call<SingleAnimal> animalCall = PetFinderClient.getInstance().petFinderApi.getAnimalById(" Bearer " + auth, base + int_rand);
        //Call<SingleAnimal> animalCall = petFinderApi.getAnimalById(" Bearer " + auth, base + int_rand);
        animalCall.enqueue(new Callback<SingleAnimal>() {
            @Override
            public void onResponse(Call<SingleAnimal> call, Response<SingleAnimal> response) {
                Log.d("API TEST", "inside SINGLE ANIMAL");
                if (!response.isSuccessful()) {
                    Log.d("API TEST", "Code: " + response.code());
                    return;
                }

                animalResult = response.body();
                randomAnimal = animalResult.getAnimal();
                Log.d("API TEST", randomAnimal.getmName() + "");
                AlertDialog.Builder builder = new AlertDialog.Builder(Search.this);
                LayoutInflater inflater = LayoutInflater.from(Search.this);
                View dialogLayout = inflater.inflate(R.layout.custom_more_info, null);
                builder.setView(dialogLayout);
                TextView nameMoreInfo = dialogLayout.findViewById(R.id.tvNameMoreInfo);
                nameMoreInfo.setText((randomAnimal.getmName()));

                TextView ageMoreInfo = dialogLayout.findViewById(R.id.tvAgeMoreInfo);
                ageMoreInfo.setText((randomAnimal.getmAge()));

                TextView genderMoreInfo = dialogLayout.findViewById(R.id.tvGenderMoreInfo);
                genderMoreInfo.setText((randomAnimal.getmGender()));

                TextView descMoreInfo = dialogLayout.findViewById(R.id.tvDescMoreInfo);
                descMoreInfo.setText((randomAnimal.getmDescription()));

                TextView sizeMoreInfo = dialogLayout.findViewById(R.id.tvSizeMoreInfo);
                sizeMoreInfo.setText((randomAnimal.getmSize()));

                TextView statusMoreInfo = dialogLayout.findViewById(R.id.tvStatusMoreInfo);
                statusMoreInfo.setText((randomAnimal.getmStatus()));


                ImageView picture = dialogLayout.findViewById(R.id.ivPicture);
                if (randomAnimal.mPhoto.isEmpty()) {
                    Picasso.get().load(R.drawable.error_pic)
                            .resize(300, 300)
                            .centerCrop()
                            .into(picture);

                } else {
                    Picasso.get().load(randomAnimal.mPhoto.get(0).full)
                            .resize(300, 300)
                            .centerCrop()
                            .error(R.drawable.error_pic)
                            .into(picture);
                }

                builder.show();
//            SingleAnimal results = response.body();
//            randomAnimal = results.getAnimal();
//            if (randomAnimal == null) {
//                Log.d("API TEST", "remember when APPA got kidnapped");
//            } else {
//
//                Log.d("API TEST", "" +randomAnimal.getmName());
//                //TODO: Maybe make a more info page from this only
//                AlertDialog.Builder builder = new AlertDialog.Builder(Search.this);
//                builder.setTitle("More Info!");
//                builder.setMessage(randomAnimal.getmName());
//                builder.show();
//            }


            }

            @Override
            public void onFailure(Call<SingleAnimal> call, Throwable t) {
                Log.d("API TEST", "hello failure");
                Log.d("API TEST", t.getMessage());

            }
        });


    }

    /**
     * Gets basic animals.
     *
     * @param auth the auth
     */
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
        basicAnimalCall.enqueue(new Callback<AnimalResults>() {
            @Override
            public void onResponse(Call<AnimalResults> call, Response<AnimalResults> response) {
                Log.d("API TEST", "inside enqueue");
                if (!response.isSuccessful()) {
                    Log.d("API TEST", "Code: " + response.code());
                    return;
                }


                AnimalResults animalResults = response.body();
                animalList = animalResults.animals;
                //AnimalAdapter adapter = new AnimalAdapter(generateAnimalList());
                AnimalAdapter favAnimaladapter = new AnimalAdapter(generateAnimalList(), petVM, userId, auth, Search.this);

                recyclerView.setAdapter(favAnimaladapter);


                Animal tempAnimal;
                tempAnimal = animalList.get(0);
                Log.d("API TEST", "" + tempAnimal.toString());

            }

            @Override
            public void onFailure(Call<AnimalResults> call, Throwable t) {
                Log.d("API TEST", "hello failure");
                Log.d("API TEST", t.getMessage());

            }
        });

    }

    /**
     * Gets filtered animals.
     *
     * @param auth the auth
     */
    public void getFilteredAnimals(String auth) {
        Log.d("API TEST", auth);
        Log.d("API TEST", "In FILTERED ANIMALs");


        //ToDo: Maybe move out of this method?
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvSearch);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//

        // Call<AnimalResults> basicAnimalCall = petFinderApi.getBasicAnimalList(" Bearer " + auth);
        if (genderChoice.equals("Any")) {
            genderChoice = null;
        }
        if (typeChoice.equals("Any")) {
            typeChoice = null;
        }
        if (ageChoice.equals("Any")) {
            ageChoice = null;
        }

        Call<AnimalResults> filteredAnimalCall = PetFinderClient.getInstance().petFinderApi.getFilteredAnimalList(" Bearer " + auth, genderChoice, typeChoice, ageChoice);
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
                animalList = animalResults.animals;
                AnimalAdapter adapter = new AnimalAdapter(generateAnimalList(), petVM, userId, auth, Search.this);

                recyclerView.setAdapter(adapter);

                Animal tempAnimal;
                tempAnimal = animalList.get(0);
                Log.d("API TEST", "" + tempAnimal.toString());

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

        for (Animal animal : animalList) {
            //simpleViewModelList.add(new AnimalViewModel(String.format(Locale.US, "This is item %d", i)));
            animalViewModelList.add(new AnimalViewModel(animal.getmID(), animal.getmName(), animal.getmType(), animal.getmAge(), animal.getmGender()));
        }

        return animalViewModelList;
    }


    private void populateSpinners() {
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
