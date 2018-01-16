package com.miuty.slowgit.ui.screen.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;
import com.miuty.slowgit.ui.base.mvp.MvpView;

import butterknife.BindView;

/**
 * Created by Asus on 1/9/2018.
 */

interface LoginMvpView extends MvpView {

    void onBasicLogin();

    void onBasicLoginFailed();
}

public class LoginActivity extends BaseMvpActivity<LoginMvpView, LoginPresenter> implements LoginMvpView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

