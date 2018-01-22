package com.miuty.slowgit.ui.screen.main.feeds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.adapter.ArrayAdapter;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.CreatedItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.CreatedViewHolder;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.ForkedItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.ForkedViewHolder;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.PublicItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.PublicViewHolder;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.PushedToItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.PushedToViewHolder;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.StarredItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.StarredViewHolder;

public class FeedsAdapter extends ArrayAdapter<BaseFeedsItem, BaseFeedsViewHolder> {

    public static final int TYPE_ITEM_FEEDS_CREATED = 1;
    public static final int TYPE_ITEM_FEEDS_FORKED = 2;
    public static final int TYPE_ITEM_FEEDS_PUSHED_TO = 3;
    public static final int TYPE_ITEM_FEEDS_STARRED = 4;
    public static final int TYPE_ITEM_FEEDS_PUBLIC = 5;
    public static final int TYPE_ITEM_FEEDS_WATCH = 6;

    public FeedsAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_ITEM_FEEDS_CREATED:
                view = layoutInflater.inflate(R.layout.item_new_feed_action, parent, false);
                return new CreatedViewHolder(context, view);
            case TYPE_ITEM_FEEDS_FORKED:
                view = layoutInflater.inflate(R.layout.item_new_feed_action, parent, false);
                return new ForkedViewHolder(context, view);
            case TYPE_ITEM_FEEDS_PUSHED_TO:
                view = layoutInflater.inflate(R.layout.item_new_feed_action, parent, false);
                return new PushedToViewHolder(context, view);
            case TYPE_ITEM_FEEDS_STARRED:
                view = layoutInflater.inflate(R.layout.item_new_feed_action, parent, false);
                return new StarredViewHolder(context, view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        BaseFeedsItem item = items.get(position);
        if (item instanceof PublicItem) {
            return TYPE_ITEM_FEEDS_PUBLIC;
        } else if (item instanceof PushedToItem) {
            return TYPE_ITEM_FEEDS_PUSHED_TO;
        } else if (item instanceof StarredItem) {
            return TYPE_ITEM_FEEDS_STARRED;
        } else if (item instanceof ForkedItem) {
            return TYPE_ITEM_FEEDS_FORKED;
        } else if (item instanceof CreatedItem) {
            return TYPE_ITEM_FEEDS_CREATED;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        BaseFeedsItem item = items.get(position);
        if (holder instanceof PublicViewHolder && item instanceof PublicItem) {
            ((PublicViewHolder) holder).bindData((PublicItem) item);
        } else if (holder instanceof PushedToViewHolder && item instanceof PushedToItem) {
            ((PushedToViewHolder) holder).bindData((PushedToItem) item);
        } else if (holder instanceof StarredViewHolder && item instanceof StarredItem) {
            ((StarredViewHolder) holder).bindData((StarredItem) item);
        } else if (holder instanceof ForkedViewHolder && item instanceof ForkedItem) {
            ((ForkedViewHolder) holder).bindData((ForkedItem) item);
        } else if (holder instanceof CreatedViewHolder && item instanceof CreatedItem) {
            ((CreatedViewHolder) holder).bindData((CreatedItem) item);
        }
        super.onBindViewHolder(holder, position);
    }
}
