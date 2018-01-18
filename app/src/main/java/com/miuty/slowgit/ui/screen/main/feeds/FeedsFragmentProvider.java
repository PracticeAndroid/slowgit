package com.miuty.slowgit.ui.screen.main.feeds;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FeedsFragmentProvider {

    @ContributesAndroidInjector(modules = {FeedsModule.class})
    abstract FeedsFragment provideFeedsFragmentFactory();
}
