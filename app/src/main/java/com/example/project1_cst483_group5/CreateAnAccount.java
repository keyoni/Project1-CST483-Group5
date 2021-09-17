package com.example.project1_cst483_group5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * The type Create an account.
 */
public class CreateAnAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);


    }


    /**
     * Gets intent.
     *
     * @param context the context
     * @param auth    the auth
     * @return the intent
     */
    public static Intent getIntent(Context context, String auth) {
        Intent intent = new Intent(context, CreateAnAccount.class);
        return intent;

    }
}