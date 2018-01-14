package com.miuty.slowgit.provider.network;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by Asus on 1/10/2018.
 */

public interface NetworkProvider {

    boolean isNetworkAvailable();

    NetworkProvider setTimeOut(int time, TimeUnit timeUnit);

    NetworkProvider addHeader(String key, String value);

    NetworkProvider addDefaultHeader();

    <T> T provideApi(String url, Class<T> service);

    <Response> Observable<Response> makeRequest(Observable<Response> observable);
}
