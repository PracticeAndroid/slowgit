package com.miuty.slowgit.data.repository;

import com.miuty.slowgit.data.dao.response.BasicAuthResponse;
import com.miuty.slowgit.data.repository.local.AuthLocalService;
import com.miuty.slowgit.data.repository.local.AuthLocalServiceImpl;
import com.miuty.slowgit.data.repository.remote.AuthRemoteService;
import com.miuty.slowgit.data.repository.remote.AuthRemoteServiceImpl;

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
    public Observable<BasicAuthResponse> saveToken(BasicAuthResponse authResponse) {
        //TODO save into local
        return Observable.just(authResponse);
    }
}
