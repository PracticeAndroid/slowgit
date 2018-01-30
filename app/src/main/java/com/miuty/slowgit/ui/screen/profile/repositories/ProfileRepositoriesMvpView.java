package com.miuty.slowgit.ui.screen.profile.repositories;


import com.miuty.slowgit.data.model.Repo;
import com.miuty.slowgit.ui.base.mvp.MvpListView;

import java.util.List;

public interface ProfileRepositoriesMvpView extends MvpListView {

    void showReposOnRecyclerView(List<Repo> items);
    void setVisibleMainView(boolean isLoading);
}
