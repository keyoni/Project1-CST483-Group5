package com.example.project1_cst483_group5;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PetFinderApi {

    @Headers({
            "client_id: upIb9LG0P6ycfE7eAPV93hsrSGFFBvwejt0seHROuLLZekv5gS",
            "client_secret: mMSNJSUxfdqXKyrUvDwEjYCg47Ar5znXPq4mhzJh"
    })
    @POST("oauth2/token")
     Call<AuthApi> Auth();

}
