package com.miuty.slowgit.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.miuty.slowgit.util.FeedsType;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Feed {

    @PrimaryKey
    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private FeedsType type = FeedsType.NO_DEFINE;

    @SerializedName("actor")
    private Actor actor;

    @SerializedName("repo")
    private Repo repo;

    @SerializedName("payload")
    private Payload payload;

    @SerializedName("public")
    private boolean isPublic;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("org")
    private Organization organization;
}
