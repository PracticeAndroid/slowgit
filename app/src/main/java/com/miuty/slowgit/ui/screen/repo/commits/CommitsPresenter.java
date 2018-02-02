package com.miuty.slowgit.ui.screen.repo.commits;


import com.miuty.slowgit.data.repository.repo.RepoRepository;
import com.miuty.slowgit.data.repository.repo.RepoRepositoryImpl;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;

import javax.inject.Inject;

public class CommitsPresenter extends BasePresenter<CommitsMvpView> {

    private RepoRepository repoRepository;
    private SchedulerProvider schedulerProvider;

    @Inject
    public CommitsPresenter(RepoRepositoryImpl repoRepository, SchedulerProviderImpl schedulerProvider) {
        this.repoRepository = repoRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void getCommits(String owner, String repo) {
        disposeOnDestroy(
                repoRepository.getCommits(owner, repo)
                        .compose(schedulerProvider.observableComputationScheduler())
                        .doOnSubscribe(disposable -> {
                        })
                        .doOnTerminate(() -> {
                        })
                        .subscribe(
                                commitResponses -> {
                                },
                                throwable -> {
                                }
                        )
        );
    }
}
