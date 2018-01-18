package com.miuty.slowgit.ui.screen.profile.starred;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ProfileStarredFragmentFactory {

    @ContributesAndroidInjector(modules = {ProfileStarredModule.class})
    abstract ProfileStarredFragment provideProfileStarredFragmentFactory();
}
