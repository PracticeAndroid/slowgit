package com.miuty.slowgit.ui.base.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

public abstract class BaseAdapter<VH extends BaseViewHolder>
        extends RecyclerView.Adapter<VH> {

    protected Context context;
    protected LayoutInflater layoutInflater;
    protected BaseViewHolder.OnItemClickListener listener;

    public BaseAdapter(Context context, BaseViewHolder.OnItemClickListener listener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    public BaseViewHolder.OnItemClickListener getItemClickListener() {
        return listener;
    }

    public void setItemClickListener(BaseViewHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    @CallSuper
    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (listener != null) {
            holder.setOnItemClickListener(listener);
        }
    }
}
