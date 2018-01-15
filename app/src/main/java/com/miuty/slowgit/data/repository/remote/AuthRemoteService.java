package com.miuty.slowgit.data.repository.remote;


import com.miuty.slowgit.data.dao.response.BasicAuthResponse;

import io.reactivex.Observable;

/**
 * Created by Asus on 1/13/2018.
 */

public interface AuthRemoteService {

    Observable<BasicAuthResponse> doBasicLogin(String userName, String password);

    Observable<Void> logout();
}
