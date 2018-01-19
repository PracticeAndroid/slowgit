package com.miuty.slowgit.data.model;


import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Repo {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

}
