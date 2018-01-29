package com.miuty.slowgit.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Asus on 1/28/2018.
 */

@Getter
@Setter
public class Label {

    @SerializedName("id")
    private int id;

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    @SerializedName("color")
    private String color;
}
