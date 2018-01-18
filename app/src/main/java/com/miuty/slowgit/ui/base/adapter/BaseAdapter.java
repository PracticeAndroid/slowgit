package com.miuty.slowgit.ui.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected List<? extends DisplayableItem> mItems;

    public BaseAdapter(@NonNull Context context, @NonNull List<? extends DisplayableItem> items) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getItemViewType();
    }

    protected View createViewFromLayout(@LayoutRes int layoutId) {
        return createViewFromLayout(layoutId, null);
    }

    protected View createViewFromLayout(@LayoutRes int layoutId, ViewGroup parent) {
        if (parent != null) {
            return mLayoutInflater.inflate(layoutId, parent, false);
        }
        return mLayoutInflater.inflate(layoutId, null, false);
    }
}
