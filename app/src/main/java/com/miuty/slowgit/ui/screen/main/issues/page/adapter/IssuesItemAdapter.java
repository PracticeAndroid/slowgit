package com.miuty.slowgit.ui.screen.main.issues.page.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.IssueItem;
import com.miuty.slowgit.ui.base.adapter.ArrayAdapter;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;

/**
 * Created by Asus on 1/28/2018.
 */

public class IssuesItemAdapter extends ArrayAdapter<IssueItem, IssuesViewHolder> {

    private static final int TYPE_ITEM_ISSUES = 1;

    public IssuesItemAdapter(Context context, BaseViewHolder.OnItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ITEM_ISSUES:
                view = layoutInflater.inflate(R.layout.item_issue, parent, false);
                return new IssuesViewHolder(context, view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() - 1 == position) {
            return super.getItemViewType(position);
        } else {
            return TYPE_ITEM_ISSUES;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (getItemCount() - 1 != position) {
            IssueItem issueItem = items.get(position);
            ((IssuesViewHolder) holder).bindData(issueItem);
        }
    }
}
