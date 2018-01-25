package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsViewHolder;
import com.miuty.slowgit.util.SpannableBuilder;

public class PublicViewHolder extends BaseFeedsViewHolder<PublicItem> {

    public PublicViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @NonNull
    @Override
    public int setDrawableTimeIcon() {
        return R.drawable.ic_github_icon;
    }

    @Override
    public SpannableBuilder buildTitle(PublicItem item) {
        return null;
    }

    @Override
    public SpannableBuilder buildDescription(PublicItem item) {
        return null;
    }

    @Override
    public void onFeedClick(View view) {

    }
}
