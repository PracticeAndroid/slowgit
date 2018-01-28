package com.miuty.slowgit.ui.screen.main.issues.page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.Issues;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.ui.base.mvp.BaseMvpListFragment;
import com.miuty.slowgit.ui.screen.main.issues.page.adapter.IssuesItemAdapter;

/**
 * Created by Asus on 1/28/2018.
 */

public class IssuesItemFragment extends BaseMvpListFragment<IssueItemMvpView, IssueItemPresenter, IssuesItemAdapter>
        implements IssueItemMvpView {

    private IssuesType issuesType;

    public static IssuesItemFragment newInstance() {
        Bundle args = new Bundle();
        IssuesItemFragment fragment = new IssuesItemFragment();
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
        presenter.getIssues(1, IssuesType.CREATED);
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

    }

    private String getTittle() {
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

    }

    @Override
    public void onGetIssuesFailed(Throwable throwable) {

    }
}
