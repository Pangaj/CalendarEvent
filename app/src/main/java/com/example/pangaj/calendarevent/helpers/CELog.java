package com.example.pangaj.calendarevent.helpers;

import android.util.Log;

/**
 * Created by pangaj on 20/10/16.
 */
public class CELog {
    /**
     * Verbose level logcat message (only output for debug builds)
     *
     * @param tag
     * @param message
     */
    public static void v(String tag, String message) {
        if (CEBuildConfig.DEBUG) {
            Log.v(tag, message);
        }
    }

    /**
     * Debug level logcat message (only output for debug builds)
     *
     * @param tag
     * @param message
     */
    public static void d(String tag, String message) {
        if (CEBuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

    /**
     * Information level logcat message (always output)
     *
     * @param tag
     * @param message
     */
    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    /**
     * Error level logcat message (always output)
     *
     * @param tag     tag
     * @param message message
     */
    public static void e(String tag, String message) {
        Log.e(tag, message);
    }
}
