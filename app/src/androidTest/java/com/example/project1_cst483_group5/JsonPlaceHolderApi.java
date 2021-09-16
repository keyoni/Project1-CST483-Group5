package com.example.project1_cst483_group5;


import java.util.List;

import Retro.Post;
import retrofit2.Call;
import retrofit2.http.GET;
public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts();

}