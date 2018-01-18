package com.miuty.slowgit.ui.screen.main.profile;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.miuty.slowgit.ui.base.activity.BaseFragment;

import java.util.List;

public class FragmentsPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragments;

    public FragmentsPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position);
    }

    public void remove(Fragment fragment) {
        if (fragments != null) {
            fragments.remove(fragment);
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        if (fragments != null) {
            fragments.remove(position);
            notifyDataSetChanged();
        }
    }
}
