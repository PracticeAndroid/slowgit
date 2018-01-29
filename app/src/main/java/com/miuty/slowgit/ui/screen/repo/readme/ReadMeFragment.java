package com.miuty.slowgit.ui.screen.repo.readme;


import android.os.Bundle;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

public class ReadMeFragment extends BaseMvpFragment<ReadMeMvpView, ReadMePresenter> implements ReadMeMvpView {

    public static ReadMeFragment newInstance() {
        Bundle args = new Bundle();
        ReadMeFragment fragment = new ReadMeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_recycler_view;
    }

    @Override
    public String getTitle() {
        return "Read Me";
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
