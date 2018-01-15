package com.miuty.slowgit.data.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.miuty.slowgit.data.dao.model.User;
import com.miuty.slowgit.data.dao.model.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();
}
