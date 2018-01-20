package com.miuty.slowgit.ui.screen.profile.overview;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.profile.BasicProfile;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;
import com.miuty.slowgit.util.BundleKeyConst;

public class ProfileOverviewFragment extends BaseMvpFragment<ProfileOverviewMvpView, ProfileOverviewPresenter>
        implements ProfileOverviewMvpView {

    private String loginId;

    public static ProfileOverviewFragment newInstance(String loginId) {
        Bundle args = new Bundle();
        args.putString(BundleKeyConst.EXTRA_1,loginId);
        ProfileOverviewFragment fragment = new ProfileOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.loginId = getArguments().getString(BundleKeyConst.EXTRA_1);
        presenter.getBasicProfile(loginId);
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

    @Override
    public void onGetBasicProfileSusscessfully(BasicProfile basicProfile) {

    }

    @Override
    public void onGetBasicProfileFailed(Throwable throwable) {

    }
}
