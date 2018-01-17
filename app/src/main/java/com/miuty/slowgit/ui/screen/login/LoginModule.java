package com.miuty.slowgit.ui.screen.login;

import android.content.Context;

import com.miuty.slowgit.di.qualifier.ActivityContext;
import com.miuty.slowgit.provider.navigator.ActivityNavigator;
import com.miuty.slowgit.provider.navigator.ActivityNavigatorImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Asus on 1/9/2018.
 */

@Module
public abstract class LoginModule {

    @ActivityContext
    @Binds
    abstract Context provideActivityContext(LoginActivity activity);

    @Provides
    public static ActivityNavigator provideActivityNavigator(@ActivityContext Context context) {
        return new ActivityNavigatorImpl(context);
    }
}