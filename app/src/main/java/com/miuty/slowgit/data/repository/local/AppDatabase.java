package com.miuty.slowgit.data.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.miuty.slowgit.data.dao.FeedDao;
import com.miuty.slowgit.data.model.Feed;
import com.miuty.slowgit.data.model.User;
import com.miuty.slowgit.data.dao.UserDao;

@Database(entities = {User.class, Feed.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

    public abstract FeedDao getFeedDao();
}
