package com.miuty.slowgit.data.repository.feed.remote;

import com.miuty.slowgit.data.model.Feed;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Asus on 1/24/2018.
 */

public interface FeedsRemoteService {

    Observable<List<Feed>> getFeeds(int page);
}
