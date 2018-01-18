package com.miuty.slowgit.ui.base.mvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.adapter.BaseAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public abstract class BaseMvpListActivity<V extends MvpListView, P extends BasePresenter<V>, A extends BaseAdapter>
        extends BaseMvpActivity<V, P> {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    protected List mItems;

    @Inject
    protected A mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
