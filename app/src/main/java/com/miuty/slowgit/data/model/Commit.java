package com.miuty.slowgit.data.model;


import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Commit {

    @SerializedName("sha")
    private String sha;

    @SerializedName("author")
    private Author author;

    @SerializedName("update")
    private String update;

    @SerializedName("distinct")
    public boolean distinct;

    @SerializedName("url")
    private String url;

}
