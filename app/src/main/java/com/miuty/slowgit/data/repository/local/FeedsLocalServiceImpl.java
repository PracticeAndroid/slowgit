package com.miuty.slowgit.data.repository.local;


import com.miuty.slowgit.data.model.Feed;
import com.miuty.slowgit.data.repository.FeedsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FeedsLocalServiceImpl implements FeedsRepository {

    @Inject
    public FeedsLocalServiceImpl() {}

    @Override
    public Observable<List<Feed>> getFeeds(int page) {
        return null;
    }
}
