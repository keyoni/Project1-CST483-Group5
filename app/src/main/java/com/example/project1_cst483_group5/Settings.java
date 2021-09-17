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

import com.example.project1_cst483_group5.db.UserViewModel;

/**
 * The type Settings.
 */
public class Settings extends AppCompatActivity {
    /**
     * The Change username btn.
     */
    Button changeUsernameBtn;
    /**
     * The Logout btn.
     */
    Button logoutBtn;
    private EditText usernameChange;
    /**
     * The User vm.
     */
    UserViewModel userVM;


    /**
     * The User id.
     */
    Integer userId;

    /**
     * The constant ACTIVITY_LABEL_ID.
     */
    public static final String ACTIVITY_LABEL_ID = "SETTINGS_COM_PROJ1_G5_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        changeUsernameBtn = findViewById(R.id.btnChangeUser);
        logoutBtn = findViewById(R.id.btnLogoutSettings);
        usernameChange = findViewById(R.id.etChangeUser);

        userVM = new ViewModelProvider(this).get(UserViewModel.class);

        userId = getIntent().getIntExtra(ACTIVITY_LABEL_ID, 0);


        changeUsernameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeUsername();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    /**
     * Change username.
     */
    public void changeUsername() {
        userVM.changeUsername(usernameChange.getText().toString(), userId);
        Toast.makeText(getApplicationContext(), "Hello " + usernameChange.getText() + "!", Toast.LENGTH_LONG).show();

    }

    /**
     * Gets intent.
     *
     * @param context the context
     * @param userId  the user id
     * @return the intent
     */
    public static Intent getIntent(Context context, Integer userId) {
        Intent intent = new Intent(context, Settings.class);

        return intent;

    }
}