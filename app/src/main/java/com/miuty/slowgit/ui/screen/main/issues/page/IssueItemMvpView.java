package com.miuty.slowgit.ui.screen.main.issues.page;

import com.miuty.slowgit.data.model.Issues;
import com.miuty.slowgit.ui.base.mvp.MvpListView;


public interface IssueItemMvpView extends MvpListView {

    void onGetIssuesSuccessfully(Issues issues);

    void onGetIssuesFailed(Throwable throwable);
}
