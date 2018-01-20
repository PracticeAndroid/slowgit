package com.miuty.slowgit.data.repository.profile.local;

import com.miuty.slowgit.data.model.profile.BasicProfile;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by igneel on 1/20/2018.
 */

public class ProfileLocalServiceImpl implements ProfileLocalService {

    @Inject
    public ProfileLocalServiceImpl() {

    }

    @Override
    public Observable<BasicProfile> getCurrentProfile() {
        return null;
    }
}
