package com.miuty.slowgit.ui.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;
import dagger.android.DaggerActivity;

/**
 * Created by Asus on 1/9/2018.
 */


public class BaseMvpActivity<V extends MvpView, P extends BasePresenter<V>> extends AppCompatActivity
        implements MvpView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpDependencyInjection();
    }

    public void setUpDependencyInjection() {
        AndroidInjection.inject(this);
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

