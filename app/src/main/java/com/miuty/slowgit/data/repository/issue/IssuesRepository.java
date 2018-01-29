package com.miuty.slowgit.data.repository.issue;

import com.miuty.slowgit.data.model.Issues;
import com.miuty.slowgit.ui.screen.main.issues.page.IssuesType;

import io.reactivex.Observable;

/**
 * Created by Asus on 1/28/2018.
 */

public interface IssuesRepository {

    /**
     *
     * @param page for load more
     * @param issuesType
     * @param loginId
     * @param status: open or closed
     * @return
     */
    Observable<Issues> getIssues(int page, IssuesType issuesType, String loginId, String status);
}
