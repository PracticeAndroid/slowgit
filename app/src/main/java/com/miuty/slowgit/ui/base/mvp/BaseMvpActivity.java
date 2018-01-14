package com.miuty.slowgit.ui.base.mvp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.miuty.slowgit.ui.base.BaseActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DaggerActivity;

/**
 * Created by Asus on 1/9/2018.
 */


public class BaseMvpActivity<V extends MvpView, P extends BasePresenter<V>> extends BaseActivity
        implements MvpView {

    private static final String TAG = "BaseMvpActivity";

    @Inject
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState, persistentState);
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

