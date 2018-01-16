package com.miuty.slowgit.ui.base.mvp;

/**
 * Created by Asus on 1/9/2018.
 */

public interface MvpView {

    void noInternetConnection();

    void showProgress(String msg, boolean isCancelable);

    void hideProgress();
}
