package com.miuty.slowgit.data.repository.remote;


import com.miuty.slowgit.data.model.Feed;
import com.miuty.slowgit.data.repository.FeedsRepository;
import com.miuty.slowgit.di.qualifier.DefaultNetworkProviderContext;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;
import com.miuty.slowgit.util.ApiConst;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

public class FeedsRemoteServiceImpl implements FeedsRepository {

    private NetworkProvider networkProvider;
    private String apiUrl;

    @Inject
    public FeedsRemoteServiceImpl(@DefaultNetworkProviderContext DefaultNetworkProvider networkProvider,
                                  @Named(ApiConst.MAIN_API_URL_NAMED) String apiUrl) {
        this.networkProvider = networkProvider;
        this.apiUrl = apiUrl;
    }

    @Override
    public Observable<List<Feed>> getFeeds(int page) {
        return networkProvider.makeRequest(
                networkProvider.provideApi(apiUrl, FeedsRestService.class)
                        .getFeeds("hungpn", page)
        );
    }
}
