package com.miuty.slowgit.ui.screen.main.issues;


import com.miuty.slowgit.ui.screen.main.issues.page.IssuesItemFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class IssuesFragmentProvider {

    @ContributesAndroidInjector(modules = {IssuesModule.class,
            IssuesItemFragmentProvider.class})
    abstract IssuesFragment provideIssuesFragmentFactory();
}
