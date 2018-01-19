package com.miuty.slowgit.ui.base.adapter;

import android.view.View;


public interface OnItemClickListener<T> {

    void onItemClick(int position, View v, T item);

    void onItemLongClick(int position, View v, T item);
}
