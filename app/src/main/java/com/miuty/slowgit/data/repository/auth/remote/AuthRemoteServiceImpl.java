package com.miuty.slowgit.data.repository.auth.remote;

import com.miuty.slowgit.BuildConfig;
import com.miuty.slowgit.data.model.request.BasicAuthRequest;
import com.miuty.slowgit.data.model.response.BasicAuthResponse;
import com.miuty.slowgit.di.qualifier.DefaultNetworkProviderContext;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;

import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.Credentials;

/**
 * Created by Asus on 1/13/2018.
 */

public class AuthRemoteServiceImpl implements AuthRemoteService {

    protected NetworkProvider networkProvider;

    @Inject
    public AuthRemoteServiceImpl(@DefaultNetworkProviderContext DefaultNetworkProvider networkProvider) {
        this.networkProvider = networkProvider;
    }

    @Override
    public Observable<BasicAuthResponse> doBasicLogin(String userName, String password) {
        String authToken = Credentials.basic(userName, password);
        BasicAuthRequest basicAuthRequest = new BasicAuthRequest();
        basicAuthRequest.setScopes(Arrays.asList("user", "repo", "gist", "notifications", "read:org"));
        basicAuthRequest.setClientId(BuildConfig.GITHUB_CLIENT_ID);
        basicAuthRequest.setClientSecret(BuildConfig.GITHUB_SECRET);

        networkProvider = networkProvider
                .addHeader("Authorization", authToken);

        return networkProvider.makeRequest(
                networkProvider.provideApi(BuildConfig.REST_URL, AuthRestService.class)
                        .doBasicLogin(basicAuthRequest));
    }
}
