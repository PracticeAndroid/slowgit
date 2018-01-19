package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import android.content.Context;
import android.view.View;

import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsViewHolder;
import com.miuty.slowgit.util.SpannableBuilder;

public class CreatedViewHolder extends BaseFeedsViewHolder<CreatedItem> {

    public CreatedViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public SpannableBuilder buildTitle(CreatedItem item) {
        return null;
    }

    @Override
    public SpannableBuilder buildDescription(CreatedItem item) {
        return null;
    }
}
