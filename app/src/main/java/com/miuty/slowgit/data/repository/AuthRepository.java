package com.miuty.slowgit.data.repository;

import com.miuty.slowgit.data.model.User;
import com.miuty.slowgit.data.model.response.BasicAuthResponse;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;


/**
 * Created by Asus on 1/13/2018.
 */

public interface AuthRepository {

    // remote
    Observable<BasicAuthResponse> doBasicLogin(String user, String password);

    // local
    Observable<BasicAuthResponse> saveToken(BasicAuthResponse authResponse);

    // for user data
    Maybe<User> getUser();

    Completable saveUser(User user);
}
