package com.example.androiddemo.utils;

import android.annotation.SuppressLint;
import android.content.Context;

public class ContextUtils {
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    public static final String TAG  = "MVP_ANDROID";
    public static void registerActivity(Context activity) {
        context = activity;
    }
}
