package com.miuty.slowgit.ui.screen.profile.overview;


import com.miuty.slowgit.data.model.profile.BasicProfile;
import com.miuty.slowgit.ui.base.mvp.MvpView;

import okhttp3.ResponseBody;

public interface ProfileOverviewMvpView extends MvpView {

    void onGetBasicProfileSuccessfully(BasicProfile basicProfile);

    void onGetBasicProfileFailed(Throwable throwable);

    void setVisibleMainView(boolean isLoading);

    void onGetContributionsSuccessfully(ResponseBody responseBody);

    void onGetContributionsFailed(Throwable throwable);
}
