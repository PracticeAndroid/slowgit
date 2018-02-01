package com.miuty.slowgit.ui.screen.profile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.User;
import com.miuty.slowgit.ui.base.adapter.ArrayAdapter;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;

/**
 * Created by igneel on 2/2/2018.
 */

public class UserAdapter extends ArrayAdapter<User, UserViewHolder> {

    private static final int TYPE_ITEM_USER = 1;

    public UserAdapter(@NonNull Context context, BaseViewHolder.OnItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ITEM_USER:
                view = layoutInflater.inflate(R.layout.item_user, parent, false);
                return new UserViewHolder(context, view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() - 1 == position) {
            return super.getItemViewType(position);
        } else {
            return TYPE_ITEM_USER;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (getItemCount() - 1 != position) {
            User item = items.get(position);
            ((UserViewHolder) holder).bind(item);
        }
    }
}
