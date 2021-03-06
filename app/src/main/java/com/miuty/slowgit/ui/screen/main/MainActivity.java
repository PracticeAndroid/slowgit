package com.miuty.slowgit.ui.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;
import com.miuty.slowgit.ui.screen.main.feeds.FeedsFragment;
import com.miuty.slowgit.ui.screen.main.issues.IssuesFragment;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainMvpView, MainPresenter> implements MainMvpView,
        BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.view_bottom_navigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarIcon(R.drawable.ic_menu);
        //getSupportFragmentManager().beginTransaction().add(R.id.container, new FeedsFragment()).commit();
        bottomNavigation.setSelectedItemId(R.id.bnv_feeds);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        bottomNavigation.setOnNavigationItemReselectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else if (item.getItemId() == R.id.search) {
            return true;
        } else if (item.getItemId() == R.id.notifications) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment visibleFragment = activityNavigator.getCurrentFragment(getSupportFragmentManager());

        switch (item.getItemId()) {
            case R.id.bnv_feeds:
                activityNavigator.replaceFragment(R.id.container, FeedsFragment.newInstance());
                break;
            case R.id.bnv_issues:
                activityNavigator.replaceFragment(R.id.container, IssuesFragment.newInstance());
                break;
            case R.id.bnv_pull_request:
                activityNavigator.replaceFragment(R.id.container, FeedsFragment.newInstance());
                break;
        }
        return true;
    }

    public void hideAndShowFragment(FragmentManager fragmentManager, Fragment hide, Fragment show) {
        fragmentManager
                .beginTransaction()
                .hide(hide)
                .add(R.id.container, show, show.getClass().getSimpleName())
                .commit();
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bnv_feeds:
                //activityNavigator.replaceFragment(R.id.container, FeedsFragment.newInstance());
                break;
            case R.id.bnv_issues:
                // activityNavigator.replaceFragment(R.id.container, FeedsFragment.newInstance());
                break;
            case R.id.bnv_pull_request:
                //activityNavigator.replaceFragment(R.id.container, FeedsFragment.newInstance());
                break;
        }
    }
}
