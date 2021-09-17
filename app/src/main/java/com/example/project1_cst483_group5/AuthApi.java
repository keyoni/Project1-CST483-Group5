package com.example.project1_cst483_group5;

import com.google.gson.annotations.SerializedName;

/**
 * The type Auth api.
 */
public class AuthApi {

    /**
     * The Token type.
     */
    @SerializedName("token_type")
    public String tokenType;
    /**
     * The Expires in.
     */
    @SerializedName("expires_in")
    public int expiresIn;
    /**
     * The Access token.
     */
    @SerializedName("access_token")
    public String access_token;


    /**
     * Gets access token.
     *
     * @return the access token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * Sets access token.
     *
     * @param access_token the access token
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
