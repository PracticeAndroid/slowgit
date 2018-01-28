package com.miuty.slowgit.ui.screen.repo.files;


import android.os.Bundle;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

public class FilesFragment extends BaseMvpFragment<FilesMvpView, FilesPresenter>
    implements FilesMvpView {

    public static FilesFragment newInstance() {
        Bundle args = new Bundle();
        FilesFragment fragment = new FilesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_recycler_view;
    }

    @Override
    public String getTitle() {
        return "Files";
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
