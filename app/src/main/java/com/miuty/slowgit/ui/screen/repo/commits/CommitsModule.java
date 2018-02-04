package com.miuty.slowgit.ui.screen.repo.commits;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.miuty.slowgit.di.qualifier.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class CommitsModule {

    @Provides
    public RecyclerView.LayoutManager provideLayoutManager(@ActivityContext Context context) {
        return new LinearLayoutManager(context);
    }

}
