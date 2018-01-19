package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import android.content.Context;
import android.view.View;

import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsViewHolder;
import com.miuty.slowgit.util.SpannableBuilder;

public class PushedToViewHolder extends BaseFeedsViewHolder<PushedToItem> {

    public PushedToViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public SpannableBuilder buildTitle(PushedToItem item) {
        return null;
    }

    @Override
    public SpannableBuilder buildDescription(PushedToItem item) {
        return null;
    }
}
