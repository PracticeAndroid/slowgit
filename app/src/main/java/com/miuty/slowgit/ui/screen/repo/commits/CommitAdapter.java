package com.miuty.slowgit.ui.screen.repo.commits;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.miuty.slowgit.ui.base.adapter.BaseAdapter;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;

public class CommitAdapter extends BaseAdapter {

    public CommitAdapter(Context context, BaseViewHolder.OnItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
