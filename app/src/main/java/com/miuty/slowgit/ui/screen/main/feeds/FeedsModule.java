package com.miuty.slowgit.ui.screen.main.feeds;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.miuty.slowgit.di.qualifier.ActivityContext;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.FeedsAdapter;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.ForkedItem;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class FeedsModule {

    @Provides
    RecyclerView.LayoutManager provideLayoutManager(@ActivityContext Context context) {
        return new LinearLayoutManager(context);
    }

    /**
     * default data for recycler view at initialization.
     *
     * @return
     */
    @Provides
    List<BaseFeedsItem> provideDataForRecyclerView() {
        List<BaseFeedsItem> items = new ArrayList<>();
        items.add(new ForkedItem());
        return items;
    }

    @Provides
    FeedsAdapter provideFeedsAdapter(@ActivityContext Context context, List<BaseFeedsItem> items) {
        return new FeedsAdapter(context, items);
    }

}
