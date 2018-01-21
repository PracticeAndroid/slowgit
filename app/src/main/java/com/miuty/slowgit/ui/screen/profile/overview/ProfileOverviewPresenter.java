package com.miuty.slowgit.ui.screen.profile.overview;


import com.miuty.slowgit.data.repository.profile.ProfileRepository;
import com.miuty.slowgit.data.repository.profile.ProfileRepositoryImpl;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;

import javax.inject.Inject;

public class ProfileOverviewPresenter extends BasePresenter<ProfileOverviewMvpView> {

    ProfileRepository profileRepository;
    SchedulerProvider schedulerProvider;

    @Inject
    public ProfileOverviewPresenter(ProfileRepositoryImpl profileRepository, SchedulerProviderImpl schedulerProvider) {
        this.profileRepository = profileRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void getBasicProfile(String loginId) {
        if (view == null) {
            return;
        }
        view.setVisibleMainView(true);
        profileRepository.getBasicProfile(loginId)
                .compose(schedulerProvider.observableComputationScheduler())
                .doOnSubscribe(disposable1 -> {
                    if (view != null) {
                        view.showProgress("loading...", true);
                    }
                })
                .doOnTerminate(() -> {
                    if (view != null) {
                        view.setVisibleMainView(false);
                        view.hideProgress();
                    }
                })
                .subscribe(basicProfile -> {
                    if (view != null) {
                        view.onGetBasicProfileSuccessfully(basicProfile);
                    }
                }, throwable -> {
                    if (view != null) {
                        view.onGetBasicProfileFailed(throwable);
                    }
                });
    }

    public void getContributions(String loginId) {
        if (view == null) {
            return;
        }
        profileRepository.getContributions(loginId)
                .compose(schedulerProvider.observableComputationScheduler())
                .doOnSubscribe(disposable1 -> {
                    if (view != null) {
                        //view.showProgress("loading...", true);
                    }
                })
                .doOnTerminate(() -> {
                    if (view != null) {
                        view.setVisibleMainView(false);
                        //view.hideProgress();
                    }
                })
                .subscribe(response -> {
                    if (view != null) {
                        view.onGetContributionsSuccessfully(response);
                    }
                }, throwable -> {
                    if (view != null) {
                        view.onGetContributionsFailed(throwable);
                    }
                });
    }
}
