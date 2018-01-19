package com.miuty.slowgit.ui.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.miuty.slowgit.R;

import butterknife.BindView;

/**
 * Created by Asus on 1/19/2018.
 */

public class LoadMoreAdapter<I extends DisplayableItem> extends BaseAdapter<I> {

    private static final int TYPE_LOAD_MORE = -1;
    private static final int TYPE_ITEM = 0;

    //Load more
    private boolean isLoadMore = true;
    private boolean isLoading = false;
    private int visibleThreshold = 3;
    private int lastVisibleItem;
    private int totalItemCount;
    private OnLoadMoreListener listener;

    public LoadMoreAdapter(@NonNull Context context) {
        super(context);
    }

    public void initLoadMore(OnLoadMoreListener listener, RecyclerView recyclerView) {
        this.listener = listener;

        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (listener != null) {
                        listener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_LOAD_MORE:
                view = layoutInflater.inflate(R.layout.item_default_layout_loadmore, parent, false);
                return new LoadMoreViewHolder(view);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == position) {
            return TYPE_LOAD_MORE;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + (isLoadMore ? 1 : 0);
    }

    private class LoadMoreViewHolder extends BaseViewHolder {

        @BindView(R.id.pb_load_more)
        ProgressBar progressBar;

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
