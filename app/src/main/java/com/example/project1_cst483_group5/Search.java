package com.example.project1_cst483_group5;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project1_cst483_group5.Favorites;

public class Search extends AppCompatActivity {

    public static final String ACTIVITY_LABEL_AUTH = "SEARCH_COM_PROJ1_G5_AUTH";
    public Button favBtn;

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
        Intent intent = new Intent(context, Search.class);

        intent.putExtra(Search.ACTIVITY_LABEL_AUTH, auth);

        return intent;

    }

    public void toFavoritesPage(String auth) {
        Intent intent = Favorites.getIntent(getApplicationContext(), auth);
        intent.putExtra("SEARCH_COM_PROJ1_G5_AUTH", auth);
        startActivity(intent);
    }


    public void getBasicAnimals(String auth) {


    }
}