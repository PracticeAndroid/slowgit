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
public class Issues {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<IssueItem> items;
}
