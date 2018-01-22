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

public abstract class LoadMoreAdapter<VH extends BaseViewHolder> extends BaseAdapter {

    private static final int TYPE_LOAD_MORE = -1;

    //Load more
    private boolean isLoadMore = false;
    private boolean isLoading = false;
    private int visibleThreshold = 3;
    private int lastVisibleItem;
    private int totalItemCount;
    private OnLoadMoreListener loadMoreListener;

    public LoadMoreAdapter(@NonNull Context context) {
        super(context);
    }

    public void initLoadMore(OnLoadMoreListener loadMoreListener, RecyclerView recyclerView) {
        this.loadMoreListener = loadMoreListener;

        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (loadMoreListener != null) {
                        loadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_LOAD_MORE:
                view = layoutInflater.inflate(R.layout.item_default_layout_loadmore, parent, false);
                return new LoadMoreViewHolder(context, view);
        }
        throw new IllegalArgumentException("Do not support view type: " + viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == position) {
            return TYPE_LOAD_MORE;
        } else {
            return super.getItemViewType(position);
        }
    }

    /*@Override
    public int getItemCount() {
        return isLoadMore ? 1 : 0;
    }*/

    public class LoadMoreViewHolder extends BaseViewHolder {

        @BindView(R.id.pb_load_more)
        ProgressBar progressBar;

        public LoadMoreViewHolder(Context context, View itemView) {
            super(context, itemView);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
