package com.miuty.slowgit.ui.base;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Asus on 1/9/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpDependencyInjection();
    }

    public void setUpDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
