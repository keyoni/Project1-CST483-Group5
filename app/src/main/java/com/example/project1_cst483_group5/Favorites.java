package com.example.project1_cst483_group5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project1_cst483_group5.db.Pet;
import com.example.project1_cst483_group5.db.PetViewModel;

import java.util.ArrayList;
import java.util.List;

public class Favorites extends AppCompatActivity {

    public static final String ACTIVITY_LABEL_AUTH = "FAVORITES_COM_PROJ1_G5_AUTH";
    //public static final String ACTIVITY_LABEL_ID = "FAVORITES_COM_PROJ1_G5_ID";
    public Button searchBtn, logoutBtn;
    public PetViewModel petVM;
    RecyclerView recyclerView;
    public List<Pet> pets;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites2);

        recyclerView = (RecyclerView)findViewById(R.id.rvFavorites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        petVM = new ViewModelProvider(this).get(PetViewModel.class);

        String auth = getIntent().getStringExtra(ACTIVITY_LABEL_AUTH);
        //Integer userId = (Integer) getIntent().getIntExtra(ACTIVITY_LABEL_ID);

        searchBtn = findViewById(R.id.btnSearchFavPage);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSearchPage(auth);
            }

        });
//        logoutBtn = findViewById(R.id.btnLogoutSearchPage);
//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Favorites.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        PetListAdapter petAdapter = new PetListAdapter(generateAnimalList(),petVM);

        recyclerView.setAdapter(petAdapter);
    }

    private List<PetListViewModel> generateAnimalList() {
        List<PetListViewModel> petListViewModelList = new ArrayList<>();
        Log.d("API TEST", "ANIMAL LIST");

        pets = petVM.getPetsByUserID(1);
        if(pets == null){
            Toast.makeText(Favorites.this, "Search to add pets", Toast.LENGTH_SHORT).show();

        } else {

            for (Pet animal : pets) {
                //simpleViewModelList.add(new AnimalViewModel(String.format(Locale.US, "This is item %d", i)));
                petListViewModelList.add(new PetListViewModel(animal.getMPetID(),
                        animal.getMName(),
                        animal.getMType(),
                        animal.getMGender()));
            }
        }

        return petListViewModelList;
    }


    public static Intent getIntent(Context context, String auth) {
        Intent intent = new Intent(context, Favorites.class);

        intent.putExtra(Favorites.ACTIVITY_LABEL_AUTH,auth);

        return intent;

    }

    public void toSearchPage(String auth) {
        Intent intent = Search.getIntent(getApplicationContext(),auth);
        intent.putExtra("FAVORITES_COM_PROJ1_G5_AUTH",auth);
        startActivity(intent);
    }

}