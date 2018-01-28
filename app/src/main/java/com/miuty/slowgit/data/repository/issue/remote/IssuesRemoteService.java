package com.miuty.slowgit.data.repository.issue.remote;

import com.miuty.slowgit.data.model.Issues;

import io.reactivex.Observable;

/**
 * Created by Asus on 1/28/2018.
 */

public interface IssuesRemoteService {

    Observable<Issues> getCreatedIssues(String q, int page);
}
