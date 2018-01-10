package com.miuty.slowgit.ui.screen.login;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Asus on 1/9/2018.
 */

@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {
    abstract class Builder extends AndroidInjector.Builder<LoginActivity> {

    }
}
