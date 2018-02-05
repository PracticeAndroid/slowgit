package com.miuty.slowgit.ui.screen.profile.following;


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

public class ProfileFollowingPresenter extends BasePresenter<ProfileFollowingMvpView> {

    private static final String TAG = "ProfileFollowing";

    private ProfileRepository profileRepository;
    private SchedulerProvider schedulerProvider;

    @Inject
    public ProfileFollowingPresenter(ProfileRepositoryImpl profileRepository, SchedulerProviderImpl schedulerProvider) {
        this.profileRepository = profileRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void getFollowing(String loginId, int page) {
        if (view == null) {
            return;
        }
        //view.setVisibleMainView(true);
        Disposable disposable = profileRepository.getFollowing(loginId, page)
                .compose(schedulerProvider.observableComputationScheduler())
                .doOnSubscribe(disposable1 -> {
                })
                .doOnTerminate(() -> {
                    if (view != null) {
                        //view.setVisibleMainView(false);
                        view.hideRefreshLayout();
                    }
                })
                .subscribe(this::processFollowingReponse, throwable -> {
                    Log.e(TAG, "getFollowing: " + throwable.getMessage());
                });
        disposeOnDestroy(disposable);
    }

    public void processFollowingReponse(List<User> user) {
        view.showFollowingOnRecyclerView(user);
    }

}
