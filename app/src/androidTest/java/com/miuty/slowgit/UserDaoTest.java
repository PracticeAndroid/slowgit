package com.miuty.slowgit;

import android.support.test.runner.AndroidJUnit4;

import com.miuty.slowgit.data.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class UserDaoTest extends BaseAppDatabaseTest {

    @Test
    public void insertAndGetUserById() {
        User createdUser = createUser();
        appDatabase.getUserDao().insert(createdUser);

        appDatabase.getUserDao().getUser(1L)
                .test()
                .assertValue(user -> Objects.equals(user.getId(), createdUser.getId())
                        && user.getUsername().equals(createdUser.getUsername()));
    }
}
