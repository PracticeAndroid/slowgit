package com.miuty.slowgit;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.miuty.slowgit.di.component.AppComponent;
import com.miuty.slowgit.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;

/**
 * Created by Asus on 1/9/2018.
 */

public class SlowGitApplication extends Application implements HasActivityInjector, HasFragmentInjector {

    private AppComponent appComponent;

    @Inject
    protected DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    protected DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpDependencyInject();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    private void setUpDependencyInject() {
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);
    }
}
