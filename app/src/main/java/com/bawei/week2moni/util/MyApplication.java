package com.bawei.week2moni.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 全局Context
 */
public class MyApplication extends Application {
    //定义
    private static Context context;
    private static SharedPreferences sharedPreferences;
    public static Context getContext() {
        return context;
    }
    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
    //获取Context对象
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
    }
}
