package com.miuty.slowgit.ui.screen.repo.commits;


import android.os.Bundle;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;
import com.miuty.slowgit.ui.base.mvp.BaseMvpListFragment;

public class CommitsFragment extends BaseMvpListFragment<CommitsMvpView, CommitsPresenter, CommitAdapter>
        implements CommitsMvpView {

    public static CommitsFragment newInstance() {
        Bundle args = new Bundle();
        CommitsFragment fragment = new CommitsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_commits;
    }

    @Override
    public String getTitle() {
        return "Commits";
    }

    @Override
    protected CommitAdapter createAdapter() {
        return null;
    }

    @Override
    protected void doRefresh() {

    }

    @Override
    public void hideRefreshLayout() {

    }

    @Override
    public void noInternetConnection() {

    }

    @Override
    public void someThingError(String msg) {

    }

    @Override
    public void showProgress(String msg, boolean isCancelable) {

    }

    @Override
    public void hideProgress() {

    }
}
