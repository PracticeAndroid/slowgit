package com.miuty.slowgit.ui.screen.login;

import android.support.annotation.NonNull;

import com.miuty.slowgit.data.repository.login.AuthRepository;
import com.miuty.slowgit.data.repository.login.AuthRepositoryImpl;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;
import com.miuty.slowgit.ui.base.mvp.MvpView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
                .flatMap(authResponse -> loginRepository.saveToken(authResponse))
                .subscribe(response -> {
                    view.onBasicLogin();
                }, throwable -> {
                    view.onBasicLoginFailed();
                });

        disposeOnDestroy(disposable);
    }
}
