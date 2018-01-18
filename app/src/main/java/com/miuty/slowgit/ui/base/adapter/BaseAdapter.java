package com.miuty.slowgit.ui.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<? extends DisplayableItem> mItems;

    public BaseAdapter(@NonNull List<? extends DisplayableItem> items) {
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getItemViewType();
    }
}
