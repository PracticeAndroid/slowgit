package com.miuty.slowgit.ui.screen.main.feeds;

import android.util.Log;

import com.miuty.slowgit.data.model.Feed;
import com.miuty.slowgit.data.repository.feed.FeedsRepository;
import com.miuty.slowgit.data.repository.feed.FeedsRepositoryImpl;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.CreatedItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.ForkedItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.PushedToItem;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.items.StarredItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class FeedsPresenter extends BasePresenter<FeedsMvpView> {

    private static final String TAG = "FeedsPresenter";

    private FeedsRepository feedsRepository;
    private SchedulerProvider schedulerProvider;

    @Inject
    public FeedsPresenter(FeedsRepositoryImpl feedsRepository, SchedulerProviderImpl schedulerProvider) {
        this.feedsRepository = feedsRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void getFeeds(int page) {
        Disposable disposable = feedsRepository.getFeeds(page)
                .compose(schedulerProvider.observableComputationScheduler())
                .doOnSubscribe(disposable1 -> {
                })
                .doOnTerminate(() -> {
                    if (view != null) {
                        view.hideRefreshLayout();
                    }
                })
                .subscribe(this::processFeedsResponse, throwable -> {
                    Log.e(TAG, "getFeeds: " + throwable.getMessage());
                });
        disposeOnDestroy(disposable);
    }

    public void processFeedsResponse(List<Feed> feeds) {
        List<BaseFeedsItem> feedsItems = new ArrayList<>();
        for (Feed feed : feeds) {
            if (feed.getType() != null) {
                switch (feed.getType()) {
                    case PUSH_EVENT:
                        feedsItems.add(new PushedToItem(feed));
                        break;
                    case FORK_EVENT:
                        feedsItems.add(new ForkedItem(feed));
                        break;
                    case WATCH_EVENT:
                        feedsItems.add(new StarredItem(feed));
                        break;
                    case CREATE_EVENT:
                        feedsItems.add(new CreatedItem(feed));
                        break;
                    case PUBLIC_EVENT:
                        break;
                    case NO_DEFINE:
                        break;
                    default:
                        Log.e(TAG, "Dont have Feed with type: " + feed.getType());
                        break;
                }
            }
        }
        // convert model to displayable item
        // show to view
        view.showFeedsOnRecyclerView(feedsItems);
    }

}
