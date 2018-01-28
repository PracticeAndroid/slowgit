package com.miuty.slowgit.ui.screen.profile.repositories.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.Repo;
import com.miuty.slowgit.ui.base.adapter.ArrayAdapter;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.CreatedViewHolder;

/**
 * Created by igneel on 1/28/2018.
 */

public class RepoAdapter extends ArrayAdapter<Repo, RepositoryViewHolder> {

    public RepoAdapter(@NonNull Context context, BaseViewHolder.OnItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepositoryViewHolder(context, layoutInflater.inflate(R.layout.item_repository, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        super.onBindViewHolder(holder, position);
        if (getItemCount() - 1 != position) {
            Repo item = items.get(position);
            ((RepositoryViewHolder) holder).bind(item);
        }
    }
}
