package com.miuty.slowgit.data.repository.login.remote;

import com.miuty.slowgit.BuildConfig;
import com.miuty.slowgit.data.dao.model.request.BasicAuthRequest;
import com.miuty.slowgit.data.dao.model.response.BasicAuthResponse;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;
import com.miuty.slowgit.util.ApiConst;

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
    public AuthRemoteServiceImpl(DefaultNetworkProvider networkProvider) {
        this.networkProvider = networkProvider;
    }

    @Override
    public Observable<BasicAuthResponse> doBasicLogin(String userName, String password) {
        String authToken = Credentials.basic(userName, password);
        BasicAuthRequest basicAuthRequest = new BasicAuthRequest();
        basicAuthRequest.setScopes(Arrays.asList("user", "repo", "gist", "notifications", "read:org"));
        basicAuthRequest.setClientId(BuildConfig.GITHUB_CLIENT_ID);
        basicAuthRequest.setClientSecrect(BuildConfig.GITHUB_SECRET);

        networkProvider = networkProvider
                .addHeader("Authorization", authToken);

        return networkProvider.makeRequest(
                networkProvider.provideApi(ApiConst.DEFAULT_BASE_URL, AuthRestService.class)
                        .doBasicLogin(basicAuthRequest));
    }

    @Override
    public Observable<Void> logout() {
        return null;
    }
}
