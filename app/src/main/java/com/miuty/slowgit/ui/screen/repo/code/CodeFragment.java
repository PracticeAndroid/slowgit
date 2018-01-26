package com.miuty.slowgit.ui.screen.repo.code;


import android.support.v4.view.ViewPager;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;

import butterknife.BindView;

public class CodeFragment extends BaseMvpFragment<CodeMvpView, CodePresenter> implements CodeMvpView {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected int layoutId() {
        return R.layout.fragment_code;
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
