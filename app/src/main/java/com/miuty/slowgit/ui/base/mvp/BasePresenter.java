package com.miuty.slowgit.ui.base.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.miuty.slowgit.provider.network.DefaultApiException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.miuty.slowgit.provider.network.DefaultApiException.*;

/**
 * Created by Asus on 1/9/2018.
 */

public abstract class BasePresenter<V extends MvpView> implements LifecycleObserver {

    private static final String TAG = "BasePresenter";

    protected CompositeDisposable disposables = new CompositeDisposable();

    protected V view;

    public void disposeOnDestroy(Disposable disposable) {
        disposables.add(disposable);
    }

    /*@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)*/
    public void bindView(V view) {
        Log.d(TAG, "BasePresenter bindView: ");
        this.view = view;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        Log.d(TAG, "BasePresenter onDestroy: ");
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
        unbindView();
    }

    /**
     * unbindView on Destroy
     */
    public void unbindView() {
        Log.d(TAG, "BasePresenter unbindView: ");
        this.view = null;
    }

    public void onError(@NonNull Throwable throwable) {
        DefaultApiException apiException = getError(throwable);
        if (apiException.getKind() == KIND.NETWORK) {
            if (apiException.getCode() == NETWORK_UNAVAILABLE_ERROR_CODE) { //rot cmn internet rui`
                view.noInternetConnection();
            }
        } else if (apiException.getKind() == KIND.UNEXPECTED) {
            view.someThingError(OOPS);
        }
    }
}
