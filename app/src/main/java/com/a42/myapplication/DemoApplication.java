package com.a42.myapplication;

import android.app.Application;

/**
 * Created by a42 on 14/12/30.
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FileUtils.copyAssets(this);
    }
}
