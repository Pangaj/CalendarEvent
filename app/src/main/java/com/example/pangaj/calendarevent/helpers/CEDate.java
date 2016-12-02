package com.example.pangaj.calendarevent.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CEDate {
    private static final String TAG = "VCDate";
    public static String PICKER_FORMAT = "MMM dd, yyyy";
    public static String CONVERT_PICKER = "yyyy-MM-dd";

    /**
     * Get the date from string in required format
     *
     * @param dateString The date in string
     * @return The formatted string
     */
    public static Date getDateFromString(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PICKER_FORMAT, Locale.ENGLISH);
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            CELog.e(TAG, e.getMessage());
        }
        return null;
    }

    public static String getStringFromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PICKER_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public static Date convertToDate(String selectedDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CONVERT_PICKER, Locale.ENGLISH);
        try {
            return simpleDateFormat.parse(selectedDate);
        } catch (ParseException e) {
            CELog.e(TAG, e.getMessage());
        }
        return null;
    }
}