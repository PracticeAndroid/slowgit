package com.miuty.slowgit.ui.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * base view holder with butter knife for view injection.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public interface OnItemClickListener<T> {

        void onItemClick(int position, View v, T item);

        void onItemLongClick(int position, View v, T item);
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
