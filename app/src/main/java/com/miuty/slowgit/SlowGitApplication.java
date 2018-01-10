package com.miuty.slowgit;

import android.app.Activity;
import android.app.Application;

import com.miuty.slowgit.di.component.AppComponent;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Asus on 1/9/2018.
 */

public class SlowGitApplication extends Application implements HasActivityInjector {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpDependencyInject();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return null;
    }

    private void setUpDependencyInject() {
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);
    }
}
