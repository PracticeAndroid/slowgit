package com.miuty.slowgit.di.component;

import com.miuty.slowgit.SlowGitApplication;
import com.miuty.slowgit.di.module.AppModule;
import com.miuty.slowgit.di.module.BuilderModule;
import com.miuty.slowgit.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Asus on 1/9/2018.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class,
        BuilderModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(SlowGitApplication slowGitApplication);

        AppComponent build();
    }

    void inject(SlowGitApplication slowGitApplication);
}
