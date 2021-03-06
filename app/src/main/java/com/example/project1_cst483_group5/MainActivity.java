package com.example.project1_cst483_group5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project1_cst483_group5.db.User;
import com.example.project1_cst483_group5.db.UserViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The User vm.
     */
    public UserViewModel userVM;
    /**
     * The Login btn.
     */
    public Button loginBtn, /**
     * The Create acc btn.
     */
    createAccBtn;
    /**
     * The Authorization.
     */
    AuthApi authorization;
    /**
     * The User id.
     */
    Integer userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId = 0;

        userVM = new ViewModelProvider(this).get(UserViewModel.class);
        loginBtn = findViewById(R.id.btnLogin);
        createAccBtn = findViewById(R.id.btnCreatePage);

        EditText user, password;
        user = findViewById(R.id.etLoginUsername);
        password = findViewById(R.id.etLoginPassword);

        int count = userVM.getUserCount();
        createUsers(count);


        getAuth();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsersByUsernameAndPassword(user, password);

            }
        });

        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnCreatePage) {
                    Intent i = new Intent(getApplicationContext(), CreateAnAccount.class);
                    startActivity(i);
                }
            }
        });


    }


    /**
     * Create users.
     *
     * @param count the count
     */
    public void createUsers(int count) {
        Log.d("API TEST", count + " : number of Users");
        if (count != 0) {
            for (int i = 0; i < 3; i++) {
                User tempUser = new User("User" + (i + 1), "user" + (i + 1), "password" + (i + 1));
                userVM.insert(tempUser);
            }
        }

    }

    /**
     * Gets users by username and password.
     *
     * @param username the username
     * @param password the password
     */
    public void getUsersByUsernameAndPassword(EditText username, EditText password) {
        String user, pass;
        user = username.getText().toString();
        pass = password.getText().toString();
        List<User> userLogin = userVM.getUsersByUsernameAndPassword(user, pass);

        if (userLogin != null) {
            userId = userLogin.get(0).getMUserID();
            login();
        } else {
            Toast.makeText(this, "Bad username/password", Toast.LENGTH_LONG).show();
        }
    }

    private void getAuth() {
        Log.d("API TEST", "helloooo");

        AuthRequest authRequest = new AuthRequest("client_credentials",
                "upIb9LG0P6ycfE7eAPV93hsrSGFFBvwejt0seHROuLLZekv5gS",
                "PNUfd2VyDeiulTuuwpOAv0pvYo3fO8LS1e9eHHfu");

        Call<AuthApi> authCall = PetFinderClient.getInstance().getPetFinderApi().Auth(authRequest);


        authCall.enqueue(new Callback<AuthApi>() {
            @Override
            public void onResponse(Call<AuthApi> call, Response<AuthApi> response) {

                if (!response.isSuccessful()) {
                    Log.d("API TEST", "Code: " + response.code());
                    return;
                }
                authorization = response.body();

                Log.d("API TEST", authorization.getAccess_token());
            }

            @Override
            public void onFailure(Call<AuthApi> call, Throwable t) {

            }
        });

    }

    /**
     * Login.
     */
    public void login() {
        Intent intent = Favorites.getIntent(getApplicationContext(), authorization.getAccess_token());
        intent.putExtra("FAVORITES_COM_PROJ1_G5_AUTH", authorization.getAccess_token());
        intent.putExtra("FAVORITES_COM_PROJ1_G5_ID", userId);
        startActivity(intent);

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
}


//        Gson gson = new GsonBuilder().serializeNulls().create();
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.petfinder.com/v2/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//        PetFinderApi petFinderApi = retrofit.create(PetFinderApi.class);
