package com.miuty.slowgit.ui.screen.main.issues.page.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.IssueItem;
import com.miuty.slowgit.data.model.Issues;
import com.miuty.slowgit.data.model.Label;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.util.DateTimeUtils;
import com.miuty.slowgit.util.InputUtils;
import com.miuty.slowgit.util.SpannableBuilder;

import org.apmem.tools.layouts.FlowLayout;

import java.util.List;

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
    @BindView(R.id.fl_tags)
    FlowLayout flTags;

    public IssuesViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    public void bindData(IssueItem issueItem) {
        tvTitle.setText(issueItem.getTitle());
        tvDescription.setText(buildDescription(issueItem));
        InputUtils.goneViewIfEmpty(tvCmtNumber, String.valueOf(issueItem.getComments()));
        //initTagsFlow(issueItem);
    }

    public SpannableBuilder buildDescription(IssueItem issueItem) {
        SpannableBuilder spannableBuilder = SpannableBuilder.builder();

        spannableBuilder
                .bold(parseRepo(issueItem.getHtmlUrl()))
                .append(" ")
                .append(DateTimeUtils.convertDateToString(issueItem.getCreatedAt(), "dd-MM-yyyy HH:mm:ss"));

        return spannableBuilder;
    }

    public void initTagsFlow(IssueItem issueItem) {
        flTags.removeAllViews();
        List<Label> labels = issueItem.getLabels();
        if (labels != null && labels.size() > 0) {
            LayoutInflater inflater = LayoutInflater.from(context);
            ViewGroup view = (ViewGroup) inflater.inflate(R.layout.item_tag, null, false);
            TextView tvTag = view.findViewById(R.id.tv_tag);
            for (Label label : labels) {
                tvTag.setText(label.getName());
                tvTag.setBackgroundColor(Color.parseColor("#" + label.getColor()));
                flTags.addView(view);
            }
        }
    }

    public String parseRepo(String url) {
        Uri uri = Uri.parse(url);
        List<String> segments = uri.getPathSegments();
        if (segments == null || segments.size() < 3) {
            return "";
        } else {
            String name = segments.get(0);
            String repoName = segments.get(1);
            String number = segments.get(3);
            return name + "/" + repoName + "#" + number;
        }
    }
}
