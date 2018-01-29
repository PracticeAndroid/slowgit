package com.miuty.slowgit.ui.screen.repo.contributors;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ContributorsFragmentProvider {

    @ContributesAndroidInjector(modules = {
            ContributorsModule.class
    })
    abstract ContributorsFragment providerContributorsFragmentFactory();
}
