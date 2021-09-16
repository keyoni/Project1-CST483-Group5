package com.example.project1_cst483_group5;

import com.google.gson.annotations.SerializedName;

public class AuthApi {

    @SerializedName("token_type")
    public String  tokenType;
    @SerializedName("expires_in")
    public int expiresIn;
    @SerializedName("access_token")
    public  String access_token;




    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
