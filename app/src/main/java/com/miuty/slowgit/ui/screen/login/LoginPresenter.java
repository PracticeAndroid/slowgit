package com.miuty.slowgit.ui.screen.login;

import android.support.annotation.NonNull;

import com.miuty.slowgit.data.repository.login.AuthRepository;
import com.miuty.slowgit.data.repository.login.AuthRepositoryImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;
import com.miuty.slowgit.ui.base.mvp.MvpView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Asus on 1/9/2018.
 */

public class LoginPresenter extends BasePresenter<LoginMvpView> {

    private AuthRepository loginRepository;

    @Inject
    public LoginPresenter(AuthRepositoryImpl loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void doBasicLogin(@NonNull String userName, @NonNull String password) {
        Disposable disposable = loginRepository.doBasicLogin(userName, password)
                .subscribe(response -> {
                    loginRepository.saveToken(response);
                    view.onBasicLogin();
                }, throwable -> {
                    view.onBasicLoginFailed();
                });

        disposeOnDestroy(disposable);
    }
}
