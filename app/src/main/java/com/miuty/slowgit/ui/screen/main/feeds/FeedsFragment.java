package com.miuty.slowgit.ui.screen.main.feeds;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.adapter.LoadMoreAdapter;
import com.miuty.slowgit.ui.base.adapter.decoration.VerticalSpacingDecoration;
import com.miuty.slowgit.ui.base.mvp.BaseMvpListFragment;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.FeedsAdapter;

import java.util.List;

public class FeedsFragment extends BaseMvpListFragment<FeedsMvpView, FeedsPresenter, FeedsAdapter, BaseFeedsItem>
        implements FeedsMvpView {

    private static final String TAG = "FeedsFragment";

    @Override
    protected int layoutId() {
        return R.layout.fragment_feeds;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.addItemDecoration(new VerticalSpacingDecoration(50, true));
        presenter.getFeeds(1);

        mAdapter.initLoadMore(() -> {

        }, mRecyclerView);
    }

    @Override
    protected FeedsAdapter createAdapter() {
        return new FeedsAdapter(getContext());
    }

    @Override
    protected void doRefresh() {
        presenter.getFeeds(1);
    }

    @Override
    public void noInternetConnection() {

    }

    @Override
    public void someThingError(String msg) {

    }

    @Override
    public void showProgress(String msg, boolean isCancelable) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showFeedsOnRecyclerView(List<BaseFeedsItem> items) {
        mAdapter.addItems(items);
    }
}
