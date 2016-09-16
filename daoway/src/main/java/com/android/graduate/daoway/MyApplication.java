package com.android.graduate.daoway;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;


/**
 * Created by Administrator on 2016/9/13.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLocation();

    }

    private void initLocation() {
        SDKInitializer.initialize(getApplicationContext());
    }
}
