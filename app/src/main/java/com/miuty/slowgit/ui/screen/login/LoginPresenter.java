package com.miuty.slowgit.ui.screen.login;

import android.support.annotation.NonNull;

import com.miuty.slowgit.BuildConfig;
import com.miuty.slowgit.data.dao.model.request.BasicAuthRequest;
import com.miuty.slowgit.data.service.LoginService;
import com.miuty.slowgit.data.service.impl.LoginServiceImpl;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;
import com.miuty.slowgit.repo.LoginRepository;
import com.miuty.slowgit.repo.LoginRepositoryImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;
import com.miuty.slowgit.ui.base.mvp.MvpView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.Credentials;

/**
 * Created by Asus on 1/9/2018.
 */

public class LoginPresenter extends BasePresenter<MvpView> {

    private LoginRepository loginRepository;
    private LoginService loginService;

    @Inject
    public LoginPresenter(LoginRepositoryImpl loginRepository, LoginServiceImpl loginService) {
        this.loginRepository = loginRepository;
        this.loginService = loginService;
    }

    public void doBasicLogin(@NonNull String userName, @NonNull String password) {

        //TODO: check validate username-password
        String authToken = Credentials.basic(userName, password);
        BasicAuthRequest basicAuthRequest = new BasicAuthRequest();
        basicAuthRequest.setScopes(Arrays.asList("user", "repo", "gist", "notifications", "read:org"));
        basicAuthRequest.setClientId(BuildConfig.GITHUB_CLIENT_ID);
        basicAuthRequest.setClientSecrect(BuildConfig.GITHUB_SECRET);

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", authToken);

        loginService.doBasicLogin(basicAuthRequest, map);
    }

}
