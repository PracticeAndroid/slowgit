package com.miuty.slowgit.ui.screen.login;

import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;
import com.miuty.slowgit.repo.LoginRepository;
import com.miuty.slowgit.repo.LoginRepositoryImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;
import com.miuty.slowgit.ui.base.mvp.MvpView;

import javax.inject.Inject;

/**
 * Created by Asus on 1/9/2018.
 */

public class LoginPresenter extends BasePresenter<MvpView> {

    private LoginRepository loginRepository;
    private NetworkProvider networkProvider;

    @Inject
    public LoginPresenter(LoginRepositoryImpl loginRepository, DefaultNetworkProvider networkProvider) {
        this.loginRepository = loginRepository;
        this.networkProvider = networkProvider;
    }

    public void f1() {
        loginRepository.insert();
    }

    public void f2() {
        networkProvider.makeRequest(null);
    }
}
