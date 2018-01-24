package com.miuty.slowgit.data.repository.feed;


import com.miuty.slowgit.data.model.Feed;
import com.miuty.slowgit.data.repository.feed.local.FeedsLocalService;
import com.miuty.slowgit.data.repository.feed.local.FeedsLocalServiceImpl;
import com.miuty.slowgit.data.repository.feed.remote.FeedsRemoteService;
import com.miuty.slowgit.data.repository.feed.remote.FeedsRemoteServiceImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FeedsRepositoryImpl implements FeedsRepository {

    private FeedsRemoteService feedsRemoteService;
    private FeedsLocalService feedsLocalService;

    @Inject
    public FeedsRepositoryImpl(FeedsRemoteServiceImpl feedsRemoteService, FeedsLocalServiceImpl feedsLocalService) {
        this.feedsRemoteService = feedsRemoteService;
        this.feedsLocalService = feedsLocalService;
    }

    @Override
    public Observable<List<Feed>> getFeeds(int page) {
        return feedsRemoteService.getFeeds(page);
    }
}
