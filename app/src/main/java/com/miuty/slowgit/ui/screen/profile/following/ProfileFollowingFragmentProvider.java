package com.miuty.slowgit.ui.screen.profile.following;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ProfileFollowingFragmentProvider {

    @ContributesAndroidInjector(modules = {ProfileFollowingModule.class})
    abstract ProfileFollowingFragment ProfileFollowingFragmentFactory();

}
