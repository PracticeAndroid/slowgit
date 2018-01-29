package com.miuty.slowgit.ui.screen.repo.commits;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CommitsFragmentProvider {

    @ContributesAndroidInjector(modules = {
            CommitsModule.class
    })
    abstract CommitsFragment provideCommitsFragmentFactory();
}
