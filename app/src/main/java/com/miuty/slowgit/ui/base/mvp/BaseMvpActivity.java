package com.miuty.slowgit.ui.base.mvp;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;

import com.miuty.slowgit.ui.base.activity.BaseActivity;
import com.miuty.slowgit.ui.dialog.CommonProgressDialogFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Asus on 1/9/2018.
 */


public abstract class BaseMvpActivity<V extends MvpView, P extends BasePresenter<V>> extends BaseActivity
        implements MvpView {

    private static final String TAG = "BaseMvpActivity";

    protected CommonProgressDialogFragment progressDialogFragment;
    private Unbinder unbinder;

    @LayoutRes
    protected abstract int layoutId();

    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        if (layoutId() != 0) {
            setContentView(layoutId());
            unbinder = ButterKnife.bind(this);
        }

        getLifecycle().addObserver(presenter);
        presenter.bindView((V) this);
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void noInternetConnection() {

    }

    @Override
    public void showProgress(String msg, boolean isCancelable) {
        if (!isFinishing()) { // check to prevent leak memory
            progressDialogFragment = (CommonProgressDialogFragment) getSupportFragmentManager().findFragmentByTag(CommonProgressDialogFragment.TAG);
            if (progressDialogFragment == null) {
                progressDialogFragment = CommonProgressDialogFragment.newInstance(msg, isCancelable);
            }
            progressDialogFragment.show(getSupportFragmentManager(), CommonProgressDialogFragment.TAG);
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialogFragment != null) {
            progressDialogFragment.dismiss();
        }
    }
}

