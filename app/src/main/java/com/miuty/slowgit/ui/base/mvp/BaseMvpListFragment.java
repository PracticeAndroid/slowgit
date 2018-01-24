package com.miuty.slowgit.ui.base.mvp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.adapter.BaseAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public abstract class BaseMvpListFragment<V extends MvpListView, P extends BasePresenter<V>,
        A extends BaseAdapter> extends BaseMvpFragment<V, P> implements MvpListView {

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @Inject
    protected RecyclerView.LayoutManager mLayoutManager;
    protected A mAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = createAdapter();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(this::doRefresh);
    }

    protected abstract A createAdapter();

    protected abstract void doRefresh();

    @Override
    public void hideRefreshLayout() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
