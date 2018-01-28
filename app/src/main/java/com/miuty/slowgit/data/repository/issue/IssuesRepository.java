package com.miuty.slowgit.data.repository.issue;

import com.miuty.slowgit.data.model.Issues;
import com.miuty.slowgit.ui.screen.main.issues.page.IssuesType;

import io.reactivex.Observable;

/**
 * Created by Asus on 1/28/2018.
 */

public interface IssuesRepository {

    Observable<Issues> getIssues(int page, IssuesType issuesType);
}
