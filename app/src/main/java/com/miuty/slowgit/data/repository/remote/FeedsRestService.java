package com.miuty.slowgit.data.repository.remote;


import com.miuty.slowgit.data.model.Feed;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FeedsRestService {

    @GET("users/{username}/received_events")
    Observable<List<Feed>> getFeeds(@Path("username") String username, @Query("page") int page);
}