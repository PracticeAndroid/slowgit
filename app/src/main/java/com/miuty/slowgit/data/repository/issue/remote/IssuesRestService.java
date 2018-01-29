package com.miuty.slowgit.data.repository.issue.remote;

import com.miuty.slowgit.data.model.Issues;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Asus on 1/28/2018.
 */

public interface IssuesRestService {

    /*https://api.github.com/search/issues?q=type:issue+involves:hungpn+is:open&page=1*/
    @GET("search/issues")
    Observable<Issues> getIssues(@Query(value = "q", encoded = true) String q, @Query("page") int page);
}
