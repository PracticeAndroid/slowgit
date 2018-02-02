package com.miuty.slowgit.data.repository.repo;


import com.miuty.slowgit.data.model.response.CommitResponse;
import com.miuty.slowgit.data.repository.repo.remote.RepoRemoteService;
import com.miuty.slowgit.data.repository.repo.remote.RepoRemoteServiceImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RepoRepositoryImpl implements RepoRepository {

    private RepoRemoteService repoRemoteService;

    @Inject
    public RepoRepositoryImpl(RepoRemoteServiceImpl repoRemoteService) {
        this.repoRemoteService = repoRemoteService;
    }

    @Override
    public Observable<List<CommitResponse>> getCommits(String owner, String repo) {
        return repoRemoteService.getCommits(owner, repo);
    }

}
