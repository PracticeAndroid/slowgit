package com.miuty.slowgit.data.repository.profile.remote;

import com.miuty.slowgit.data.model.Repo;
import com.miuty.slowgit.data.model.profile.BasicProfile;
import com.miuty.slowgit.di.qualifier.DefaultNetworkProviderContext;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;
import com.miuty.slowgit.util.ApiConst;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by igneel on 1/20/2018.
 */

public class ProfileRemoteServiceImpl implements ProfileRemoteService {

    private NetworkProvider networkProvider;
    private String apiUrl;
    private String apiUr2;
    private static final String CONTRIBUTION_URL = "https://github.com/users/%s/contributions";

    @Inject
    public ProfileRemoteServiceImpl(@DefaultNetworkProviderContext DefaultNetworkProvider networkProvider,
                                    @Named(ApiConst.MAIN_API_URL_NAMED) String apiUrl,
                                    @Named(ApiConst.SECOND_API_URL_NAMED) String apiUr2) {
        this.networkProvider = networkProvider;
        this.apiUrl = apiUrl;
        this.apiUr2 = apiUr2;
    }

    @Override
    public Observable<BasicProfile> getBasicProfile(String loginId) {
        return networkProvider.makeRequest(
                networkProvider.provideApi(apiUrl, ProfileRestService.class)
                        .getBasicProfile(loginId)
        );
    }

    @Override
    public Observable<ResponseBody> getContributions(String loginId) {
        return networkProvider.makeRequest(
                networkProvider.provideApi(apiUr2, ProfileRestService.class)
                        .getContributions(loginId)
        );
    }

    @Override
    public Observable<List<Repo>> getRepos(String loginId, int page) {
        return networkProvider.makeRequest(
                networkProvider.provideApi(apiUrl, ProfileRestService.class)
                        .getRepos(loginId, page)
        );
    }

}
