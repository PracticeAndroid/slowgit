package com.miuty.slowgit.ui.base.mvp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.adapter.BaseAdapter;
import com.miuty.slowgit.ui.base.adapter.DisplayableItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public abstract class BaseMvpListFragment<V extends MvpListView, P extends BasePresenter<V>,
        A extends BaseAdapter, I extends DisplayableItem> extends BaseMvpFragment<V, P> {

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<I> mItems;
    protected A mAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);*/
    }
}
