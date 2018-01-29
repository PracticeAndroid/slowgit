package com.miuty.slowgit;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.miuty.slowgit.di.component.AppComponent;
import com.miuty.slowgit.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Asus on 1/9/2018.
 */

public class SlowGitApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    private static SlowGitApplication instance;

    private AppComponent appComponent;

    @Inject
    protected DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    protected DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    public static SlowGitApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupFontSize();
        setUpDependencyInject();
    }

    private void setupFontSize() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
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
