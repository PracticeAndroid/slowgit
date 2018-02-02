package com.miuty.slowgit.data.model.response;


import com.google.gson.annotations.SerializedName;
import com.miuty.slowgit.data.model.Commit;
import com.miuty.slowgit.data.model.User;

public class CommitResponse {

    @SerializedName("sha")
    private String sha;

    @SerializedName("commit")
    private Commit commit;

    @SerializedName("url")
    private String url;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("comments_url")
    private String commentsUrl;

    @SerializedName("author")
    private User author;

    @SerializedName("committer")
    private User committer;
}
