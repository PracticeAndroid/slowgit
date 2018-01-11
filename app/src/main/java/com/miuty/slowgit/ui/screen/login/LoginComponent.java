package com.miuty.slowgit.ui.screen.login;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Asus on 1/9/2018.
 */

@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent extends AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginActivity> {

    }
}
