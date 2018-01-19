package com.miuty.slowgit.ui.base.adapter.loadmore;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Asus on 1/19/2018.
 */

public abstract class LoadMoreScroll extends RecyclerView.OnScrollListener {

    private int visibleThreshold = 3;
    private int currentPage = 0;
    private boolean isLoading = true;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    // this method is to init all type of LayoutManager but now I just handle LinearLayoutManager
    private void initLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    private void initRecyclerView(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        initLayoutManager(recyclerView.getLayoutManager());
        initRecyclerView(recyclerView.getAdapter());

        if (layoutManager instanceof LinearLayoutManager) {
            int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            int totalItemCount = layoutManager.getItemCount();
            if (lastVisibleItemPosition + visibleThreshold >= totalItemCount) {
                currentPage++;
                onLoadMore(currentPage);
            }
        }

    }

    public abstract void onLoadMore(int page);

    public void init(int page) {
        this.currentPage = page;
    }
}
