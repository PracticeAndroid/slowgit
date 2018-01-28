package com.miuty.slowgit.ui.screen.main.issues.page.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.IssueItem;
import com.miuty.slowgit.data.model.Issues;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.util.InputUtils;
import com.miuty.slowgit.util.SpannableBuilder;

import butterknife.BindView;

/**
 * Created by Asus on 1/28/2018.
 */

public class IssuesViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_cmt_number)
    TextView tvCmtNumber;

    public IssuesViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    public void bindData(IssueItem issueItem) {
        tvTitle.setText(issueItem.getTitle());
        tvDescription.setText(buildDescription(issueItem));
        InputUtils.goneViewIfEmpty(tvCmtNumber, String.valueOf(issueItem.getComments()));
    }

    public SpannableBuilder buildDescription(IssueItem issueItem) {
        SpannableBuilder spannableBuilder = SpannableBuilder.builder();

        spannableBuilder
                .bold("hung/test")
                .append(" ")
                .append(issueItem.getCreatedAt().toString());

        return spannableBuilder;
    }
}
