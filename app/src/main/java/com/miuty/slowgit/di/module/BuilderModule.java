package com.miuty.slowgit.di.module;

import com.miuty.slowgit.ui.screen.login.LoginActivity;
import com.miuty.slowgit.ui.screen.login.LoginModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Asus on 1/9/2018.
 */

@Module
public abstract class BuilderModule {

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity bindLoginActivity();
}
