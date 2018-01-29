package com.miuty.slowgit.ui.screen.main.issues;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.Issues;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;
import com.miuty.slowgit.ui.screen.main.issues.page.adapter.IssuesPagerAdapter;
import com.miuty.slowgit.ui.screen.profile.overview.ProfileOverviewFragment;
import com.miuty.slowgit.util.BundleKeyConst;

import butterknife.BindView;

public class IssuesFragment extends BaseMvpFragment<IssuesMvpView, IssuesPresenter> implements IssuesMvpView {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private IssuesPagerAdapter pagerAdapter;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pagerAdapter = new IssuesPagerAdapter(getChildFragmentManager(), presenter.initListPagerFragment());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
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

    @Override
    public void hideRefreshLayout() {

    }
}
