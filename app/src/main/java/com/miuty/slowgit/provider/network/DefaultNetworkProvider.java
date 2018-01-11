package com.miuty.slowgit.provider.network;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Asus on 1/10/2018.
 */

public class DefaultNetworkProvider implements NetworkProvider {

    private static final String TAG = "DefaultNetworkProvider";

    private Context context;

    @Inject
    public DefaultNetworkProvider(Context context) {
        this.context = context;
    }

    @Override
    public boolean isNetworkAvailable() {
        return false;
    }

    @Override
    public <Response> Observable<Response> makeRequest(Observable<Response> observable) {
        Log.d(TAG, "makeRequest() called with: observable = [" + observable + "]");
        Observable<Response> responseObservable = observable
                .observeOn(Schedulers.computation())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Response>>() {
                    @Override
                    public ObservableSource<? extends Response> apply(Throwable throwable) throws Exception {
                        if (!isNetworkAvailable()) {
                            return Observable.error(new Exception("No internet"));
                        }
                        return Observable.error(throwable);
                    }
                });
        return responseObservable;
    }
}
