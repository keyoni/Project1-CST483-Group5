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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project1_cst483_group5.db.Pet;
import com.example.project1_cst483_group5.db.PetViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Favorites.
 */
public class Favorites extends AppCompatActivity {

    /**
     * The constant ACTIVITY_LABEL_AUTH.
     */
    public static final String ACTIVITY_LABEL_AUTH = "FAVORITES_COM_PROJ1_G5_AUTH";
    /**
     * The constant ACTIVITY_LABEL_ID.
     */
    public static final String ACTIVITY_LABEL_ID = "FAVORITES_COM_PROJ1_G5_ID";
    /**
     * The Search btn.
     */
    public Button searchBtn;
    /**
     * The Refresh btn.
     */
    public Button refreshBtn;
    /**
     * The Logout btn.
     */
    public Button logoutBtn;
    /**
     * The Pet vm.
     */
    public PetViewModel petVM;
    /**
     * The Recycler view.
     */
    RecyclerView recyclerView;
    /**
     * The Pets.
     */
    public List<Pet> pets;
    private Integer userId;
    private String auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites2);

        recyclerView = findViewById(R.id.rvFavorites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        petVM = new ViewModelProvider(this).get(PetViewModel.class);

        auth = getIntent().getStringExtra(ACTIVITY_LABEL_AUTH);
        userId = getIntent().getIntExtra(ACTIVITY_LABEL_ID, 0);
        //userId = 1;

        refreshBtn = findViewById(R.id.btnRefresh);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateAnimalList();
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
                toSettingsPage();
            }

        });


    }

    private List<PetListViewModel> generateAnimalList() {
        List<PetListViewModel> petListViewModelList = new ArrayList<>();

        pets = petVM.getPetsByUserID(userId);

        if (pets == null || pets.isEmpty()) {
            Toast.makeText(Favorites.this, "Refresh to See List", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(Favorites.this, pets.get(0).getMName() + " is here", Toast.LENGTH_SHORT).show();

            for (Pet animal : pets) {
                petListViewModelList.add(new PetListViewModel(animal.getMID(),
                        animal.getMName(),
                        animal.getMType(),
                        animal.getMGender()));
            }
        }
        LayoutInflater inflater = getLayoutInflater();

        PetListAdapter petAdapter = new PetListAdapter(petListViewModelList, petVM, auth, Favorites.this);
        recyclerView.setAdapter(petAdapter);

        return petListViewModelList;
    }


    /**
     * Gets intent.
     *
     * @param context the context
     * @param auth    the auth
     * @return the intent
     */
    public static Intent getIntent(Context context, String auth) {
        Intent intent = new Intent(context, Favorites.class);

        intent.putExtra(Favorites.ACTIVITY_LABEL_AUTH, auth);

        return intent;

    }

    /**
     * To search page.
     *
     * @param auth the auth
     */
    public void toSearchPage(String auth) {
        Intent intent = Search.getIntent(getApplicationContext(), auth);
        intent.putExtra("SEARCH_COM_PROJ1_G5_AUTH", auth);
        intent.putExtra("SEARCH_COM_PROJ1_G5_ID", userId);
        startActivity(intent);


    }

    /**
     * To settings page.
     */
    public void toSettingsPage() {
        Intent intent = Settings.getIntent(getApplicationContext(), userId);
        intent.putExtra("SETTINGS_COM_PROJ1_G5_ID", userId);
        startActivity(intent);
    }

}