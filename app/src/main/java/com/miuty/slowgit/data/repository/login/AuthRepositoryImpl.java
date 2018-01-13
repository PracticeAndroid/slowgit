package com.miuty.slowgit.data.repository.login;

import com.miuty.slowgit.data.dao.model.response.BasicAuthResponse;
import com.miuty.slowgit.data.repository.login.local.AuthLocalService;
import com.miuty.slowgit.data.repository.login.local.AuthLocalServiceImpl;
import com.miuty.slowgit.data.repository.login.remote.AuthRemoteService;
import com.miuty.slowgit.data.repository.login.remote.AuthRemoteServiceImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Asus on 1/13/2018.
 */

public class AuthRepositoryImpl implements AuthRepository {

    private AuthRemoteService authRemoteService;
    private AuthLocalService authLocalService;

    @Inject
    public AuthRepositoryImpl(AuthRemoteServiceImpl authRemoteService, AuthLocalServiceImpl authLocalService) {
        this.authRemoteService = authRemoteService;
        this.authLocalService = authLocalService;
    }

    @Override
    public Observable<BasicAuthResponse> doBasicLogin(String userName, String password) {
        return authRemoteService.doBasicLogin(userName, password);
    }

    @Override
    public void saveToken(BasicAuthResponse authResponse) {
        //TODO
    }
}
