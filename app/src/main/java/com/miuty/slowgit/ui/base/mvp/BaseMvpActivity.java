package com.miuty.slowgit.ui.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.miuty.slowgit.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by Asus on 1/9/2018.
 */


public class BaseMvpActivity<V extends MvpView, P extends BasePresenter<V>> extends BaseActivity
        implements MvpView {

    private static final String TAG = "BaseMvpActivity";

    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(presenter);
        presenter.bindView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void noInternetConnection() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}

