package com.miuty.slowgit.ui.screen.profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.activity.BaseFragment;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;
import com.miuty.slowgit.ui.screen.profile.overview.ProfileOverviewFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ProfileActivity extends BaseMvpActivity<ProfileMvpView, ProfilePresenter> implements ProfileMvpView {

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Inject
    FragmentManager fragmentManager;

    private FragmentsPagerAdapter fragmentsPagerAdapter;

    private String loginId = "dutn158";

    @Override
    protected int layoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<BaseFragment> baseFragmentList = new ArrayList<>();
        baseFragmentList.add(ProfileOverviewFragment.newInstance("dutn158"));
        baseFragmentList.add(ProfileOverviewFragment.newInstance("wuchong"));
        baseFragmentList.add(ProfileOverviewFragment.newInstance("dutn158"));

        fragmentsPagerAdapter = new FragmentsPagerAdapter(fragmentManager, baseFragmentList);
        mViewPager.setAdapter(fragmentsPagerAdapter);

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
