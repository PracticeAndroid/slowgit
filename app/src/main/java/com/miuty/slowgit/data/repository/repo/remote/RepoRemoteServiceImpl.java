package com.miuty.slowgit.data.repository.repo.remote;


import com.miuty.slowgit.data.model.response.CommitResponse;
import com.miuty.slowgit.data.repository.profile.remote.ProfileRestService;
import com.miuty.slowgit.di.qualifier.DefaultNetworkProviderContext;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;
import com.miuty.slowgit.util.ApiConst;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

public class RepoRemoteServiceImpl implements RepoRemoteService {

    private NetworkProvider networkProvider;
    private String apiUrl;
    private String apiUr2;

    @Inject
    public RepoRemoteServiceImpl(@DefaultNetworkProviderContext DefaultNetworkProvider networkProvider,
                                 @Named(ApiConst.MAIN_API_URL_NAMED) String apiUrl,
                                 @Named(ApiConst.SECOND_API_URL_NAMED) String apiUr2) {
        this.networkProvider = networkProvider;
        this.apiUrl = apiUrl;
        this.apiUr2 = apiUr2;
    }

    @Override
    public Observable<List<CommitResponse>> getCommits(String owner, String repo) {
        return networkProvider.makeRequest(
                networkProvider.provideApi(apiUrl, RepoRestService.class)
                        .getCommits(owner, repo)
        );
    }
}
