package com.miuty.slowgit.ui.screen.main.feeds;

import com.miuty.slowgit.ui.base.mvp.MvpListView;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsItem;

import java.util.List;

public interface FeedsMvpView extends MvpListView {

    void showFeedsOnRecyclerView(List<BaseFeedsItem> items);
}
