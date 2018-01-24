package com.miuty.slowgit.ui.screen.login;

import android.support.annotation.NonNull;

import com.miuty.slowgit.data.repository.auth.AuthRepository;
import com.miuty.slowgit.data.repository.auth.AuthRepositoryImpl;
import com.miuty.slowgit.provider.network.DefaultApiException;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;
import com.miuty.slowgit.util.InputUtils;

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

        boolean isUserNameEmpty = InputUtils.isEmpty(userName);
        boolean isPasswordEmpty = InputUtils.isEmpty(userName);
        view.onUsernameEmpty(isUserNameEmpty);
        view.onPasswordEmpty(isPasswordEmpty);

        if (view == null || isUserNameEmpty || isPasswordEmpty) {
            return;
        }

        Disposable disposable = loginRepository.doBasicLogin(userName, password)
                .compose(schedulerProvider.observableComputationScheduler())
                .doOnSubscribe(disposable1 -> {
                    if (view != null) {
                        view.onSignInStatus(true);
                    }
                })
                .doOnTerminate(() -> {
                    if (view != null) {
                        view.onSignInStatus(false);
                    }
                })
                .flatMap(authResponse -> loginRepository.saveToken(authResponse))
                .subscribe(response -> {
                    view.onBasicLoginSuccessfully();
                }, this::onErrorLogin);

        disposeOnDestroy(disposable);
    }

    public void onErrorLogin(@NonNull Throwable throwable) {
        super.onError(throwable);
        throwable.printStackTrace();

        DefaultApiException apiException = DefaultApiException.getError(throwable);
        if (apiException.getCode() == DefaultApiException.HTTP_ERROR_CODE_UNAUTHORIZED) {
            view.onBasicLoginFailed("");
        } /*else {
            view.someThingError(apiException.getMessage());
        }*/
    }
}
