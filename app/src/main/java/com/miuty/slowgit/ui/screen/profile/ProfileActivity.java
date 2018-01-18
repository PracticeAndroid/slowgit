package com.miuty.slowgit.ui.screen.profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class ProfileActivity extends BaseMvpActivity<ProfileMvpView, ProfilePresenter> implements ProfileMvpView {

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Inject
    FragmentsPagerAdapter fragmentsPagerAdapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager.setAdapter(fragmentsPagerAdapter);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
