package com.miuty.slowgit.ui.screen.login;

import android.support.annotation.NonNull;

import com.miuty.slowgit.data.repository.AuthRepository;
import com.miuty.slowgit.data.repository.AuthRepositoryImpl;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Asus on 1/9/2018.
 */

public class LoginPresenter extends BasePresenter<LoginMvpView> {

    private AuthRepository loginRepository;
    private SchedulerProvider schedulerProvider;

    @Inject
    public LoginPresenter(AuthRepositoryImpl loginRepository, SchedulerProviderImpl schedulerProvider) {
        this.loginRepository = loginRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void doBasicLogin(@NonNull String userName, @NonNull String password) {
        Disposable disposable = loginRepository.doBasicLogin(userName, password)
                .compose(schedulerProvider.observableComputationScheduler())
                .doOnSubscribe(disposable1 -> {
                    if (view != null) {
                        view.showProgress("Login...", true);
                    }
                })
                .doOnTerminate(() -> {
                    if (view != null) {
                        view.hideProgress();
                    }
                })
                .flatMap(authResponse -> loginRepository.saveToken(authResponse))
                .subscribe(response -> {
                    view.onBasicLogin();
                }, throwable -> {
                    view.onBasicLoginFailed();
                });

        disposeOnDestroy(disposable);
    }
}
