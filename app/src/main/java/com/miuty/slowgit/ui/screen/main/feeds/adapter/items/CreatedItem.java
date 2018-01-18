package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.FeedsAdapter;

public class CreatedItem extends BaseFeedsItem {

    @Override
    public int getItemViewType() {
        return FeedsAdapter.ITEM_FEEDS_CREATED;
    }
}
