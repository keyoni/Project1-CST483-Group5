package com.example.project1_cst483_group5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Favorites extends AppCompatActivity {

    public static final String ACTIVITY_LABEL_AUTH = "FAVORITES_COM_PROJ1_G5_AUTH";
    public Button searchBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites2);

        String auth = getIntent().getStringExtra(ACTIVITY_LABEL_AUTH);

        searchBtn = findViewById(R.id.btnSearchFavPage);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSearchPage(auth);
            }

        });
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