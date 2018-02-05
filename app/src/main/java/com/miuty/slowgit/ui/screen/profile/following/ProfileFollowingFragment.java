package com.miuty.slowgit.ui.screen.profile.following;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.User;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.ui.base.mvp.BaseMvpListFragment;
import com.miuty.slowgit.ui.screen.profile.adapter.UserAdapter;
import com.miuty.slowgit.util.BundleKeyConst;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

public class ProfileFollowingFragment extends BaseMvpListFragment<ProfileFollowingMvpView, ProfileFollowingPresenter, UserAdapter>
        implements ProfileFollowingMvpView {

    private static final String TAG = "ProfileFollowingFrag";

    private int page = 1;

    private String loginId;

    public static ProfileFollowingFragment newInstance(String loginId) {
        Bundle args = new Bundle();
        args.putString(BundleKeyConst.EXTRA_2, loginId);
        ProfileFollowingFragment fragment = new ProfileFollowingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.loginId = getArguments().getString(BundleKeyConst.EXTRA_2);

        // setup animation for appearing, additional and removal
        mRecyclerView.setItemAnimator(new ScaleInAnimator());
        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mAdapter));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mAdapter.initLoadMore(() -> {
            Log.d(TAG, "load more" + page);
            presenter.getFollowing(loginId, page);
        }, mRecyclerView);
    }

    @Override
    public void noInternetConnection() {

    }

    @Override
    public void someThingError(String msg) {

    }

    @Override
    public void showProgress(String msg, boolean isCancelable) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showFollowingOnRecyclerView(List<User> items) {
        if (items == null || items.size() == 0) { // end of load more
            mAdapter.setLoadMore(false);
            mAdapter.setLoading();
            mAdapter.notifyItemChanged(mAdapter.getItems().size());
        } else {
            mAdapter.setLoaded();
            page++;
            mAdapter.addItems(items);
        }
    }

    @Override
    public void setVisibleMainView(boolean isLoading) {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_users;
    }

    @Override
    protected UserAdapter createAdapter() {
        return new UserAdapter(getContext(), new BaseViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                /*mAdapter.remove(position);*/

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }

    @Override
    public String getTitle() {
        return "Following";
    }

    @Override
    protected void doRefresh() {
        mAdapter.setLoadMore(true);
        page = 1;
        mAdapter.clear();
        presenter.getFollowing(loginId, page);
    }
}
