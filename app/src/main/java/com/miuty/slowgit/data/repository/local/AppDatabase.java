package com.miuty.slowgit.data.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.miuty.slowgit.data.dao.FeedDao;
import com.miuty.slowgit.data.model.Actor;
import com.miuty.slowgit.data.model.Author;
import com.miuty.slowgit.data.model.Commit;
import com.miuty.slowgit.data.model.Feed;
import com.miuty.slowgit.data.model.Forkee;
import com.miuty.slowgit.data.model.Organization;
import com.miuty.slowgit.data.model.Owner;
import com.miuty.slowgit.data.model.Payload;
import com.miuty.slowgit.data.model.Repo;
import com.miuty.slowgit.data.model.User;
import com.miuty.slowgit.data.dao.UserDao;
import com.miuty.slowgit.data.model.response.BasicAuthResponse;

@Database(entities = {
        BasicAuthResponse.class, User.class,
//        Feed.class, Actor.class, Repo.class, Payload.class, Organization.class,
//        Author.class, Commit.class, Forkee.class, Owner.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

    public abstract FeedDao getFeedDao();
}
