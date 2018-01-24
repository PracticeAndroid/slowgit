package com.miuty.slowgit.provider.navigator;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class FragmentNavigatorImpl implements FragmentNavigator {

    private final Context context;

    public FragmentNavigatorImpl(Context context) {
        this.context = context;
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
}
