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
    public static final String ACTIVITY_LABEL_ID = "FAVORITES_COM_PROJ1_G5_ID";
    public Button searchBtn;
    public Button refreshBtn;
    public  Button logoutBtn;
    public PetViewModel petVM;
    RecyclerView recyclerView;
    public List<Pet> pets;
    private Integer userId;
    private String auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites2);

        recyclerView = (RecyclerView)findViewById(R.id.rvFavorites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        petVM = new ViewModelProvider(this).get(PetViewModel.class);

        auth = getIntent().getStringExtra(ACTIVITY_LABEL_AUTH);
        userId = getIntent().getIntExtra(ACTIVITY_LABEL_ID,0);
        //userId = 1;

        refreshBtn = findViewById(R.id.btnRefresh);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                generateAnimalList();
//                PetListAdapter petAdapter = new PetListAdapter(generateAnimalList(),petVM);
//
//                recyclerView.setAdapter(petAdapter);

            }
        });
        searchBtn = findViewById(R.id.btnSearchFavPage);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSearchPage(auth);
            }

        });

        logoutBtn = findViewById(R.id.btnLogoutFavPage);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Favorites.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private List<PetListViewModel> generateAnimalList() {
        List<PetListViewModel> petListViewModelList = new ArrayList<>();
        Log.d("API TEST", "ANIMAL LIST" + userId);

        pets = petVM.getPetsByUserID(userId);

        //Toast.makeText(Favorites.this, pets.get(0).getMName() + " is here", Toast.LENGTH_SHORT).show();
        if(pets == null || pets.isEmpty()){
            Toast.makeText(Favorites.this, "Search to add pets", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(Favorites.this, pets.get(0).getMName() + " is here", Toast.LENGTH_SHORT).show();

            for (Pet animal : pets) {
                //simpleViewModelList.add(new AnimalViewModel(String.format(Locale.US, "This is item %d", i)));
                petListViewModelList.add(new PetListViewModel(animal.getMID(),
                        animal.getMName(),
                        animal.getMType(),
                        animal.getMGender()));
            }
        }

        PetListAdapter petAdapter = new PetListAdapter(petListViewModelList,petVM,auth,Favorites.this);
        //petAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(petAdapter);

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
        intent.putExtra("SEARCH_COM_PROJ1_G5_ID",userId);
        startActivity(intent);
    }

}