package com.miuty.slowgit.ui.screen.main.issues;


import android.util.Log;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.miuty.slowgit.data.repository.issue.IssuesRepository;
import com.miuty.slowgit.data.repository.issue.IssuesRepositoryImpl;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;
import com.miuty.slowgit.ui.screen.main.issues.page.IssuesItemFragment;
import com.miuty.slowgit.ui.screen.main.issues.page.IssuesType;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

import static com.miuty.slowgit.ui.screen.main.issues.page.IssuesType.ASSIGNED;
import static com.miuty.slowgit.ui.screen.main.issues.page.IssuesType.CREATED;
import static com.miuty.slowgit.ui.screen.main.issues.page.IssuesType.MENTIONED;

public class IssuesPresenter extends BasePresenter<IssuesMvpView> {

    private static final String TAG = "IssuesPresenter";

    private IssuesRepository issuesRepository;
    private SchedulerProvider schedulerProvider;

    @Inject
    public IssuesPresenter(IssuesRepositoryImpl issuesRepository, SchedulerProviderImpl schedulerProvider) {
        this.issuesRepository = issuesRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public List<IssuesItemFragment> initListPagerFragment() {
        return Stream.of(
                IssuesItemFragment.newInstance(CREATED, "hungpn"),
                IssuesItemFragment.newInstance(ASSIGNED, "hungpn"),
                IssuesItemFragment.newInstance(MENTIONED, "hungpn"))
                .collect(Collectors.toList());
    }
}
