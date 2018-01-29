package com.miuty.slowgit.ui.screen.repo.readme;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ReadMeFragmentProvider {

    @ContributesAndroidInjector(modules = {
            ReadMeModule.class
    })
    abstract ReadMeFragment providerReadMeFragmentFactory();
}
