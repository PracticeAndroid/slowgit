package com.miuty.slowgit.ui.base.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * base view holder with butter knife for view injection.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    protected OnItemClickListener listener;
    protected Context context;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public OnItemClickListener getOnItemClickListener() {
        return listener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public View findViewById(@IdRes int id) {
        return itemView.findViewById(id);
    }

    @Override
    public void onClick(View view) {
        if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
            listener.onItemClick(getAdapterPosition(), view);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
            listener.onItemLongClick(getAdapterPosition(), view);
        }
        return false;
    }

    public interface OnItemClickListener {

        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);
    }
}
