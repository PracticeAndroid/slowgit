package com.miuty.slowgit.ui.screen.profile.following;


import com.miuty.slowgit.data.model.User;
import com.miuty.slowgit.ui.base.mvp.MvpListView;

import java.util.List;

public interface ProfileFollowingMvpView extends MvpListView {

    void showFollowingOnRecyclerView(List<User> items);

    void setVisibleMainView(boolean isLoading);

}
