package com.miuty.slowgit.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Actor {

    @SerializedName("id")
    private long id;

    @SerializedName("login")
    private String login;

    @SerializedName("display_login")
    private String displayLogin;

    @SerializedName("gravatar_id")
    private String gravatarId;

    @SerializedName("url")
    private String url;

    @SerializedName("avatar_url")
    private String avatarUrl;
}
