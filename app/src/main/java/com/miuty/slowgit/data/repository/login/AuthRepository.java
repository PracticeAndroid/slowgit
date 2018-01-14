package com.miuty.slowgit.data.repository.login;

import com.miuty.slowgit.data.dao.model.response.BasicAuthResponse;

import io.reactivex.Observable;


/**
 * Created by Asus on 1/13/2018.
 */

public interface AuthRepository {

    // remote
    Observable<BasicAuthResponse> doBasicLogin(String user, String password);

    // local
    void saveToken(BasicAuthResponse authResponse);
}
