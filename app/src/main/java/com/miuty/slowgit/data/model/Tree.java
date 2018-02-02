package com.miuty.slowgit.data.model;

import com.google.gson.annotations.SerializedName;

public class Tree {

    @SerializedName("sha")
    private String sha;

    @SerializedName("url")
    private String url;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
