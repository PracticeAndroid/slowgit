package com.miuty.slowgit.ui.screen.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;
import com.miuty.slowgit.ui.base.mvp.MvpView;

import javax.inject.Inject;

import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjection;

/**
 * Created by Asus on 1/9/2018.
 */

interface LoginMvpView extends MvpView {

    void onBasicLogin();

    void onBasicLoginFailed();
}

public class LoginActivity extends BaseMvpActivity implements LoginMvpView {

    @Inject
    protected LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBasicLogin() {

    }

    @Override
    public void onBasicLoginFailed() {

    }
}

