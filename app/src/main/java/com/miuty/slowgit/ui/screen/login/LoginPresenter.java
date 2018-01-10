package com.miuty.slowgit.ui.screen.login;

import com.miuty.slowgit.repo.LoginRepository;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;
import com.miuty.slowgit.ui.base.mvp.MvpView;

import javax.inject.Inject;

/**
 * Created by Asus on 1/9/2018.
 */

public class LoginPresenter extends BasePresenter<MvpView> {

    private LoginRepository loginRepository;

    @Inject
    public LoginPresenter(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void f1() {
        loginRepository.insert();
    }
}
