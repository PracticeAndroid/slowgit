package com.miuty.slowgit.ui.screen.repo;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.miuty.slowgit.di.qualifier.ActivityContext;
import com.miuty.slowgit.di.qualifier.ActivityFragmentManager;
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
        return new ActivityNavigatorImpl(context, (AppCompatActivity) context);
    }

    @Provides
    @ActivityFragmentManager
    public static FragmentManager provideFragmentManager(@ActivityContext Context context) {
        RepoActivity activity = (RepoActivity) context;
        return activity.getSupportFragmentManager();
    }
}
