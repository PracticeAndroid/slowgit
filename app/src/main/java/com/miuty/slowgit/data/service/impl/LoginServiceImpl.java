package com.miuty.slowgit.data.service.impl;

import com.miuty.slowgit.data.dao.model.request.BasicAuthRequest;
import com.miuty.slowgit.data.dao.model.response.BasicAuthResponse;
import com.miuty.slowgit.data.service.LoginService;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Asus on 1/12/2018.
 */

public class LoginServiceImpl implements LoginService {

    NetworkProvider networkProvider;

    @Inject
    public LoginServiceImpl(DefaultNetworkProvider defaultNetworkProvider) {
        this.networkProvider = defaultNetworkProvider;
    }

    @Override
    public Observable<BasicAuthResponse> doBasicLogin(BasicAuthRequest request, Map<String, String> header) {
        networkProvider.makeRequest()
        return null;
    }
}
