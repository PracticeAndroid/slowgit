package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import android.content.Context;
import android.view.View;

import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsViewHolder;
import com.miuty.slowgit.util.SpannableBuilder;

public class ForkedViewHolder extends BaseFeedsViewHolder<ForkedItem> {

    public ForkedViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void bindData(ForkedItem item) {
        super.bindData(item);

    }

    @Override
    public SpannableBuilder buildTitle(ForkedItem item) {
        SpannableBuilder spannableBuilder = SpannableBuilder.builder();
        spannableBuilder.append(item.getActorName())
                .bold(" pushed to")
                .append(" ")
                .append(item.getPayloadRef())
                .append(" ")
                .bold("at")
                .append(" ")
                .append(item.getRepoName());
        return spannableBuilder;
    }

    @Override
    public SpannableBuilder buildDescription(ForkedItem item) {
        return null;
    }
}
