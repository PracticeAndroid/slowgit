package com.miuty.slowgit.ui.screen.profile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.Repo;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.util.DateTimeUtils;
import com.miuty.slowgit.util.InputHelper;

import java.text.NumberFormat;

import butterknife.BindColor;
import butterknife.BindView;
import lt.neworld.spanner.Spanner;

import static lt.neworld.spanner.Spans.background;
import static lt.neworld.spanner.Spans.foreground;

/**
 * Created by igneel on 1/27/2018.
 */

public class RepositoryViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_stars)
    TextView tvStars;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_forks)
    TextView tvForks;
    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.tv_language)
    TextView tvLanguage;

    @BindColor(R.color.material_indigo_700)
    int forkColor;

    public RepositoryViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    public void bind(@NonNull Repo repo) {
        if (repo.isFork()) {
            tvTitle.setText(new Spanner()
                    .append(" Forked ", background(forkColor), foreground(Color.WHITE))
                    .append(" ")
                    .append(repo.getName()));
        } else {
            tvTitle.setText(repo.getName());
        }

        long repoSize = repo.getSize() > 0 ? (repo.getSize() * 1000) : repo.getSize();
        tvSize.setText(Formatter.formatFileSize(tvSize.getContext(), repoSize));
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        tvStars.setText(numberFormat.format(repo.getStargazersCount()));
        tvForks.setText(numberFormat.format(repo.getForks()));


        tvTime.setText(DateTimeUtils.getTimeAgo(repo.getUpdatedAt()));

        if (!InputHelper.isEmpty(repo.getLanguage())) {
            tvLanguage.setText(repo.getLanguage());
//            tvLanguage.setTextColor(ColorsProvider.getColorAsColor(repo.getLanguage(), tvLanguage.getContext()));
//            tvLanguage.setVisibility(View.VISIBLE);
        } //else {
//            tvLanguage.setTextColor(Color.BLACK);
//            tvLanguage.setVisibility(View.GONE);
//            tvLanguage.setText("");
//        }
    }

}
