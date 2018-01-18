package com.miuty.slowgit.ui.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;
import com.miuty.slowgit.ui.screen.main.feeds.FeedsFragment;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainMvpView, MainPresenter> implements MainMvpView {

    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarIcon(R.drawable.ic_menu);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new FeedsFragment()).commit();
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
}
