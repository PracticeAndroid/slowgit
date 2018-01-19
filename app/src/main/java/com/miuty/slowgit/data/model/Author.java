package com.miuty.slowgit.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Author {

    @PrimaryKey
    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;
}
