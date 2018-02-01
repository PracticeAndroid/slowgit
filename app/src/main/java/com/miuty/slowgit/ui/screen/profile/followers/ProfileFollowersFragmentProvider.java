package com.miuty.slowgit.ui.screen.profile.followers;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ProfileFollowersFragmentProvider {

    @ContributesAndroidInjector(modules = {ProfileFollowersModule.class})
    abstract ProfileFollowersFragment ProfileFollowersFragmentFactory();

}
