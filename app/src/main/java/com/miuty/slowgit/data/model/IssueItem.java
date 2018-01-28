package com.miuty.slowgit.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Asus on 1/28/2018.
 */

@Getter
@Setter
public class IssueItem {

    @SerializedName("title")
    private String title;

    @SerializedName("comments")
    private int comments;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updateAt;

    @SerializedName("body")
    private String body;

    @SerializedName("labels")
    private List<Label> labels;
}
