package com.miuty.slowgit.ui.screen.profile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.Repo;
import com.miuty.slowgit.ui.base.adapter.ArrayAdapter;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;

/**
 * Created by igneel on 1/28/2018.
 */

public class RepoAdapter extends ArrayAdapter<Repo, RepositoryViewHolder> {

    private static final int TYPE_ITEM_REPO = 1;

    public RepoAdapter(@NonNull Context context, BaseViewHolder.OnItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ITEM_REPO:
                view = layoutInflater.inflate(R.layout.item_repository, parent, false);
                return new RepositoryViewHolder(context, view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() - 1 == position) {
            return super.getItemViewType(position);
        } else {
            return TYPE_ITEM_REPO;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (getItemCount() - 1 != position) {
            Repo item = items.get(position);
            ((RepositoryViewHolder) holder).bind(item);
        }
    }
}
