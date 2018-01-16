package com.miuty.slowgit.util;

import retrofit2.HttpException;

/**
 * Created by Asus on 1/12/2018.
 */

public final class HttpUtils {

    public static int getStatusCode(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            return httpException.code();
        } else {
            return -1;
        }
    }
}
