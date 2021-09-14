package com.example.project1_cst483_group5;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PetFinderApi {


    @POST("oauth2/token")
     Call<AuthApi> Auth(@Body AuthRequest authRequest);



    @GET("animals")
    Call<AnimalResults> getBasicAnimalList( @Header("Authorization") String auth);

    @GET("animals")
    Call<AnimalResults> getFilteredAnimalList( @Header("Authorization") String auth,
                                               @Query("gender") String gender,
                                               @Query("type") String type,
                                               @Query("age") String age);





//    @FormUrlEncoded
//    @POST("oauth2/token")
//     Call<AuthApi> Auth(@Field("grant_type") String grant_type,
//                        @Field("client_id") String client_id,
//                        @Field ("client_secret") String client_secret);

//    @Headers({
//            "grant_type: client_credential",
//            "client_id: upIb9LG0P6ycfE7eAPV93hsrSGFFBvwejt0seHROuLLZekv5gS",
//            "client_secret: PNUfd2VyDeiulTuuwpOAv0pvYo3fO8LS1e9eHHfu"
//    })
//    @POST("oauth2/token")
//    Call<AuthApi> Auth();

//

}
