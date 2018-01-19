package com.miuty.slowgit.ui.screen.main.feeds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.adapter.BaseAdapter;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.ui.base.adapter.LoadMoreAdapter;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.CreatedItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.CreatedViewHolder;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.ForkedItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.ForkedViewHolder;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.PushedToItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.PushedToViewHolder;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.StarredItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.StarredViewHolder;

import java.util.List;

public class FeedsAdapter extends LoadMoreAdapter<BaseFeedsItem> {

    public static final int ITEM_FEEDS_CREATED = 1;
    public static final int ITEM_FEEDS_FORKED = 2;
    public static final int ITEM_FEEDS_PUSHED_TO = 3;
    public static final int ITEM_FEEDS_STARRED = 4;

    public FeedsAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_FEEDS_CREATED) {

        } else if (viewType == ITEM_FEEDS_FORKED) {
            return new ForkedViewHolder(createViewFromLayout(R.layout.item_new_feed_action));
        } else if (viewType == ITEM_FEEDS_PUSHED_TO) {

        } else if (viewType == ITEM_FEEDS_STARRED) {

        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int viewType = items.get(position).getItemViewType();
        if (viewType == ITEM_FEEDS_CREATED) {
            CreatedViewHolder createdViewHolder = (CreatedViewHolder) holder;
            CreatedItem createdItem = (CreatedItem) items.get(position);

        } else if (viewType == ITEM_FEEDS_FORKED) {
            ForkedViewHolder forkedViewHolder = (ForkedViewHolder) holder;
            ForkedItem forkedItem = (ForkedItem) items.get(position);

        } else if (viewType == ITEM_FEEDS_PUSHED_TO) {
            PushedToViewHolder pushedToViewHolder = (PushedToViewHolder) holder;
            PushedToItem pushedToItem = (PushedToItem) items.get(position);

        } else if (viewType == ITEM_FEEDS_STARRED) {
            StarredViewHolder starredViewHolder = (StarredViewHolder) holder;
            StarredItem starredItem = (StarredItem) items.get(position);

        } else {
            throw new IllegalArgumentException("Do not support view type: " + viewType);
        }
    }
}
