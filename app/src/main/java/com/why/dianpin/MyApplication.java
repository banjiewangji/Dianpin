package com.why.dianpin;

import android.app.Application;
import android.content.Context;

/**
 * @author shidefeng
 * @since 2018/4/10.
 */

public class MyApplication extends Application {

    public static MyApplication sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }
}
