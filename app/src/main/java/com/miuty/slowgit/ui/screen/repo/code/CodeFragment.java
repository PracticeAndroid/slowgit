package com.miuty.slowgit.ui.screen.repo.code;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class CodeFragment extends BaseMvpFragment<CodeMvpView, CodePresenter> implements CodeMvpView {

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Inject
    CodePagerAdapter codePagerAdapter;

    public static CodeFragment newInstance() {
        Bundle args = new Bundle();
        CodeFragment fragment = new CodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_code;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager.setAdapter(codePagerAdapter);

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
}
