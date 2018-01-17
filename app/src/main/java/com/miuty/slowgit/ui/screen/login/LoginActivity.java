package com.miuty.slowgit.ui.screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;
import com.miuty.slowgit.ui.base.mvp.MvpView;
import com.miuty.slowgit.ui.screen.main.MainActivity;
import com.miuty.slowgit.util.InputUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Asus on 1/9/2018.
 */

interface LoginMvpView extends MvpView {

    void onBasicLoginSuccessfully();

    void onBasicLoginFailed(String msg);

    void onUsernameEmpty(boolean isEmpty);

    void onPasswordEmpty(boolean isEmpty);

    void onSignInStatus(boolean isSigningIn);
}

public class LoginActivity extends BaseMvpActivity<LoginMvpView, LoginPresenter> implements LoginMvpView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.tv_create_account)
    TextView tvCreateAccount;
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.iv_close_msg)
    ImageView ivCloseMsg;
    @BindView(R.id.rl_incorrect_auth)
    RelativeLayout rlIncorrectAuth;

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.btn_login, R.id.iv_close_msg})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                presenter.doBasicLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
                break;
            case R.id.iv_close_msg:
                rlIncorrectAuth.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onBasicLoginSuccessfully() {
        Log.d(TAG, "onBasicLoginSuccessfully: ");
        activityNavigator.startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onUsernameEmpty(boolean isEmpty) {
        if (isEmpty) {
            edtUsername.setError(getString(R.string.username_is_empty));
        } else {
            edtUsername.setError(null);
        }
    }

    @Override
    public void onPasswordEmpty(boolean isEmpty) {
        if (isEmpty) {
            edtPassword.setError(getString(R.string.username_is_empty));
        } else {
            edtPassword.setError(null);
        }
    }

    @Override
    public void onSignInStatus(boolean isSigningIn) {
        if (isSigningIn) {
            btnLogin.setText(R.string.signing_in);
            btnLogin.setEnabled(false);
            btnLogin.setAlpha(0.5f);
            InputUtils.setEnabledEditTexts(false, edtUsername, edtPassword);
        } else {
            btnLogin.setText(R.string.sign_in_1);
            btnLogin.setEnabled(true);
            btnLogin.setAlpha(1f);
            InputUtils.setEnabledEditTexts(true, edtUsername, edtPassword);
        }
    }

    @Override
    public void onBasicLoginFailed(String msg) {
        rlIncorrectAuth.setVisibility(View.VISIBLE);
    }
}

