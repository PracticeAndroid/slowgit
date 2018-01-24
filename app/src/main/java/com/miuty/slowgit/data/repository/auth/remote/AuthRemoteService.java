package com.miuty.slowgit.data.repository.auth.remote;

import com.miuty.slowgit.data.model.response.BasicAuthResponse;

import io.reactivex.Observable;

/**
 * Created by Asus on 1/24/2018.
 */

public interface AuthRemoteService {

    Observable<BasicAuthResponse> doBasicLogin(String user, String password);
}
