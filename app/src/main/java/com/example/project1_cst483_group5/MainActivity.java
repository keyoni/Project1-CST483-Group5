package com.example.project1_cst483_group5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up a click listener for the Create Account button on the welcome(home) page.
        View createAccountButton = findViewById(R.id.btnCreateAccount);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnCreateAccount){
            Intent i = new Intent(this, CreateAnAccount.class);
            startActivity(i);
        }
    }
}