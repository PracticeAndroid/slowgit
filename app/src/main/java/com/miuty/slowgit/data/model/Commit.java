package com.miuty.slowgit.data.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Commit {

    @PrimaryKey
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

    @SerializedName("message")
    private String message;

}
