package com.miuty.slowgit.data.repository.local;

import javax.inject.Inject;

/**
 * Created by Asus on 1/13/2018.
 */

public class AuthLocalServiceImpl implements AuthLocalService {

    private AppDatabase appDatabase;

    @Inject
    public AuthLocalServiceImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }
}
