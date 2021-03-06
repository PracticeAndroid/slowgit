package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsViewHolder;
import com.miuty.slowgit.util.SpannableBuilder;

public class ForkedViewHolder extends BaseFeedsViewHolder<ForkedItem> {

    public ForkedViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @NonNull
    @Override
    public int setDrawableTimeIcon() {
        return R.drawable.ic_fork;
    }

    @Override
    public SpannableBuilder buildTitle(ForkedItem item) {
        SpannableBuilder spannableBuilder = SpannableBuilder.builder();
        spannableBuilder.append(item.getActorName())
                .bold(" forked ")
                .append(item.getRepoName());
        return spannableBuilder;
    }

    @Override
    public SpannableBuilder buildDescription(ForkedItem item) {
        return null;
    }

    @Override
    public void onFeedClick(View view, ForkedItem item) {

    }
}
