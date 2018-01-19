package com.miuty.slowgit.ui.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * base view holder with butter knife for view injection.
 */
public abstract class BaseViewHolder<I extends DisplayableItem> extends RecyclerView.ViewHolder {

    protected final Context context;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    /**
     * method to bind data to view
     *
     * @param item
     */
    public void bindData(I item) {

    }
}
