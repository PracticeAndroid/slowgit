package com.miuty.slowgit.provider.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.miuty.slowgit.util.HttpHelper;

import java.net.HttpRetryException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Asus on 1/10/2018.
 */

public class DefaultNetworkProvider implements NetworkProvider {

    private static final String TAG = "DefaultNetworkProvider";

    private Context context;

    @Inject
    private Retrofit retrofit;

    @Inject
    private Gson gson;

    @Inject
    private OkHttpClient okHttpClient;

    @Inject
    public DefaultNetworkProvider(Context context) {
        this.context = context;
    }

    @Override
    public boolean isNetworkAvailable() {
        return true;
    }

    @Override
    public <Response> Observable<Response> makeRequest(Observable<Response> observable) {
        Observable<Response> responseObservable = observable
                .observeOn(Schedulers.computation())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Response>>() {
                    @Override
                    public ObservableSource<? extends Response> apply(Throwable throwable) throws Exception {
                        if (!isNetworkAvailable()) {
                            return Observable.error(new Exception("No internet"));
                        } else if (HttpHelper.getStatusCode(throwable) == 401) {
                            return Observable.error(new Exception("session expired"));
                        }
                        return Observable.error(throwable);
                    }
                });
        return responseObservable;
    }
}
