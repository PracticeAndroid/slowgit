package com.miuty.slowgit.repo;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Asus on 1/9/2018.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private static final String TAG = "LoginRepositoryImpl";

    @Inject
    public LoginRepositoryImpl() {

    }

    @Override
    public void insert() {
        Log.d(TAG, "insert() called");
    }
}
