package com.miuty.slowgit.data.repository.repo.remote;


import com.miuty.slowgit.data.model.response.CommitResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoRestService {

    @GET("repos/{owner}/{repo}/commits")
    Observable<List<CommitResponse>> getCommits(@Path("owner") String owner,
                                                @Path("repo") String repo);

}
