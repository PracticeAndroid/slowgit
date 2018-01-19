package com.miuty.slowgit.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payload {

    @PrimaryKey
    @SerializedName("push_id")
    private long pushId;

    @SerializedName("size")
    private int size;

    @SerializedName("distinct_size")
    private int distinctSize;

    @SerializedName("ref")
    private String ref;

    @SerializedName("head")
    private String head;

    @SerializedName("before")
    private String before;

    @SerializedName("commits")
    private List<Commit> commits;

    // forkee
    @SerializedName("forkee")
    private Forkee forkee;

    // action starred
    @SerializedName("action")
    private String action;

    // created event
//    @SerializedName("ref")
//    private String ref;

    @SerializedName("ref_type")
    private String refType;

    @SerializedName("master_branch")
    private String masterBranch;

    @SerializedName("description")
    private String description;

    @SerializedName("pusher_type")
    private String pusherType;

}
