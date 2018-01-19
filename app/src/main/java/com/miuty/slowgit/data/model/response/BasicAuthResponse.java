package com.miuty.slowgit.data.model.response;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus on 1/12/2018.
 */
@Entity
public class BasicAuthResponse extends BaseDataResponse {

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("url")
    private String url;

    @SerializedName("token")
    private String token;

    @SerializedName("hashed_token")
    private String hashedToken;

    @SerializedName("token_last_eight")
    private String tokenLastEight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHashedToken() {
        return hashedToken;
    }

    public void setHashedToken(String hashedToken) {
        this.hashedToken = hashedToken;
    }

    public String getTokenLastEight() {
        return tokenLastEight;
    }

    public void setTokenLastEight(String tokenLastEight) {
        this.tokenLastEight = tokenLastEight;
    }
}
