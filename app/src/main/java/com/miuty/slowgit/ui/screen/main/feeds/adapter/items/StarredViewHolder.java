package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsViewHolder;
import com.miuty.slowgit.util.SpannableBuilder;

public class StarredViewHolder extends BaseFeedsViewHolder<StarredItem> {

    public StarredViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @NonNull
    @Override
    public int setDrawableTimeIcon() {
        return R.drawable.ic_star;
    }

    @Override
    public SpannableBuilder buildTitle(StarredItem item) {
        SpannableBuilder spannableBuilder = SpannableBuilder.builder();

        spannableBuilder
                .append(item.getActorName())
                .bold(" starred ")
                .append(item.getRepoNamePushTo());

        return spannableBuilder;
    }

    @Override
    public SpannableBuilder buildDescription(StarredItem item) {
        return null;
    }
}
