package com.miuty.slowgit.ui.screen.main.issues.page;


import android.util.Log;

import com.miuty.slowgit.data.repository.issue.IssuesRepository;
import com.miuty.slowgit.data.repository.issue.IssuesRepositoryImpl;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class IssueItemPresenter extends BasePresenter<IssueItemMvpView> {

    private static final String TAG = "IssueItemPresenter";

    private IssuesRepository issuesRepository;
    private SchedulerProvider schedulerProvider;

    /*Data object*/
    private IssuesType issuesType = IssuesType.CREATED;
    private String loginId = "hungpn";

    public IssuesType getIssuesType() {
        return issuesType;
    }

    public void setIssuesType(IssuesType issuesType) {
        this.issuesType = issuesType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Inject
    public IssueItemPresenter(IssuesRepositoryImpl issuesRepository, SchedulerProviderImpl schedulerProvider) {
        this.issuesRepository = issuesRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void getIssues(int page, String status) {
        Disposable disposable = issuesRepository.getIssues(page, issuesType, loginId, status)
                .compose(schedulerProvider.observableComputationScheduler())
                .doOnSubscribe(disposable1 -> {
                })
                .doOnTerminate(() -> {
                    if (view != null) {
                        view.hideRefreshLayout();
                    }
                })
                .subscribe(issues -> {
                    if (view != null) {
                        view.onGetIssuesSuccessfully(issues);
                    }
                }, throwable -> {
                    Log.e(TAG, "getFeeds: " + throwable.getMessage());
                });

        disposeOnDestroy(disposable);
    }
}
