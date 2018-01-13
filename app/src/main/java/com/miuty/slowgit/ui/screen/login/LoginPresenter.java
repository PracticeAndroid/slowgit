package com.miuty.slowgit.ui.screen.login;

import android.support.annotation.NonNull;

import com.miuty.slowgit.data.repository.login.AuthRepository;
import com.miuty.slowgit.data.repository.login.AuthRepositoryImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;
import com.miuty.slowgit.ui.base.mvp.MvpView;

import javax.inject.Inject;

/**
 * Created by Asus on 1/9/2018.
 */

public class LoginPresenter extends BasePresenter<MvpView> {

    private AuthRepository loginRepository;

    @Inject
    public LoginPresenter(AuthRepositoryImpl loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void doBasicLogin(@NonNull String userName, @NonNull String password) {
        loginRepository.doBasicLogin(userName, password).subscribe(response -> {
            loginRepository.saveToken(response);
        }, throwable -> {
        });
    }
}
