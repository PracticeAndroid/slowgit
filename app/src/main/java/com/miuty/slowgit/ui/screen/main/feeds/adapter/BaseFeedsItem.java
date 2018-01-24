package com.miuty.slowgit.ui.screen.main.feeds.adapter;


import com.miuty.slowgit.data.model.Feed;

public abstract class  BaseFeedsItem {

    protected final Feed feed;

    protected BaseFeedsItem() {
        feed = new Feed();
    }

    protected BaseFeedsItem(Feed feed) {
        this.feed = feed;
    }

    public Feed getFeed() {
        return feed;
    }

    // use for common
    public String getActorName() {
        if (feed.getActor() != null) {
            return feed.getActor().getDisplayLogin();
        }
        return "";
    }

    // use for showing avatar
    public String getActorAvatar() {
        if (feed.getActor() != null) {
            return feed.getActor().getAvatarUrl();
        }
        return "";
    }

    // use for PUSH_EVENT
    public String getBranchPushedTo() {
        if (feed.getPayload() != null) {
            String ref = feed.getPayload().getRef();
            if (ref.startsWith("refs/heads/")) {
                ref = ref.substring("refs/heads/".length());
            }
            return ref;
        }
        return "";
    }

    // use for PUSH_EVENT
    public String getRepoNamePushTo() {
        if (feed.getRepo() != null) {
            return feed.getRepo().getName();
        }
        return "";
    }
}
