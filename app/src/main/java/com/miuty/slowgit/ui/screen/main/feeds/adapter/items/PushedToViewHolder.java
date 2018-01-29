package com.miuty.slowgit.ui.screen.main.feeds.adapter.items;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.Commit;
import com.miuty.slowgit.data.model.Feed;
import com.miuty.slowgit.ui.screen.main.feeds.adapter.BaseFeedsViewHolder;
import com.miuty.slowgit.util.SpannableBuilder;

import java.util.List;

public class PushedToViewHolder extends BaseFeedsViewHolder<PushedToItem> {

    public PushedToViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public int setDrawableTimeIcon() {
        return R.drawable.ic_commit;
    }

    @NonNull
    @Override
    public SpannableBuilder buildTitle(PushedToItem item) {
        SpannableBuilder spannableBuilder = SpannableBuilder.builder();

        spannableBuilder
                .append(item.getActorName())
                .bold(" pushed to ")
                .append(item.getBranchPushedTo())
                .bold(" at ")
                .append(item.getRepoNamePushTo());

        return spannableBuilder;
    }

    @NonNull
    @Override
    public SpannableBuilder buildDescription(PushedToItem item) {
        SpannableBuilder spannableBuilder = SpannableBuilder.builder();

        Feed feed = item.getFeed();
        if (feed.getPayload() != null && feed.getPayload().getCommits() != null) {
            List<Commit> commits = feed.getPayload().getCommits();
            spannableBuilder
                    .append(String.valueOf(commits.size()))
                    .append(" new commits")
                    .beginANewLine();
            for (int i = 0; i < commits.size(); i++) {
                String shortSHA = commits.get(i).getSha();
                if (!TextUtils.isEmpty(shortSHA)) {
                    shortSHA = shortSHA.substring(0, 7);
                }
                String message = commits.get(i).getMessage();
                spannableBuilder
                        .foreground(shortSHA, Color.RED)
                        .append(" ")
                        .append(message);

                // drop a line if this is not end of list, otherwise does not
                if (i < commits.size() - 1) {
                    spannableBuilder.beginANewLine();
                }
            }
        }
        return spannableBuilder;
    }

    @Override
    public void onFeedClick(View view, PushedToItem item) {

    }
}
