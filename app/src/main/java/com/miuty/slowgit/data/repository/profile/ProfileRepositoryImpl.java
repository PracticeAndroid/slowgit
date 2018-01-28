package com.miuty.slowgit.data.repository.profile;

import com.miuty.slowgit.data.model.Repo;
import com.miuty.slowgit.data.model.profile.BasicProfile;
import com.miuty.slowgit.data.repository.profile.local.ProfileLocalService;
import com.miuty.slowgit.data.repository.profile.local.ProfileLocalServiceImpl;
import com.miuty.slowgit.data.repository.profile.remote.ProfileRemoteService;
import com.miuty.slowgit.data.repository.profile.remote.ProfileRemoteServiceImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by igneel on 1/20/2018.
 */

public class ProfileRepositoryImpl implements ProfileRepository {

    private ProfileLocalService profileLocalService;
    private ProfileRemoteService profileRemoteService;

    @Inject
    public ProfileRepositoryImpl(ProfileLocalServiceImpl profileLocalService, ProfileRemoteServiceImpl profileRemoteService) {
        this.profileRemoteService = profileRemoteService;
        this.profileLocalService = profileLocalService;
    }

    @Override
    public Observable<BasicProfile> getBasicProfile(String loginId) {
        return profileRemoteService.getBasicProfile(loginId);
    }

    @Override
    public Observable<ResponseBody> getContributions(String loginId) {
        return profileRemoteService.getContributions(loginId);
    }

    @Override
    public Observable<List<Repo>> getRepos(String loginId, int page) {
        return profileRemoteService.getRepos(loginId, page);
    }

}
