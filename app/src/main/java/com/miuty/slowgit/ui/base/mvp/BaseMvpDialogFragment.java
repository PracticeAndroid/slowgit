package com.miuty.slowgit.ui.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miuty.slowgit.ui.base.dialog.BaseDialogFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Asus on 1/16/2018.
 */

public abstract class BaseMvpDialogFragment<V extends MvpView, P extends BasePresenter<V>> extends BaseDialogFragment
        implements MvpView {

    protected V callBack;

    @Nullable
    protected Unbinder unbinder;

    @LayoutRes
    protected abstract int fragmentLayout();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MvpView) {
            callBack = (V) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (fragmentLayout() != 0) {
            View view = inflater.inflate(fragmentLayout(), container, false);
            unbinder = ButterKnife.bind(this, view);
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void noInternetConnection() {
        callBack.noInternetConnection();
    }

    @Override
    public void showProgress(String msg, boolean isCancelable) {
        callBack.showProgress(msg, isCancelable);
    }

    @Override
    public void hideProgress() {
        callBack.hideProgress();
    }

    @Override
    public void someThingError(String msg) {
        callBack.someThingError(msg);
    }
}
