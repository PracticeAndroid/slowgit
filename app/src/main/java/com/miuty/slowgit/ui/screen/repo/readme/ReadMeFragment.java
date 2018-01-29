package com.miuty.slowgit.ui.screen.repo.readme;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;
import com.miuty.slowgit.ui.widget.pretty.PrettifyWebView;

import butterknife.BindView;

public class ReadMeFragment extends BaseMvpFragment<ReadMeMvpView, ReadMePresenter> implements ReadMeMvpView {

    @BindView(R.id.webView)
    PrettifyWebView webView;

    public static ReadMeFragment newInstance() {
        Bundle args = new Bundle();
        ReadMeFragment fragment = new ReadMeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_read_me;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView.loadUrl("https://github.com/Vita3K/Vita3K/blob/master/README.md");
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
