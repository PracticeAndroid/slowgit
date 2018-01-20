package com.miuty.slowgit.data.model.profile;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by igneel on 1/20/2018.
 */

@Getter
@Setter
@Entity
public class BasicProfile {

    @SerializedName("login")
    private String loginId;

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("url")
    private String url;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("followers_url")
    private String followersUrl;

    @SerializedName("following_url")
    private String followingUrl;

    @SerializedName("gists_url")
    private String gistsUrl;

    @SerializedName("starred_url")
    private String starredUrl;

    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;

    @SerializedName("organizations_url")
    private String organizationsUrl;

    @SerializedName("repos_url")
    private String reposUrl;

    @SerializedName("events_url")
    private String eventsUrl;

    @SerializedName("received_events_url")
    private String receivedEventsUrl;

    @SerializedName("location")
    private String location;

    @SerializedName("company")
    private String company;

    @SerializedName("email")
    private  String email;

    @SerializedName("name")
    private String name;

    @SerializedName("public_repos")
    private long publicRepos;

    @SerializedName("public_gists")
    private long publicGists;

    @SerializedName("followers")
    private long followers;

    @SerializedName("following")
    private long following;

    @SerializedName("created_at")
    private Date createAt;

}
