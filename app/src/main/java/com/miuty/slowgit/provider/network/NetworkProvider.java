package com.miuty.slowgit.provider.network;


import io.reactivex.Observable;

/**
 * Created by Asus on 1/10/2018.
 */

public interface NetworkProvider {

    boolean isNetworkAvailable();

    <Response> Observable<Response> makeRequest(Observable<Response> observable);
}
