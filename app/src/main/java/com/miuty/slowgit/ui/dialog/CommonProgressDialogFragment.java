package com.miuty.slowgit.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.miuty.slowgit.R;
import com.miuty.slowgit.ui.base.dialog.BaseDialogFragment;
import com.miuty.slowgit.ui.base.mvp.BaseMvpDialogFragment;
import com.miuty.slowgit.util.BundleKeyConst;

/**
 * Created by Asus on 1/16/2018.
 */

public class CommonProgressDialogFragment extends BaseMvpDialogFragment {

    public static final String TAG = "CommonProgressDialogFra";

    private boolean isCancelable = false;
    private String msg = "";

    @NonNull
    public static CommonProgressDialogFragment newInstance(String msg, boolean isCancelable) {
        CommonProgressDialogFragment commonProgressDialogFragment = new CommonProgressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeyConst.EXTRA_1, msg);
        bundle.putBoolean(BundleKeyConst.EXTRA_2, isCancelable);
        commonProgressDialogFragment.setArguments(bundle);
        return commonProgressDialogFragment;
    }

    @Override
    protected int fragmentLayout() {
        return R.layout.fragment_dialog_common_progress;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            msg = savedInstanceState.getString(BundleKeyConst.EXTRA_1);
            isCancelable = savedInstanceState.getBoolean(BundleKeyConst.EXTRA_2);
        }
    }
}
