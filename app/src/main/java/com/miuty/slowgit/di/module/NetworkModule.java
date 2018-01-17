package com.miuty.slowgit.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.miuty.slowgit.di.qualifier.ApplicationContext;
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
 * Created by Asus on 1/13/2018.
 */

@Module
public class NetworkModule {

    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create();
    }

    @Provides
    @DefaultOkHtppClientContext
    OkHttpClient provideDefaultOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //Set timeout
        builder.connectTimeout(ApiConst.DEFAULT_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
        builder.readTimeout(ApiConst.DEFAULT_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
        builder.writeTimeout(ApiConst.DEFAULT_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);

        //Enable log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        return builder.build();
    }

    @Provides
    @DefaultNetworkProviderContext
    DefaultNetworkProvider provideDefaultNetworkProvider(@ApplicationContext Context context,
                                                         @DefaultOkHtppClientContext OkHttpClient okHttpClient,
                                                         Gson gson) {
        return new DefaultNetworkProvider(context, okHttpClient, gson)
                .addDefaultHeader();
    }
}
