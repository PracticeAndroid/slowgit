package com.miuty.slowgit.ui.base.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpDependencyInjection();
    }

    public void setUpDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }
}
