package com.miuty.slowgit.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.miuty.slowgit.SlowGitApplication;
import com.miuty.slowgit.data.repository.AppDatabase;
import com.miuty.slowgit.di.qualifier.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Asus on 1/9/2018.
 */

@Module
public class AppModule {

    @Provides
    @ApplicationContext
    Context provideAppContext(SlowGitApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    Application provideApplication(SlowGitApplication application) {
        return application;
    }

    @Provides
    AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app-db").build();
    }
}
