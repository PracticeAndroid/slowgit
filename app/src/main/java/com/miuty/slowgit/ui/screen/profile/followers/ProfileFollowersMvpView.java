package com.miuty.slowgit.ui.screen.profile.followers;

import com.miuty.slowgit.data.model.User;
import com.miuty.slowgit.ui.base.mvp.MvpListView;

import java.util.List;

public interface ProfileFollowersMvpView extends MvpListView {

    void showFollowersOnRecyclerView(List<User> items);
    void setVisibleMainView(boolean isLoading);

}
