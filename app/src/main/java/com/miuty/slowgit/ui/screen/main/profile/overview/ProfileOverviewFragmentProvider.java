package com.miuty.slowgit.ui.screen.main.profile.overview;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ProfileOverviewFragmentProvider {

    @ContributesAndroidInjector(modules = {ProfileOverviewModule.class})
    abstract ProfileOverviewFragment profileOverviewFragmentFactory();
}
