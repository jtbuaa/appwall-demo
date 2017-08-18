package com.example.jt.demo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by jt on 17-8-18.
 */

public class MyApp extends Application {
    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context) {
        MyApp application = (MyApp) context
                .getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }

}
