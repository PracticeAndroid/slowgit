package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import com.miuty.slowgit.data.model.Feed;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.FeedsAdapter;

public class ForkedItem extends BaseFeedsItem {

    public ForkedItem(Feed feed) {
        super(feed);
    }

    public String getPayloadRef() {
        try {
            String ref = feed.getPayload().getRef();
            if (ref.startsWith("refs/heads/")) {
                ref = ref.substring(11);
            }
            return ref;
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return "";
        }
    }

    public String getRepoName() {
        if (feed.getRepo() != null) {
            return feed.getRepo().getName();
        }
        return "";
    }

    @Override
    public int getItemViewType() {
        return FeedsAdapter.ITEM_FEEDS_FORKED;
    }
}
