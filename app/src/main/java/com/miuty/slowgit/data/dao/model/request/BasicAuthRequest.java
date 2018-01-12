package com.miuty.slowgit.data.dao.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asus on 1/12/2018.
 */

public class BasicAuthRequest {

    @SerializedName("client_id")
    private String clientId;

    @SerializedName("client_secret")
    private String clientSecrect;

    private List<String> scopes;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecrect() {
        return clientSecrect;
    }

    public void setClientSecrect(String clientSecrect) {
        this.clientSecrect = clientSecrect;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
}
