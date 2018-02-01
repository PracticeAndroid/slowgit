package com.miuty.slowgit.ui.screen.profile.repositories;


import android.util.Log;

import com.miuty.slowgit.data.model.Repo;
import com.miuty.slowgit.data.repository.profile.ProfileRepository;
import com.miuty.slowgit.data.repository.profile.ProfileRepositoryImpl;
import com.miuty.slowgit.provider.scheduler.SchedulerProvider;
import com.miuty.slowgit.provider.scheduler.SchedulerProviderImpl;
import com.miuty.slowgit.ui.base.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class ProfileRepositoriesPresenter extends BasePresenter<ProfileRepositoriesMvpView>{

    private static final String TAG = "ProfileReposPresenter";

    private ProfileRepository profileRepository;
    private SchedulerProvider schedulerProvider;

    @Inject
    public ProfileRepositoriesPresenter(ProfileRepositoryImpl profileRepository, SchedulerProviderImpl schedulerProvider){
        this.profileRepository = profileRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void getRepos(String loginId, int page)
    {
        if (view == null) {
            return;
        }
        //view.setVisibleMainView(true);
        Disposable disposable = profileRepository.getRepos(loginId, page)
                .compose(schedulerProvider.observableComputationScheduler())
                .doOnSubscribe(disposable1 -> {
                })
                .doOnTerminate(() -> {
                    if (view != null) {
                        //view.setVisibleMainView(false);
                        view.hideRefreshLayout();
                    }
                })
                .subscribe(this::processReposReponse, throwable -> {
                    Log.e(TAG, "getRepos: " + throwable.getMessage());
                });
        disposeOnDestroy(disposable);
    }

    public void processReposReponse(List<Repo> repos){
        view.showReposOnRecyclerView(repos);
    }

}
