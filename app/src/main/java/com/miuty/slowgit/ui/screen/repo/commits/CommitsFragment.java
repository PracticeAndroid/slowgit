package com.miuty.slowgit.ui.screen.repo.commits;


import android.os.Bundle;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

public class CommitsFragment extends BaseMvpFragment<CommitsMvpView, CommitsPresenter>
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
