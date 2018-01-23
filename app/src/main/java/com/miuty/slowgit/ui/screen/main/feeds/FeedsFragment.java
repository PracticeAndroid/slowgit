package com.miuty.slowgit.ui.screen.main.feeds;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.ui.base.adapter.LoadMoreAdapter;
import com.miuty.slowgit.ui.base.adapter.decoration.VerticalSpacingDecoration;
import com.miuty.slowgit.ui.base.mvp.BaseMvpListFragment;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.FeedsAdapter;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class FeedsFragment extends BaseMvpListFragment<FeedsMvpView, FeedsPresenter, FeedsAdapter, BaseFeedsItem>
        implements FeedsMvpView {

    private static final String TAG = "FeedsFragment";

    private int page = 1;

    @Override
    protected int layoutId() {
        return R.layout.fragment_feeds;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.addItemDecoration(new VerticalSpacingDecoration(50, true));
        /*mRecyclerView.setItemAnimator(new LandingAnimator());*/
        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mAdapter));
        mAdapter.initLoadMore(() -> {
            Log.d(TAG, "loadmore" + page);
            presenter.getFeeds(page);
        }, mRecyclerView);
        mAdapter.setItemClickListener(new BaseViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                mAdapter.remove(position);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }

    @Override
    protected FeedsAdapter createAdapter() {
        return new FeedsAdapter(getContext(), new BaseViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //mAdapter.remove(position);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }

    @Override
    protected void doRefresh() {
        mAdapter.setLoadMore(true);
        page = 1;
        mAdapter.clear();
        presenter.getFeeds(page);
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
        if (items == null || items.size() == 0) { // end of loadmore
            mAdapter.setLoadMore(false);
            mAdapter.notifyItemChanged(mAdapter.getItems().size());
        }
        mAdapter.setLoaded();
        page++;
        mAdapter.addItems(items);
    }
}
