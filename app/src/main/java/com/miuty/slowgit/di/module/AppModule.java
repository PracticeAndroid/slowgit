package com.miuty.slowgit.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.miuty.slowgit.BuildConfig;
import com.miuty.slowgit.SlowGitApplication;
import com.miuty.slowgit.util.ApiConst;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //Set timeout
        int timeOut = ApiConst.timeOut; // in seconds
        builder.connectTimeout(timeOut, TimeUnit.SECONDS);
        builder.readTimeout(timeOut, TimeUnit.SECONDS);
        builder.writeTimeout(timeOut, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BuildConfig.REST_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return restAdapter;
    }
}
