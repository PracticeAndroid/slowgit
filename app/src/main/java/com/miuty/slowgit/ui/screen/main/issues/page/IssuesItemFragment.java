package com.miuty.slowgit.ui.screen.main.issues.page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.Issues;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.ui.base.adapter.decoration.VerticalSpacingDecoration;
import com.miuty.slowgit.ui.base.mvp.BaseMvpListFragment;
import com.miuty.slowgit.ui.screen.main.issues.page.adapter.IssuesItemAdapter;
import com.miuty.slowgit.util.BundleKeyConst;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

/**
 * Created by Asus on 1/28/2018.
 */

public class IssuesItemFragment extends BaseMvpListFragment<IssueItemMvpView, IssueItemPresenter, IssuesItemAdapter>
        implements IssueItemMvpView {

    private static final String TAG = "IssuesItemFragment";

    private int page = 1;
    private String status = "open";

    public static IssuesItemFragment newInstance(IssuesType issuesType, String loginId) {
        IssuesItemFragment fragment = new IssuesItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleKeyConst.EXTRA_1, issuesType);
        args.putString(BundleKeyConst.EXTRA_2, loginId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_issues_item;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set data from bundle
        presenter.setIssuesType((IssuesType) getArguments().getSerializable(BundleKeyConst.EXTRA_1));
        presenter.setLoginId(getArguments().getString(BundleKeyConst.EXTRA_2));

        // setup animation for appearing, additional and removal
        mRecyclerView.setItemAnimator(new ScaleInAnimator());
        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mAdapter));
        mRecyclerView.addItemDecoration(new VerticalSpacingDecoration(10, true));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mAdapter.initLoadMore(() -> {
            presenter.getIssues(page, status);
        }, mRecyclerView);
    }

    @Override
    protected IssuesItemAdapter createAdapter() {
        return new IssuesItemAdapter(getContext(), new BaseViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }

    @Override
    protected void doRefresh() {
        mAdapter.setLoadMore(true);
        page = 1;
        mAdapter.clear();
        presenter.getIssues(page, status);
    }

    private String getTittle() {
        switch (presenter.getIssuesType()) {
            case CREATED:
                return "CREATED";
            case ASSIGNED:
                return "ASSIGNED";
            case MENTIONED:
                return "MENTIONED";
        }
        return "";
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
    public void onGetIssuesSuccessfully(Issues issues) {
        if (issues == null || issues.getItems() == null ||
                issues.getItems().size() == 0) { // end of load more
            mAdapter.setLoadMore(false);
            mAdapter.notifyItemChanged(mAdapter.getItems().size());
        } else {
            mAdapter.setLoaded();
            mAdapter.addItems(issues.getItems());
            page++;
        }
    }

    @Override
    public void onGetIssuesFailed(Throwable throwable) {

    }
}
