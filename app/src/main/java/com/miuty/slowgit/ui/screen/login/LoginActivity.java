package com.miuty.slowgit.ui.screen.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;
import com.miuty.slowgit.ui.base.mvp.MvpView;

/**
 * Created by Asus on 1/9/2018.
 */

interface LoginMvpView extends MvpView {

    void onBasicLogin();

    void onBasicLoginFailed();
}

public class LoginActivity extends BaseMvpActivity<LoginMvpView, LoginPresenter> implements LoginMvpView {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.doBasicLogin("nguyenhung.uit@gmail.com", "hungpncm1");
    }

    @Override
    public void onBasicLogin() {
        Log.d(TAG, "onBasicLogin: ");
    }

    @Override
    public void onBasicLoginFailed() {
        Log.e(TAG, "onBasicLoginFailed: ");
    }
}

