package com.miuty.slowgit.ui.screen.repo;


import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;

public class RepoActivity extends BaseMvpActivity<RepoMvpView, RepoPresenter> implements RepoMvpView {

    @Override
    protected int layoutId() {
        return R.layout.activity_repo;
    }
}
