package com.miuty.slowgit.ui.screen.main.feeds;


import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

public class FeedsFragment extends BaseMvpFragment<FeedsMvpView, FeedsPresenter> implements FeedsMvpView {

    @Override
    protected int layoutId() {
        return 0;
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
