package com.miuty.slowgit.ui.screen.profile.followers;


import android.util.Log;

import com.miuty.slowgit.data.model.User;
import com.miuty.slowgit.data.repository.profile.ProfileRepository;
import com.miuty.slowgit.data.repository.profile.ProfileRepositoryImpl;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class ProfileFollowersPresenter extends BasePresenter<ProfileFollowersMvpView> {

    private static final String TAG = "ProfileFollowers";

    private ProfileRepository profileRepository;
    private SchedulerProvider schedulerProvider;

    @Inject
    public ProfileFollowersPresenter(ProfileRepositoryImpl profileRepository, SchedulerProviderImpl schedulerProvider){
        this.profileRepository = profileRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void getFollowers(String loginId, int page){
        if (view == null) {
            return;
        }
        view.setVisibleMainView(true);
        Disposable disposable = profileRepository.getFollowers(loginId, page)
                .compose(schedulerProvider.observableComputationScheduler())
                .doOnSubscribe(disposable1 -> {
                })
                .doOnTerminate(() -> {
                    if (view != null) {
                        //view.setVisibleMainView(false);
                        view.hideRefreshLayout();
                    }
                })
                .subscribe(this::processFollowersReponse, throwable -> {
                    Log.e(TAG, "getFollowers: " + throwable.getMessage());
                });
        disposeOnDestroy(disposable);
    }

    public void processFollowersReponse(List<User> user){
        view.showFollowersOnRecyclerView(user);
    }

}
