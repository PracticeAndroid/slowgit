package com.miuty.slowgit.ui.screen.main.feeds;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpListFragment;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.FeedsAdapter;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.ForkedItem;

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
        /*mAdapter = new FeedsAdapter(getContext());

        mAdapter.addItem(new ForkedItem());
        mAdapter.addItem(new ForkedItem());
        mAdapter.addItem(new ForkedItem());
        mAdapter.addItem(new ForkedItem());
        mAdapter.addItem(new ForkedItem());
        mAdapter.addItem(new ForkedItem());
        mAdapter.addItem(new ForkedItem());
        mAdapter.addItem(new ForkedItem());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);*/

        presenter.getFeeds();
    }

    @Override
    protected FeedsAdapter createAdapter() {
        return new FeedsAdapter(getContext());
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
