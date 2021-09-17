package com.example.project1_cst483_group5;

import com.google.gson.annotations.SerializedName;

/**
 * The type Auth request.
 */
public class AuthRequest {
    @SerializedName("grant_type")
    private String grantType;
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_secret")
    private String clientSecret;

    /**
     * Instantiates a new Auth request.
     *
     * @param grantType    the grant type
     * @param clientId     the client id
     * @param clientSecret the client secret
     */
    public AuthRequest(String grantType, String clientId, String clientSecret) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * Gets grant type.
     *
     * @return the grant type
     */
    public String getGrantType() {
        return grantType;
    }

    /**
     * Sets grant type.
     *
     * @param grantType the grant type
     */
    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets client secret.
     *
     * @return the client secret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Sets client secret.
     *
     * @param clientSecret the client secret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
