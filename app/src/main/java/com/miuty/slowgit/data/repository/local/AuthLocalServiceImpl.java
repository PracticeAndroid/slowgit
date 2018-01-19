package com.miuty.slowgit.data.repository.local;

import com.miuty.slowgit.data.model.User;
import com.miuty.slowgit.data.model.response.BasicAuthResponse;
import com.miuty.slowgit.data.repository.AuthRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by Asus on 1/13/2018.
 */

public class AuthLocalServiceImpl implements AuthRepository {

    private AppDatabase appDatabase;

    @Inject
    public AuthLocalServiceImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public Observable<BasicAuthResponse> doBasicLogin(String user, String password) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<BasicAuthResponse> saveToken(BasicAuthResponse authResponse) {

        return Observable.just(authResponse);
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
