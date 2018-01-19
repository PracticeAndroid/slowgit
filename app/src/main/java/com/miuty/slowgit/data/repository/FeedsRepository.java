package com.miuty.slowgit.data.repository;


import com.miuty.slowgit.data.model.Feed;

import java.util.List;

import io.reactivex.Observable;

public interface FeedsRepository {

    Observable<List<Feed>> getFeeds(int page);
}
