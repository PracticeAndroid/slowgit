package com.miuty.slowgit.ui.screen.main.issues;


import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;
import com.miuty.slowgit.ui.screen.profile.overview.ProfileOverviewFragment;
import com.miuty.slowgit.util.BundleKeyConst;

import butterknife.BindView;

public class IssuesFragment extends BaseMvpFragment<IssuesMvpView, IssuesPresenter> implements IssuesMvpView {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public static IssuesFragment newInstance() {
        Bundle args = new Bundle();
        IssuesFragment fragment = new IssuesFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
