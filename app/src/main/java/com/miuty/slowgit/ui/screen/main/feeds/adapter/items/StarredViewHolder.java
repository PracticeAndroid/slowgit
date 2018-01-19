package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import android.content.Context;
import android.view.View;

import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsViewHolder;
import com.miuty.slowgit.util.SpannableBuilder;

public class StarredViewHolder extends BaseFeedsViewHolder<StarredItem> {

    public StarredViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public SpannableBuilder buildTitle(StarredItem item) {
        return null;
    }

    @Override
    public SpannableBuilder buildDescription(StarredItem item) {
        return null;
    }
}
