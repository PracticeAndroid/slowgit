package com.miuty.slowgit.ui.screen.profile.overview;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miuty.slowgit.R;
import com.miuty.slowgit.data.model.profile.BasicProfile;
import com.miuty.slowgit.ui.base.mvp.BaseMvpFragment;
import com.miuty.slowgit.util.BundleKeyConst;
import com.miuty.slowgit.util.InputUtils;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileOverviewFragment extends BaseMvpFragment<ProfileOverviewMvpView, ProfileOverviewPresenter>
        implements ProfileOverviewMvpView {

    @BindView(R.id.imv_avatar)
    CircleImageView imvAvatar;
    @BindView(R.id.tv_fullname)
    TextView tvFullName;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.btn_following)
    Button btnFollowing;
    @BindView(R.id.btn_followers)
    Button btnFollowers;
    @BindView(R.id.btn_follow)
    Button btnFollow;
    @BindView(R.id.tv_organization)
    TextView tvOrganization;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_link)
    TextView tvLink;
    @BindView(R.id.tv_joined)
    TextView tvJoined;
    @BindView(R.id.rl_organization)
    RelativeLayout rlOrganization;
    @BindView(R.id.rl_location)
    RelativeLayout rlLocation;
    @BindView(R.id.rl_email)
    RelativeLayout rlEmail;
    @BindView(R.id.rl_link)
    RelativeLayout rlLink;
    @BindView(R.id.rl_joined)
    RelativeLayout rlJoined;
    @BindView(R.id.fl_main)
    FrameLayout flMain;

    private String loginId;

    public static ProfileOverviewFragment newInstance(String loginId) {
        Bundle args = new Bundle();
        args.putString(BundleKeyConst.EXTRA_1, loginId);
        ProfileOverviewFragment fragment = new ProfileOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.loginId = getArguments().getString(BundleKeyConst.EXTRA_1);
        presenter.getBasicProfile(loginId);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_profile_overview;
    }

    @Override
    public String getTitle() {
        return "Overview";
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
    public void onGetBasicProfileSuccessfully(BasicProfile basicProfile) {
        Glide.with(this)
                .asDrawable()
                .load(basicProfile.getAvatarUrl())
                .into(imvAvatar);
        tvFullName.setText(basicProfile.getName());
        tvUsername.setText(basicProfile.getLoginId());

        btnFollowers.setText(String.format("Followers (%d)", basicProfile.getFollowers()));
        btnFollowing.setText(String.format("Following (%d)", basicProfile.getFollowing()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy");
        InputUtils.goneViewIfEmpty(rlOrganization, basicProfile.getCompany());
        InputUtils.goneViewIfEmpty(rlLocation, basicProfile.getLocation());
        InputUtils.goneViewIfEmpty(rlEmail, basicProfile.getEmail());
        InputUtils.goneViewIfEmpty(rlJoined, simpleDateFormat.format(basicProfile.getCreateAt()));
        InputUtils.goneViewIfEmpty(rlLink, basicProfile.getHtmlUrl());

        InputUtils.goneViewIfEmpty(tvOrganization, basicProfile.getCompany());
        InputUtils.goneViewIfEmpty(tvLocation, basicProfile.getLocation());
        InputUtils.goneViewIfEmpty(tvEmail, basicProfile.getEmail());
        InputUtils.goneViewIfEmpty(tvJoined, simpleDateFormat.format(basicProfile.getCreateAt()));
        InputUtils.goneViewIfEmpty(tvLink, basicProfile.getHtmlUrl());
    }

    @Override
    public void onGetBasicProfileFailed(Throwable throwable) {

    }

    @Override
    public void setVisibleMainView(boolean isLoading) {
        if(isLoading){
            flMain.setVisibility(View.GONE);
        } else {
            flMain.setVisibility(View.VISIBLE);
        }
    }
}
