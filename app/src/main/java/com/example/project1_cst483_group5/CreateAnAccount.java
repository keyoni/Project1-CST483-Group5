package com.example.project1_cst483_group5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.project1_cst483_group5.db.User;
import com.example.project1_cst483_group5.db.UserViewModel;

public class CreateAnAccount extends AppCompatActivity {
    public UserViewModel userVM;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userVM = new ViewModelProvider(this).get(UserViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);
        submit = findViewById(R.id.btnCreateAccount);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText cname, cpassword, cemail;
                String name, password, username;

                cemail = findViewById(R.id.etLoginEmail);
                cname = findViewById(R.id.etFirstName);
                cpassword = findViewById(R.id.etCreatePassword);
                name = cname.getText().toString();
                password = cpassword.getText().toString();
                username = cemail.getText().toString();
                User newUser = new User(name, username, password);
                userVM.insert(newUser);
            }
        });
    }


    public static Intent getIntent(Context context, String auth) {
        Intent intent = new Intent(context, CreateAnAccount.class);
        return intent;

    }
}