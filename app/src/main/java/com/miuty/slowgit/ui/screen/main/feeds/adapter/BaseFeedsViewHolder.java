package com.miuty.slowgit.ui.screen.main.feeds.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;
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
        Glide.with(context).asDrawable().load(item.getActorAvatar()).into(imvAvatar);
        tvTitle.setText(buildTitle(item));
        tvDescription.setText(buildDescription(item));
    }

    public abstract SpannableBuilder buildTitle(I item);

    public abstract SpannableBuilder buildDescription(I item);
}
