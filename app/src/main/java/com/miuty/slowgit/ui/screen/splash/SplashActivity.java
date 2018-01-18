package com.miuty.slowgit.ui.screen.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.miuty.slowgit.ui.base.activity.BaseActivity;
import com.miuty.slowgit.ui.screen.login.LoginActivity;
import com.miuty.slowgit.ui.screen.main.MainActivity;
import com.miuty.slowgit.ui.screen.main.profile.ProfileActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isLoggedIn = false;
        if (!isLoggedIn) {
//            activityNavigator.startActivity(MainActivity.class);
            activityNavigator.startActivity(ProfileActivity.class);
            activityNavigator.finish();
        } else {
            activityNavigator.startActivity(LoginActivity.class);
            activityNavigator.finish();
        }
    }
}
