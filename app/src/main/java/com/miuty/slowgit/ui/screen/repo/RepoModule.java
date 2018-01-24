package com.miuty.slowgit.ui.screen.repo;


import android.content.Context;

import com.miuty.slowgit.di.qualifier.ActivityContext;
import com.miuty.slowgit.provider.navigator.ActivityNavigator;
import com.miuty.slowgit.provider.navigator.ActivityNavigatorImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RepoModule {

    @Binds
    @ActivityContext
    abstract Context provideActivityContext(RepoActivity activity);

    @Provides
    public static ActivityNavigator provideActivityNavigator(@ActivityContext Context context) {
        return new ActivityNavigatorImpl(context);
    }
}
