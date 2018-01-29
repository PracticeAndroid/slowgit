package com.miuty.slowgit.ui.screen.repo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.miuty.slowgit.R;
import com.miuty.slowgit.di.qualifier.ActivityFragmentManager;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;
import com.miuty.slowgit.ui.screen.repo.code.CodeFragment;

import javax.inject.Inject;

import butterknife.BindView;

import static com.miuty.slowgit.util.BundleKeyConst.EXTRA_LOGIN;
import static com.miuty.slowgit.util.BundleKeyConst.EXTRA_REPO_ID;

public class RepoActivity extends BaseMvpActivity<RepoMvpView, RepoPresenter> implements RepoMvpView,
        BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    @BindView(R.id.view_bottom_navigation)
    BottomNavigationView bottomNavigation;

    @Inject
    @ActivityFragmentManager
    FragmentManager fragmentManager;

    private String repoId;
    private String login;

    public static Intent createIntent(@NonNull Context context, @NonNull String repoId, @NonNull String login) {
        Intent intent = new Intent(context, RepoActivity.class);
        intent.putExtra(EXTRA_REPO_ID, repoId);
        intent.putExtra(EXTRA_LOGIN, login);
        return intent;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_repo;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repoId = getIntent().getStringExtra(EXTRA_REPO_ID);
        login = getIntent().getStringExtra(EXTRA_LOGIN);
        bottomNavigation.setSelectedItemId(R.id.bnv_code);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        bottomNavigation.setOnNavigationItemReselectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bnv_code:
                activityNavigator.replaceFragment(R.id.container, CodeFragment.newInstance(repoId, login,
                        null, null, null));
                break;
            case R.id.bnv_issues:
                activityNavigator.replaceFragment(R.id.container, CodeFragment.newInstance(repoId, login,
                        null, null, null));
                break;
            case R.id.bnv_pull_request:
                activityNavigator.replaceFragment(R.id.container, CodeFragment.newInstance(repoId, login,
                        null, null, null));
                break;
        }
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}
