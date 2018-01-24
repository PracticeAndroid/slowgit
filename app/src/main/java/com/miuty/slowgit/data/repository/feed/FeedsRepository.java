package com.miuty.slowgit.data.repository.feed;


import com.miuty.slowgit.data.model.Feed;

import java.util.List;

import io.reactivex.Observable;

public interface FeedsRepository {

    // local

    // remote
    Observable<List<Feed>> getFeeds(int page);

    // mix of remote and local
}
