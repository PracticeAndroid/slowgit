package com.miuty.slowgit.ui.screen.repo.code;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;
import com.miuty.slowgit.util.InputHelper;

import javax.inject.Inject;

import butterknife.BindView;

import static com.miuty.slowgit.util.BundleKeyConst.EXTRA_DEFAULT_BRANCH;
import static com.miuty.slowgit.util.BundleKeyConst.EXTRA_HTML_LINK;
import static com.miuty.slowgit.util.BundleKeyConst.EXTRA_LOGIN;
import static com.miuty.slowgit.util.BundleKeyConst.EXTRA_REPO_ID;
import static com.miuty.slowgit.util.BundleKeyConst.EXTRA_URL;

public class CodeFragment extends BaseMvpFragment<CodeMvpView, CodePresenter> implements CodeMvpView {

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Inject
    CodePagerAdapter codePagerAdapter;

    private String repoId;
    private String login;
    private String htmlLink;
    private String url;
    private String defaultBranch;

    public static CodeFragment newInstance(@NonNull String repoId, @NonNull String login,
                                           @NonNull String htmlLink, @NonNull String url, @NonNull String defaultBranch) {
        Bundle args = new Bundle();
        CodeFragment fragment = new CodeFragment();
        args.putString(EXTRA_REPO_ID, repoId);
        args.putString(EXTRA_LOGIN, login);
        args.putString(EXTRA_HTML_LINK, htmlLink);
        args.putString(EXTRA_URL, url);
        args.putString(EXTRA_DEFAULT_BRANCH, defaultBranch);
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
        if (getArguments() != null) {
            Bundle args = getArguments();
            repoId = args.getString(EXTRA_REPO_ID);
            login = args.getString(EXTRA_LOGIN);
            htmlLink = args.getString(EXTRA_HTML_LINK);
            url = args.getString(EXTRA_URL);
            defaultBranch = args.getString(EXTRA_DEFAULT_BRANCH);
            if (InputHelper.isEmpty(repoId) || InputHelper.isEmpty(login) /*|| InputHelper.isEmpty(url) || InputHelper.isEmpty(htmlLink)*/) {
                return;
            }
        }
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
