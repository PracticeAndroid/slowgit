package com.miuty.slowgit.ui.screen.main;


import android.support.v7.app.AppCompatActivity;

import com.miuty.slowgit.provider.navigator.ActivityNavigator;
import com.miuty.slowgit.provider.navigator.ActivityNavigatorImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainModule {

    @Binds
    abstract AppCompatActivity provideActivity(MainActivity activity);

    @Provides
    public static ActivityNavigator provideActivityNavigator(AppCompatActivity activity) {
        return new ActivityNavigatorImpl(activity);
    }
}
