package com.miuty.slowgit.ui.screen.main.issues;


import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

public class IssuesFragment extends BaseMvpFragment<IssuesMvpView, IssuesPresenter> implements IssuesMvpView {

    @Override
    protected int layoutId() {
        return R.layout.fragment_issues;
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
