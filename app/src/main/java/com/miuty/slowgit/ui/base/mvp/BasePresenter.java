package com.miuty.slowgit.ui.base.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Asus on 1/9/2018.
 */

public class BasePresenter<V extends MvpView> implements LifecycleObserver {

    private static final String TAG = "BasePresenter";

    protected CompositeDisposable disposables;

    protected V view;

    public void disposeOnDestroy(Disposable disposable) {
        disposables.add(disposable);
    }

    /*@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)*/
    public void bindView(V view) {
        Log.d(TAG, "bindView: ");
        this.view = view;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
        unbindView();
    }

    public void unbindView() {
        Log.d(TAG, "unbindView: ");
        this.view = null;
    }
}
