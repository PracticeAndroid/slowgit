package com.miuty.slowgit.ui.screen.main.issues.page.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.miuty.slowgit.ui.base.activity.BaseFragment;
import com.miuty.slowgit.ui.screen.main.issues.page.IssuesItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 1/28/2018.
 */

public class IssuesPagerAdapter extends FragmentStatePagerAdapter {

    private List<IssuesItemFragment> baseFragments;

    public IssuesPagerAdapter(FragmentManager fm, @NonNull List<IssuesItemFragment> baseFragments) {
        super(fm);
        this.baseFragments = baseFragments;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "hung";
    }

    @Override
    public Fragment getItem(int position) {
        return baseFragments.get(position);
    }

    @Override
    public int getCount() {
        return baseFragments.size();
    }
}
