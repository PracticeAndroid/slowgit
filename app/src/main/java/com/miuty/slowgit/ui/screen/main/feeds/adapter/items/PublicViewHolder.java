package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import android.content.Context;
import android.view.View;

import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsViewHolder;
import com.miuty.slowgit.util.SpannableBuilder;

public class PublicViewHolder extends BaseFeedsViewHolder<PublicItem> {

    public PublicViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public SpannableBuilder buildTitle(PublicItem item) {
        return null;
    }

    @Override
    public SpannableBuilder buildDescription(PublicItem item) {
        return null;
    }
}
