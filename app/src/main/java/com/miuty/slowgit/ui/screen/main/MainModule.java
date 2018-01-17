package com.miuty.slowgit.ui.screen.main;


import android.content.Context;

import com.miuty.slowgit.di.qualifier.ActivityContext;
import com.miuty.slowgit.provider.navigator.ActivityNavigator;
import com.miuty.slowgit.provider.navigator.ActivityNavigatorImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainModule {

    @Binds
    @ActivityContext
    abstract Context provideActivity(MainActivity activity);

    @Provides
    public static ActivityNavigator provideActivityNavigator(@ActivityContext Context context) {
        return new ActivityNavigatorImpl(context);
    }
}
