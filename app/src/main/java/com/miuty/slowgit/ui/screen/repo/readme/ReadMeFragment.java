package com.miuty.slowgit.ui.screen.repo.readme;


import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

public class ReadMeFragment extends BaseMvpFragment<ReadMeMvpView, ReadMePresenter> implements ReadMeMvpView {

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
