package com.miuty.slowgit.ui.screen.main.feeds.adapter;


import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
import com.miuty.slowgit.util.DateTimeUtils;
import com.miuty.slowgit.util.SpannableBuilder;

import butterknife.BindView;

public abstract class BaseFeedsViewHolder<I extends BaseFeedsItem> extends BaseViewHolder<I> {

    @BindView(R.id.imv_avatar)
    ImageView imvAvatar;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    @BindView(R.id.iv_time)
    ImageView imvTime;

    @BindView(R.id.tv_time)
    TextView tvTime;

    public BaseFeedsViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void bindData(I item) {
        super.bindData(item);
        Glide.with(context)
                .asDrawable()
                .load(item.getActorAvatar())
                .into(imvAvatar);
        tvTitle.setText(buildTitle(item));

        CharSequence charSequence = buildDescription(item);
        if (TextUtils.isEmpty(charSequence)) {
            tvDescription.setVisibility(View.GONE);
        } else {
            tvDescription.setVisibility(View.VISIBLE);
            tvDescription.setText(charSequence);
        }

        tvTime.setText(DateTimeUtils.getTimeAgo(item.getFeed().getCreatedAt()));
        imvTime.setImageDrawable(context.getDrawable(setDrawableTimeIcon()));
    }

    @DrawableRes
    @NonNull
    public abstract int setDrawableTimeIcon();

    @NonNull
    public abstract SpannableBuilder buildTitle(I item);

    @NonNull
    public abstract SpannableBuilder buildDescription(I item);
}
