package com.miuty.slowgit.data.repository.issue;

import com.miuty.slowgit.data.model.Issues;
import com.miuty.slowgit.data.repository.issue.local.IssuesLocalService;
import com.miuty.slowgit.data.repository.issue.local.IssuesLocalServiceImpl;
import com.miuty.slowgit.data.repository.issue.remote.IssuesRemoteService;
import com.miuty.slowgit.data.repository.issue.remote.IssuesRemoteServiceImpl;
import com.miuty.slowgit.di.qualifier.DefaultNetworkProviderContext;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;
import com.miuty.slowgit.ui.screen.main.issues.page.IssuesType;
import com.miuty.slowgit.util.ApiConst;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

import static com.miuty.slowgit.ui.screen.main.issues.page.IssuesType.CREATED;

/**
 * Created by Asus on 1/28/2018.
 */

public class IssuesRepositoryImpl implements IssuesRepository {

    private IssuesRemoteService issuesRemoteService;
    private IssuesLocalService issuesLocalService;

    @Inject
    public IssuesRepositoryImpl(IssuesRemoteServiceImpl issuesRemoteService, IssuesLocalServiceImpl issuesLocalService) {
        this.issuesRemoteService = issuesRemoteService;
        this.issuesLocalService = issuesLocalService;
    }

    @Override
    public Observable<Issues> getIssues(int page, IssuesType issuesType, String loginId, String status) {
        StringBuilder issuesQuery = new StringBuilder();
        issuesQuery.append("is:issue+")
                .append("is:").append(status).append("+");
        switch (issuesType) {
            case CREATED:
                issuesQuery.append("author:").append(loginId);
                break;
            case ASSIGNED:
                issuesQuery.append("assignee:").append(loginId);
                break;
            case MENTIONED:
                issuesQuery.append("mentions:").append(loginId);
                break;
            default:
                return Observable.error(new IllegalArgumentException("issuesType not found"));
        }
        return issuesRemoteService.getCreatedIssues(issuesQuery.toString(), page);
    }
}
