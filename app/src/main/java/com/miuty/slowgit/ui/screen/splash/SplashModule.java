package com.miuty.slowgit.ui.screen.splash;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.miuty.slowgit.di.qualifier.ActivityContext;
import com.miuty.slowgit.provider.navigator.ActivityNavigator;
import com.miuty.slowgit.provider.navigator.ActivityNavigatorImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SplashModule {

    @Binds
    @ActivityContext
    abstract Context provideActivity(SplashActivity activity);

    @Provides
    public static ActivityNavigator provideActivityNavigator(@ActivityContext Context context) {
        return new ActivityNavigatorImpl(context, (AppCompatActivity) context);
    }
}
