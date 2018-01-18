package com.miuty.slowgit.ui.screen.main.issues;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class IssuesFragmentProvider {

    @ContributesAndroidInjector(modules = {IssuesModule.class})
    abstract IssuesFragment provideIssuesFragmentFactory();
}
