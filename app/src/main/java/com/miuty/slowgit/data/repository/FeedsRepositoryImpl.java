package com.miuty.slowgit.data.repository;


import com.miuty.slowgit.data.model.Feed;
import com.miuty.slowgit.data.repository.local.FeedsLocalServiceImpl;
import com.miuty.slowgit.data.repository.remote.FeedsRemoteServiceImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FeedsRepositoryImpl implements FeedsRepository {

    private FeedsRepository feedsRemoteService;
    private FeedsRepository feedsLocalService;

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
