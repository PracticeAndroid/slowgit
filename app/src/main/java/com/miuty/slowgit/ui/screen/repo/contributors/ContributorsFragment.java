package com.miuty.slowgit.ui.screen.repo.contributors;


import android.os.Bundle;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

public class ContributorsFragment extends BaseMvpFragment<ContributorsMvpView, ContributorsPresenter>
        implements ContributorsMvpView {

    public static ContributorsFragment newInstance() {
        Bundle args = new Bundle();
        ContributorsFragment fragment = new ContributorsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_recycler_view;
    }

    @Override
    public String getTitle() {
        return "Contributors";
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
