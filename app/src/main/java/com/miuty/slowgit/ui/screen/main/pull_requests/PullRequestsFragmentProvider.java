package com.miuty.slowgit.ui.screen.main.pull_requests;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PullRequestsFragmentProvider {

    @ContributesAndroidInjector(modules = {PullRequestsModule.class})
    abstract PullRequestsFragment providePullRequestsFragmentFactory();
}
