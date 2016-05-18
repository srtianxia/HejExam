package com.srtianxia.hejexam.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by srtianxia on 2016/5/18.
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
