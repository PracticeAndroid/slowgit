package com.miuty.slowgit.provider.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.miuty.slowgit.di.qualifier.DefaultOkHtppClientContext;
import com.miuty.slowgit.util.ApiConst;
import com.miuty.slowgit.util.HttpHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DefaultNetworkProvider implements NetworkProvider {

    private static final String TAG = "DefaultNetworkProviderContext";

    private Context context;
    private Gson gson;
    private OkHttpClient okHttpClient;

    private Map<String, String> headers;

    public DefaultNetworkProvider(Context context, OkHttpClient okHttpClient, Gson gson) {
        this.context = context;
        this.headers = new HashMap<>();
        this.okHttpClient = okHttpClient;
        this.gson = gson;
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public DefaultNetworkProvider setTimeOut(int time, TimeUnit timeUnit) {
        return this;
    }

    @Override
    public DefaultNetworkProvider addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public DefaultNetworkProvider addDefaultHeader() {
        this.headers.put("Content-Type", "application/json");
        return this;
    }

    @Override
    public <T> T provideApi(String url, Class<T> service) {

        //Set interceptor
        okHttpClient.newBuilder().addInterceptor(chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            if (headers == null || headers.size() == 0) {
                //addDefaultHeader();
            }
            for (Map.Entry<String, String> keyValueEntry : headers.entrySet()) {
                requestBuilder.addHeader(keyValueEntry.getKey(), keyValueEntry.getValue());
            }
            return chain.proceed(requestBuilder.build());
        });

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return restAdapter.create(service);
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
