package com.miuty.slowgit.data.repository.profile.remote;

import com.miuty.slowgit.data.model.profile.BasicProfile;
import com.miuty.slowgit.di.qualifier.DefaultNetworkProviderContext;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;
import com.miuty.slowgit.util.ApiConst;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

/**
 * Created by igneel on 1/20/2018.
 */

public class ProfileRemoteServiceImpl implements ProfileRemoteService {

    private NetworkProvider networkProvider;
    private String apiUrl;

    @Inject
    public ProfileRemoteServiceImpl(@DefaultNetworkProviderContext DefaultNetworkProvider networkProvider,
                                    @Named(ApiConst.MAIN_API_URL_NAMED) String apiUrl){
        this.networkProvider = networkProvider;
        this.apiUrl = apiUrl;
    }

    @Override
    public Observable<BasicProfile> getBasicProfile(String loginId) {
        return networkProvider.makeRequest(
                networkProvider.provideApi(apiUrl, ProfileRestService.class)
                .getBasicProfile(loginId)
        );
    }
}
