package com.miuty.slowgit.data.repository.remote;

import com.miuty.slowgit.BuildConfig;
import com.miuty.slowgit.data.dao.model.User;
import com.miuty.slowgit.data.dao.request.BasicAuthRequest;
import com.miuty.slowgit.data.dao.response.BasicAuthResponse;
import com.miuty.slowgit.data.repository.AuthRepository;
import com.miuty.slowgit.di.qualifier.DefaultNetworkProviderContext;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;

import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import okhttp3.Credentials;

/**
 * Created by Asus on 1/13/2018.
 */

public class AuthRemoteServiceImpl implements AuthRepository {

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
        basicAuthRequest.setClientSecrect(BuildConfig.GITHUB_SECRET);

        networkProvider = networkProvider
                .addHeader("Authorization", authToken);

        return networkProvider.makeRequest(
                networkProvider.provideApi(BuildConfig.REST_URL, AuthRestService.class)
                        .doBasicLogin(basicAuthRequest));
    }

    @Override
    public Observable<BasicAuthResponse> saveToken(BasicAuthResponse authResponse) {
        return null;
    }

    @Override
    public Maybe<User> getUser() {
        return null;
    }

    @Override
    public Completable saveUser(User user) {
        return null;
    }

}
