package com.miuty.slowgit.ui.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 1/22/2018.
 */

public abstract class ArrayAdapter<T, VH extends BaseViewHolder> extends LoadMoreAdapter<VH> {

    protected List<T> items;

    public ArrayAdapter(@NonNull Context context, BaseViewHolder.OnItemClickListener listener) {
        super(context, listener);
        items = new ArrayList<>();
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size() + 1; // add 1 is to use load more item
    }

    public void addItem(@NonNull T item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void addItem(@NonNull T item, int pos) {
        items.add(pos, item);
        notifyItemInserted(pos);
    }

    public void addItems(@NonNull List<T> items) {
        this.items.addAll(items);
        notifyItemRangeInserted(getItemCount(), getItemCount() + items.size() - 1);
    }

    public void remove(int pos) {
        this.items.remove(pos);
        notifyItemRemoved(pos);
    }

    public void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }
}
