package com.miuty.slowgit.ui.screen.profile.repositories;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ProfileRepositoriesFragmentProvider {

    @ContributesAndroidInjector(modules = {ProfileRepositoriesModule.class})
    abstract ProfileRepositoriesFragment provideProfileRepositoriesFragmentFactory();
}
