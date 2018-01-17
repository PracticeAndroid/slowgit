package com.miuty.slowgit.provider.navigator;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by Asus on 1/16/2018.
 */

public class ActivityNavigatorImpl implements ActivityNavigator {

    protected final Context context;

    public ActivityNavigatorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void finish() {

    }

    @Override
    public void startActivity(@NonNull Intent intent) {
        context.startActivity(intent);
    }

    @Override
    public void startActivity(@NonNull Class<? extends Activity> clazz) {

    }

    @Override
    public void startActivity(@NonNull Class<? extends Activity> clazz, Bundle bundle) {

    }

    @Override
    public void startActivityForResult(@NonNull Class<? extends Activity> clazz, int requestCode) {

    }

    @Override
    public void replaceFragment(int idResource, @NonNull Fragment fragment) {

    }

    @Override
    public void replaceFragment(int idResource, @NonNull Fragment fragment, Bundle bundle) {

    }

    @Override
    public <T extends Fragment> T findFragmentByTag(@NonNull String tag) {
        return null;
    }

    @Override
    public <T extends Fragment> T findFragmentById(int idResource) {
        return null;
    }
}
