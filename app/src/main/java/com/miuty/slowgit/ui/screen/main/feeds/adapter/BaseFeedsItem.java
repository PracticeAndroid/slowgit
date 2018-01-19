package com.miuty.slowgit.ui.screen.main.feeds.adapter;


import com.miuty.slowgit.data.model.Feed;
import com.miuty.slowgit.ui.base.adapter.DisplayableItem;

public abstract class BaseFeedsItem implements DisplayableItem {

    protected final Feed feed;

    protected BaseFeedsItem(Feed feed) {
        this.feed = feed;
    }

    public String getActorName() {
        if (feed.getActor() != null) {
            return feed.getActor().getDisplayLogin();
        }
        return "";
    }

    public String getActorAvatar() {
        if (feed.getActor() != null) {
            return feed.getActor().getAvatarUrl();
        }
        return "";
    }
}
