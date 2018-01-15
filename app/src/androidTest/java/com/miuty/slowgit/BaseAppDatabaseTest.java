package com.miuty.slowgit;


import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.miuty.slowgit.data.dao.model.User;
import com.miuty.slowgit.data.repository.local.AppDatabase;

import org.junit.After;
import org.junit.Before;

public abstract class BaseAppDatabaseTest {

    protected AppDatabase appDatabase;

    @Before
    public void init() throws Exception {
        appDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void destroy() throws Exception {
        appDatabase.close();
    }

    protected User createUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setAvatar("http://google.com");
        return user;
    }
}
