package sbs20.filenotes;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.text.format.DateFormat;

public class DateTime {

    private DateTime() {
    }

    private static SimpleDateFormat dateFormat;
    private static SimpleDateFormat timeFormat;

    private static SimpleDateFormat dateFormat() {
        if (dateFormat == null) {
            Context context = ServiceManager.getInstance().getContext();
            SimpleDateFormat tmp = (SimpleDateFormat)DateFormat.getDateFormat(context);
            dateFormat = new SimpleDateFormat(tmp.toLocalizedPattern());
        }

        return dateFormat;
    }

    private static SimpleDateFormat timeFormat() {
        if (timeFormat == null) {
            Context context = ServiceManager.getInstance().getContext();
            SimpleDateFormat tmp = (SimpleDateFormat)DateFormat.getTimeFormat(context);
            timeFormat = new SimpleDateFormat(tmp.toLocalizedPattern());
        }

        return timeFormat;
    }

    public static String formatDate(Date date) {
        return dateFormat().format(date);
    }

    public static String formatTime(Date date) {
        return timeFormat().format(date);
    }

    public static Date min() {
        return new Date(0);
    }

    public static Date now() {
        return new Date();
    }

    public static String to8601String(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.UK);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

    public static Date from8601String(String s) {
        Date date = min();
        try {
            s = s.replace("Z", "+00:00");
            s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
            return date;
        } catch (Exception e) {
        }

        return date;
    }
}
