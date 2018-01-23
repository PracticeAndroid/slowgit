package com.miuty.slowgit.data.repository.remote;


import com.miuty.slowgit.data.model.Feed;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FeedsRestService {

    @GET("users/{username}/received_events?client_id=4fa5fcacb0ec316a2758&client_secret=1be5777b6f9a6a4a09fa6cc3cdec62f3c579dbe5")
    Observable<List<Feed>> getFeeds(@Path("username") String username, @Query("page") int page);
}
