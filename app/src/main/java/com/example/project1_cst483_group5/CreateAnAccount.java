package com.example.project1_cst483_group5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project1_cst483_group5.db.User;
import com.example.project1_cst483_group5.db.UserViewModel;

import java.util.List;

/**
 * The type Create an account.
 */
public class CreateAnAccount extends AppCompatActivity {
    /**
     * The User vm.
     */
    public UserViewModel userVM;
    /**
     * The Create acc btn.
     */
    public Button createAccBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);

        userVM = new ViewModelProvider(this).get(UserViewModel.class);
        // this is for the CAA in this page.
        createAccBtn = findViewById(R.id.btnCreateAccount);
        EditText mUsername, mPassword, mName;
        mUsername = findViewById(R.id.etCreateEmail);
        mPassword = findViewById(R.id.etCreatePassword);
        mName = findViewById(R.id.etFirstName);

        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(mName, mUsername, mPassword);
                Intent i = new Intent(CreateAnAccount.this, MainActivity.class);
                startActivity(i);
            }
        });
    }


    /**
     * Create account.
     *
     * @param names    the names
     * @param username the username
     * @param password the password
     */
    public void createAccount(EditText names, EditText username, EditText password) {
        String mUsername, mPassword, mName;
        mUsername = username.getText().toString();
        mPassword = password.getText().toString();
        mName = names.getText().toString();
        User tempUser = new User(mName, mUsername, mPassword);
        if (tempUser != null) {
            userVM.insert(tempUser);
        }
    }


//    public static Intent getIntent(Context context, String auth) {
//        Intent intent = new Intent(context, CreateAnAccount.class);
//        return intent;
//
//    }
}