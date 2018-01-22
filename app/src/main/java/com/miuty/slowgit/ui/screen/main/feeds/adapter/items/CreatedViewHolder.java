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

    @NonNull
    @Override
    public int setDrawableTimeIcon() {
        return R.drawable.ic_github_icon;
    }

    @NonNull
    @Override
    public SpannableBuilder buildTitle(CreatedItem item) {
        return null;
    }

    @NonNull
    @Override
    public SpannableBuilder buildDescription(CreatedItem item) {
        return null;
    }
}
