package com.miuty.slowgit.ui.screen.profile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.User;
import com.miuty.slowgit.ui.base.adapter.BaseViewHolder;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by igneel on 2/2/2018.
 */

public class UserViewHolder extends BaseViewHolder {

    @BindView(R.id.imv_avatar)
    CircleImageView imvAvatar;
    @BindView(R.id.tv_login_name)
    TextView tvLoginName;

    public UserViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    public void bind(@NonNull User user) {
        Glide.with(context)
                .asDrawable()
                .load(user.getAvatar())
                .into(imvAvatar);
        tvLoginName.setText(user.getUsername());
    }

}
