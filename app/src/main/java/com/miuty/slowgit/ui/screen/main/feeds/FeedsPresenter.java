package com.miuty.slowgit.ui.screen.main.feeds;

import com.miuty.slowgit.data.repository.FeedsRepository;
import com.miuty.slowgit.data.repository.FeedsRepositoryImpl;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class FeedsPresenter extends BasePresenter<FeedsMvpView> {

    private FeedsRepository feedsRepository;
    private SchedulerProvider schedulerProvider;

    @Inject
    public FeedsPresenter(FeedsRepositoryImpl feedsRepository, SchedulerProviderImpl schedulerProvider) {
        this.feedsRepository = feedsRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void getFeeds() {
        Disposable disposable = feedsRepository.getFeeds(1)
                .compose(schedulerProvider.observableComputationScheduler())
                .doOnSubscribe(disposable1 -> {
                })
                .doOnTerminate(() -> {
                })
                .subscribe(feeds -> {
                }, throwable -> {
                });
        disposeOnDestroy(disposable);
    }

}
