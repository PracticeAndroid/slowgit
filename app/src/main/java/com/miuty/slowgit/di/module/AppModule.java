package com.miuty.slowgit.di.module;

import android.app.Application;
import android.content.Context;

import com.miuty.slowgit.SlowGitApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Asus on 1/9/2018.
 */

@Module
public class AppModule {

    @Provides
    Context provideAppContext(SlowGitApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    Application provideApplication(SlowGitApplication application) {
        return application;
    }
}
