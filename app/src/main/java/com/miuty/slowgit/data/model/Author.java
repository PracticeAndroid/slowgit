package com.miuty.slowgit.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;
}
