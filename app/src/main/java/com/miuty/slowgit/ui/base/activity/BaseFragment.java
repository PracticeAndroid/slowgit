package com.miuty.slowgit.ui.base.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.miuty.slowgit.provider.navigator.FragmentNavigator;
import com.miuty.slowgit.ui.base.mvp.BaseMvpActivity;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {

    protected BaseMvpActivity baseActivity;

    @Inject
    protected FragmentNavigator fragmentNavigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseMvpActivity) {
            baseActivity = (BaseMvpActivity) context;
        } else {
            throw new IllegalArgumentException("Activity contain base fragment must be instance of BaseMvpActivity");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpDependencyInjection();
    }

    public void setUpDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    public String getTitle() {
        return "";
    }
}
