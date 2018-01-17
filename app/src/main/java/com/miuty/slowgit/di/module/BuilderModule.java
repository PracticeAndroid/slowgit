package com.miuty.slowgit.di.module;

import com.miuty.slowgit.ui.screen.login.LoginActivity;
import com.miuty.slowgit.ui.screen.login.LoginModule;
import com.miuty.slowgit.ui.screen.main.MainActivity;
import com.miuty.slowgit.ui.screen.main.MainModule;
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

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity bindMainActivity();
}
