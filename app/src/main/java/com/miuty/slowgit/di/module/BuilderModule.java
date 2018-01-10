package com.miuty.slowgit.di.module;

import android.app.Activity;

import com.miuty.slowgit.ui.screen.login.LoginActivity;
import com.miuty.slowgit.ui.screen.login.LoginComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by Asus on 1/9/2018.
 */

@Module(subcomponents = {LoginComponent.class})
public abstract class BuilderModule {

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindLogin(LoginComponent.Builder builder);
}
