package com.miuty.slowgit.provider.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

/**
 * Created by Asus on 1/16/2018.
 */

public class ActivityNavigatorImpl implements ActivityNavigator {

    protected final Context context;
    protected final AppCompatActivity activity;

    public ActivityNavigatorImpl(Context context, AppCompatActivity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void finish() {
        ((AppCompatActivity) context).finish();
    }

    @Override
    public void startActivity(@NonNull Intent intent) {
        context.startActivity(intent);
    }

    @Override
    public void startActivity(@NonNull Class<? extends Activity> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    @Override
    public void startActivity(@NonNull Class<? extends Activity> clazz, Bundle bundle) {

    }

    @Override
    public void startActivityForResult(@NonNull Class<? extends Activity> clazz, int requestCode) {

    }

    @Override
    public void replaceFragment(@IdRes int idResource, @NonNull Fragment fragment) {
        replaceFragment(activity.getSupportFragmentManager(), idResource, fragment,
                null, null, false, null);
    }

    @Override
    public void replaceFragment(@IdRes int idResource, @NonNull Fragment fragment, Bundle bundle) {
        replaceFragment(activity.getSupportFragmentManager(), idResource, fragment,
                null, bundle, false, null);
    }

    public void replaceFragment(@NonNull FragmentManager fragmentManager, @IdRes int idResource,
                                @NonNull Fragment fragment, @Nullable String tag,
                                @Nullable Bundle args, boolean hasAddToBackStack, @Nullable String backStackTag,
                                @Nullable View... transitionView) {
        if (args != null) {
            fragment.setArguments(args);
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(idResource, fragment, tag);
        if (hasAddToBackStack) {
            ft.addToBackStack(tag).commit();
            fragmentManager.executePendingTransactions();
        } else {
            ft.commitNow();
        }
    }

    @Override
    public <T extends Fragment> T findFragmentByTag(@NonNull String tag) {
        return null;
    }

    @Override
    public <T extends Fragment> T findFragmentById(int idResource) {
        return null;
    }

    @Override
    public Fragment getCurrentFragment(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && !fragments.isEmpty()) {
            for (Fragment f : fragments) {
                if (f != null && f.isVisible()) {
                    return f;
                }
            }
        }
        return null;
    }
}
