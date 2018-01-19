package com.miuty.slowgit.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payload {

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
}
