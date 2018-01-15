package com.miuty.slowgit.data.dao.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import io.reactivex.Maybe;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user where id=:userId")
    Maybe<User> getUser(Long userId);

    @Update
    void update(User user);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
