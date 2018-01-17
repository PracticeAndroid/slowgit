package com.miuty.slowgit.provider.network;

import java.io.IOException;

import retrofit2.HttpException;

/**
 * Created by Asus on 1/17/2018.
 */

public class DefaultApiException extends Throwable {

    public static final int HTTP_ERROR_CODE_UNAUTHORIZED = 401;
    public static final int NETWORK_UNAVAILABLE_ERROR_CODE = -1;
    public static final int OOPS_ERROR_CODE = -2;


    public static final String OOPS = "Something error, please try again";
    public static final String NETWORK_UNAVAILABLE = "Network problem!";
    public static final String ERROR_UNAUTHORIZED = "Error! Please re-login!";

    private String message = "";
    private int code = 0;
    private KIND kind;

    // this field is to using when you want to get origin throwable of OOPS_ERROR
    private Throwable throwable;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public KIND getKind() {
        return kind;
    }

    public void setKind(KIND kind) {
        this.kind = kind;
    }

    public static DefaultApiException getError(Throwable throwable) {
        DefaultApiException e = new DefaultApiException();

        if (throwable instanceof DefaultApiException) {
            return (DefaultApiException) throwable;
        } else if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            e.setKind(KIND.HTTP);
            if (httpException.code() == HTTP_ERROR_CODE_UNAUTHORIZED) {
                e.setMessage(ERROR_UNAUTHORIZED);
                e.setCode(HTTP_ERROR_CODE_UNAUTHORIZED);
            } else {
                e.setMessage(httpException.getMessage());
                e.setCode(httpException.code());
            }
        } else if (throwable instanceof IOException) {
            e.setKind(KIND.NETWORK);
            e.setMessage(NETWORK_UNAVAILABLE);
            e.setCode(NETWORK_UNAVAILABLE_ERROR_CODE);
        } else {
            e.setKind(KIND.UNEXPECTED);
            e.setMessage(OOPS);
            e.setCode(OOPS_ERROR_CODE);
            e.setThrowable(throwable);
        }
        return e;
    }

    public enum KIND {
        NETWORK,
        HTTP,
        UNEXPECTED
    }
}
