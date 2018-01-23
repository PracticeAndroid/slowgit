package com.miuty.slowgit.ui.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<VH extends BaseViewHolder>
        extends RecyclerView.Adapter<VH> {

    protected Context context;
    protected LayoutInflater layoutInflater;
    protected BaseViewHolder.OnItemClickListener listener;

    public BaseAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public BaseViewHolder.OnItemClickListener getItemClickListener() {
        return listener;
    }

    public void setItemClickListener(BaseViewHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (listener != null) {
            holder.setOnItemClickListener(listener);
        }
    }

    /* public BaseAdapter(@NonNull Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.layoutInflater = LayoutInflater.from(context);
        this.items = new ArrayList<>();
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getItemViewType();
    }

    protected View createViewFromLayout(@LayoutRes int layoutId) {
        return createViewFromLayout(layoutId, null);
    }

    protected View createViewFromLayout(@LayoutRes int layoutId, ViewGroup parent) {
        return layoutInflater.inflate(layoutId, parent, false);
    }

    public void addItem(@NonNull I item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void addItem(@NonNull I item, int pos) {
        items.add(pos, item);
        notifyItemInserted(pos);
    }

    public void addItems(@NonNull List<I> items) {
        this.items.addAll(items);
        notifyItemRangeInserted(getItemCount(), getItemCount() + items.size() - 1);
    }

    public void remove(int pos) {
        this.items.remove(pos);
        notifyItemRemoved(pos);
    }*/
}
