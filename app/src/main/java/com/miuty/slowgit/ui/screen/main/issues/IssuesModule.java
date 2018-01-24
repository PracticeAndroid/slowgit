package com.miuty.slowgit.ui.screen.main.issues;


import android.content.Context;

import com.miuty.slowgit.di.qualifier.ActivityContext;
import com.miuty.slowgit.provider.navigator.FragmentNavigator;
import com.miuty.slowgit.provider.navigator.FragmentNavigatorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class IssuesModule {

    @Provides
    FragmentNavigator provideFragmentNavigator(@ActivityContext Context context) {
        return new FragmentNavigatorImpl(context);
    }
}
