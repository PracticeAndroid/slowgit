package com.miuty.slowgit.di.module;

import com.miuty.slowgit.ui.screen.login.LoginActivity;
import com.miuty.slowgit.ui.screen.login.LoginModule;
import com.miuty.slowgit.ui.screen.main.MainActivity;
import com.miuty.slowgit.ui.screen.main.MainModule;
import com.miuty.slowgit.ui.screen.main.feeds.FeedsFragmentProvider;
import com.miuty.slowgit.ui.screen.main.issues.IssuesFragmentProvider;
import com.miuty.slowgit.ui.screen.main.profile.ProfileActivity;
import com.miuty.slowgit.ui.screen.main.profile.ProfileModule;
import com.miuty.slowgit.ui.screen.main.profile.overview.ProfileOverviewFragmentProvider;
import com.miuty.slowgit.ui.screen.main.pull_requests.PullRequestsFragmentProvider;
import com.miuty.slowgit.ui.screen.splash.SplashActivity;
import com.miuty.slowgit.ui.screen.splash.SplashModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Asus on 1/9/2018.
 */

@Module
public abstract class BuilderModule {

    @ContributesAndroidInjector(modules = {SplashModule.class})
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {MainModule.class, FeedsFragmentProvider.class,
            IssuesFragmentProvider.class, PullRequestsFragmentProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {ProfileModule.class, ProfileOverviewFragmentProvider.class})
    abstract ProfileActivity bindProfileActivity();
}
