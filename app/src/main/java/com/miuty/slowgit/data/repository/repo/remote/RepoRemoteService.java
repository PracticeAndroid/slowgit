package com.miuty.slowgit.data.repository.repo.remote;


import com.miuty.slowgit.data.model.response.CommitResponse;

import java.util.List;

import io.reactivex.Observable;

public interface RepoRemoteService {

    Observable<List<CommitResponse>> getCommits(String owner, String repo);

}
