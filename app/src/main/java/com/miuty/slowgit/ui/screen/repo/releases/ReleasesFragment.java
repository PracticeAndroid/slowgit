package com.miuty.slowgit.ui.screen.repo.releases;


import android.os.Bundle;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

public class ReleasesFragment extends BaseMvpFragment<ReleasesMvpView, ReleasesPresenter>
        implements ReleasesMvpView {

    public static ReleasesFragment newInstance() {
        Bundle args = new Bundle();
        ReleasesFragment fragment = new ReleasesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_recycler_view;
    }

    @Override
    public String getTitle() {
        return "Releases";
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
