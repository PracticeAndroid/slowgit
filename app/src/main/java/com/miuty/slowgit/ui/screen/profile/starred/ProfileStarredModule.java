package com.miuty.slowgit.ui.screen.profile.starred;

import android.content.Context;

import com.miuty.slowgit.di.qualifier.ActivityContext;
import com.miuty.slowgit.provider.navigator.FragmentNavigator;
import com.miuty.slowgit.provider.navigator.FragmentNavigatorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileStarredModule {

    @Provides
    FragmentNavigator provideFragmentNavigator(@ActivityContext Context context) {
        return new FragmentNavigatorImpl(context);
    }
}
