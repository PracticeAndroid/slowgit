package com.miuty.slowgit.ui.base.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpDependencyInjection();
    }

    public void setUpDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }
}
