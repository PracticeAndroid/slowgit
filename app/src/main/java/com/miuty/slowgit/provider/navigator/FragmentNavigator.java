package com.miuty.slowgit.provider.navigator;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

public interface FragmentNavigator {

    void startActivity(@NonNull Intent intent);

    void startActivity(@NonNull Class<? extends Activity> clazz);
}
