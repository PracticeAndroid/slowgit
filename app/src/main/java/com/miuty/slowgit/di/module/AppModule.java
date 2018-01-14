package com.miuty.slowgit.di.module;

import android.app.Application;
import android.content.Context;

import com.miuty.slowgit.SlowGitApplication;
import com.miuty.slowgit.di.qualifier.DefaultNetworkProviderContext;
import com.miuty.slowgit.di.qualifier.DefaultOkHtppClientContext;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.util.ApiConst;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
