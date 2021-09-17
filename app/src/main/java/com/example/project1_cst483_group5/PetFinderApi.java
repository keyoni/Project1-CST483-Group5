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
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * The interface Pet finder api.
 */
public interface PetFinderApi {


    /**
     * Auth call.
     *
     * @param authRequest the auth request
     * @return the call
     */
    @POST("oauth2/token")
    Call<AuthApi> Auth(@Body AuthRequest authRequest);


    /**
     * Gets basic animal list.
     *
     * @param auth the auth
     * @return the basic animal list
     */
    @GET("animals")
    Call<AnimalResults> getBasicAnimalList(@Header("Authorization") String auth);

    /**
     * Gets filtered animal list.
     *
     * @param auth   the auth
     * @param gender the gender
     * @param type   the type
     * @param age    the age
     * @return the filtered animal list
     */
    @GET("animals")
    Call<AnimalResults> getFilteredAnimalList(@Header("Authorization") String auth,
                                              @Query("gender") String gender,
                                              @Query("type") String type,
                                              @Query("age") String age);

    /**
     * Gets animal by id.
     *
     * @param auth the auth
     * @param id   the id
     * @return the animal by id
     */
    @GET("animals/{id}")
    Call<SingleAnimal> getAnimalById(@Header("Authorization") String auth,
                                     @Path("id") Integer id);


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
