package com.miuty.slowgit.ui.screen.main.pull_requests;


import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

public class PullRequestsFragment extends BaseMvpFragment<PullRequestsMvpView, PullRequestsPresenter> implements PullRequestsMvpView {

    @Override
    protected int layoutId() {
        return R.layout.fragment_pull_requests;
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
