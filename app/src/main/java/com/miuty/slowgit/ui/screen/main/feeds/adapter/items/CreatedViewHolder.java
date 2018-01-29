package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsViewHolder;
import com.miuty.slowgit.util.SpannableBuilder;

public class CreatedViewHolder extends BaseFeedsViewHolder<CreatedItem> {

    public CreatedViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public int setDrawableTimeIcon() {
        return R.drawable.ic_document;
    }

    @NonNull
    @Override
    public SpannableBuilder buildTitle(CreatedItem item) {
        SpannableBuilder spannableBuilder = SpannableBuilder.builder();

        spannableBuilder
                .append(item.getActorName())
                .bold(" created")
                .append(" repository")
                .bold(" at ")
                .append(item.getRepoNamePushTo());

        return spannableBuilder;
    }

    @NonNull
    @Override
    public SpannableBuilder buildDescription(CreatedItem item) {
        return null;
    }

    @Override
    public void onFeedClick(View view, CreatedItem item) {

    }
}
