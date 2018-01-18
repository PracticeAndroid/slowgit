package com.miuty.slowgit.ui.screen.main.feeds;


import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.FeedsAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class FeedsModule {

    /**
     * default data for recycler view at initialization.
     *
     * @return
     */
    @Provides
    List<BaseFeedsItem> provideDataForRecyclerView() {
        return new ArrayList<>();
    }

    @Provides
    FeedsAdapter provideFeedsAdapter(List<BaseFeedsItem> items) {
        return new FeedsAdapter(items);
    }

}
