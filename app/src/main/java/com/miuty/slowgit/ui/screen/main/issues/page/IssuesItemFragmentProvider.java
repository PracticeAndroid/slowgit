package com.miuty.slowgit.ui.screen.main.issues.page;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Asus on 1/28/2018.
 */

@Module
public abstract class IssuesItemFragmentProvider {

    @ContributesAndroidInjector(modules = IssuesItemModule.class)
    abstract IssuesItemFragment provideIssuesItemFragment();
}
