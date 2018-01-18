package com.miuty.slowgit.ui.screen.profile.overview;


import android.os.Bundle;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

public class ProfileOverviewFragment extends BaseMvpFragment<ProfileOverviewMvpView, ProfileOverviewPresenter>
        implements ProfileOverviewMvpView {

    public static ProfileOverviewFragment newInstance() {
        Bundle args = new Bundle();
        ProfileOverviewFragment fragment = new ProfileOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_profile_overview;
    }

    @Override
    public String getTitle() {
        return "Overview";
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
