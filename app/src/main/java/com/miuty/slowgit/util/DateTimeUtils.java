package com.miuty.slowgit.util;

import android.text.format.DateUtils;

import java.util.Date;

/**
 * Created by Asus on 1/20/2018.
 */

public final class DateTimeUtils {

    public static CharSequence getTimeAgo(Date date) {
        if (date != null) {
            long now = System.currentTimeMillis();
            return DateUtils.getRelativeTimeSpanString(date.getTime(), now, DateUtils.SECOND_IN_MILLIS);
        }
        return "N/A";
    }
}
