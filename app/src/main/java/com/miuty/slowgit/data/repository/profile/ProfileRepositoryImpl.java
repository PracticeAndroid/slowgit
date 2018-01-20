package com.miuty.slowgit.data.repository.profile;

import com.miuty.slowgit.data.model.profile.BasicProfile;
import com.miuty.slowgit.data.repository.profile.local.ProfileLocalService;
import com.miuty.slowgit.data.repository.profile.remote.ProfileRemoteService;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by igneel on 1/20/2018.
 */

public class ProfileRepositoryImpl implements ProfileRepository{

    ProfileLocalService profileLocalService;
    ProfileRemoteService profileRemoteService;

    @Inject
    public ProfileRepositoryImpl(ProfileLocalService profileLocalService, ProfileRemoteService profileRemoteService){
        this.profileRemoteService = profileRemoteService;
        this.profileLocalService = profileLocalService;
    }

    @Override
    public Observable<BasicProfile> getBasicProfile(String loginId){
        return profileRemoteService.getBasicProfile(loginId);
    }

}
