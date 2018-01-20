package com.miuty.slowgit.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Asus on 1/17/2018.
 */

public final class InputUtils {

    public static boolean isEmpty(@Nullable String text) {
        return TextUtils.isEmpty(text);
    }

    public static boolean isEmpty(@Nullable TextView textView) {
        return textView == null || TextUtils.isEmpty(textView.getText());
    }

    public static boolean isEmpty(@Nullable EditText editText) {
        return editText == null || TextUtils.isEmpty(editText.getText());
    }

    public static void setEnabledEditTexts(boolean isEnabled, @NonNull View... views) {
        for (View v : views) {
            v.setEnabled(isEnabled);
        }
    }

    public static void goneViewIfEmpty(@NonNull View view, String text){
        if(view instanceof TextView) {
            if (TextUtils.isEmpty(text)) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
                ((TextView)view).setText(text);
            }
        } else{
            if (TextUtils.isEmpty(text)) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }
}
