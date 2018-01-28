package com.miuty.slowgit.data.repository.issue.remote;

import com.miuty.slowgit.data.model.Issues;
import com.miuty.slowgit.di.qualifier.DefaultNetworkProviderContext;
import com.miuty.slowgit.provider.network.DefaultNetworkProvider;
import com.miuty.slowgit.provider.network.NetworkProvider;
import com.miuty.slowgit.util.ApiConst;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

/**
 * Created by Asus on 1/28/2018.
 */

public class IssuesRemoteServiceImpl implements IssuesRemoteService {

    private NetworkProvider networkProvider;
    private String apiUrl;

    @Inject
    public IssuesRemoteServiceImpl(@DefaultNetworkProviderContext DefaultNetworkProvider networkProvider,
                                   @Named(ApiConst.MAIN_API_URL_NAMED) String apiUrl) {
        this.networkProvider = networkProvider;
        this.apiUrl = apiUrl;
    }


    @Override
    public Observable<Issues> getIssues(String q, int page) {
        return null;
    }
}
