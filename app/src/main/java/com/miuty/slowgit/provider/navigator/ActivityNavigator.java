package com.miuty.slowgit.provider.navigator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by Asus on 1/16/2018.
 */

public interface ActivityNavigator {

    void finish();

    void startActivity(@NonNull Intent intent);

    void startActivity(@NonNull Class<? extends Activity> clazz);

    void startActivity(@NonNull Class<? extends Activity> clazz, Bundle bundle);

    void startActivityForResult(@NonNull Class<? extends Activity> clazz, int requestCode);

    void replaceFragment(@IdRes int idResource, @NonNull Fragment fragment);

    void replaceFragment(@IdRes int idResource, @NonNull Fragment fragment, Bundle bundle);

    <T extends Fragment> T findFragmentByTag(@NonNull String tag);

    <T extends Fragment> T findFragmentById(@IdRes int idResource);
}
