package com.miuty.slowgit.ui.screen.main.issues.page;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.miuty.slowgit.di.qualifier.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Asus on 1/28/2018.
 */

@Module
public class IssuesItemModule {

    @Provides
    RecyclerView.LayoutManager provideLayoutManager(@ActivityContext Context context) {
        return new LinearLayoutManager(context);
    }
}
