package com.miuty.slowgit.ui.screen.repo.releases;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ReleasesFragmentProvider {

    @ContributesAndroidInjector(modules = {
            ReleasesModule.class
    })
    abstract ReleasesFragment providerReleasesFragmentFactory();
}
