package com.miuty.slowgit.util;

import android.annotation.SuppressLint;
import android.text.format.DateUtils;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    public static String convertDateToString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        return dateFormat.format(date);
    }
}
