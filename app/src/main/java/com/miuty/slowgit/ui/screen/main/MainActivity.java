package com.miuty.slowgit.ui.screen.main;

import android.os.Bundle;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;

public class MainActivity extends BaseMvpActivity<MainMvpView, MainPresenter> implements MainMvpView {


    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}