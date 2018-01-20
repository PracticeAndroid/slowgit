package com.miuty.slowgit.ui.screen.profile.overview;


import com.miuty.slowgit.data.model.profile.BasicProfile;
import com.miuty.slowgit.ui.base.mvp.MvpView;

public interface ProfileOverviewMvpView extends MvpView {

    void onGetBasicProfileSusscessfully(BasicProfile basicProfile);

    void onGetBasicProfileFailed(Throwable throwable);
}
