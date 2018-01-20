package com.miuty.slowgit.ui.screen.profile.overview;


import com.miuty.slowgit.data.repository.profile.ProfileRepository;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;

import javax.inject.Inject;

public class ProfileOverviewPresenter extends BasePresenter<ProfileOverviewMvpView> {

    ProfileRepository profileRepository;
    SchedulerProvider schedulerProvider;

    @Inject
    public ProfileOverviewPresenter(ProfileRepository profileRepository, SchedulerProvider schedulerProvider) {
        this.profileRepository = profileRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void getBasicProfile(String loginId){
        profileRepository.getBasicProfile(loginId)
                        .compose(schedulerProvider.observableComputationScheduler())
                        .doOnSubscribe(disposable1 -> {
                            if (view != null) {
                                view.showProgress("loading...", true);
                            }
                        })
                        .doOnTerminate(() -> {
                            if (view != null) {
                                view.hideProgress();
                            }
                        })
                        .subscribe(basicProfile -> {
                            if (view != null) {
                                view.onGetBasicProfileSusscessfully(basicProfile);
                            }
                        }, throwable -> {
                            if (view != null) {
                                view.onGetBasicProfileFailed(throwable);
                            }
                        });
    }
}
