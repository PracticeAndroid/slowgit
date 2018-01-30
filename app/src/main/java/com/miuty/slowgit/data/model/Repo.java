package com.miuty.slowgit.data.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Repo {

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("fork")
    private boolean isFork;

    @SerializedName("size")
    private long size;

    @SerializedName("stargazers_count")
    private int stargazersCount;

    @SerializedName("watchers_count")
    private int watchersCount;

    @SerializedName("language")
    private String language;

    @SerializedName("forks_count")
    private int forksCount;

    @SerializedName("forks")
    private int forks;

    @SerializedName("created_at")
    private  Date createdAt;

    @SerializedName("updated_at")
    private  Date updatedAt;

    @SerializedName("pushed_at")
    private Date pushedAt;
}
