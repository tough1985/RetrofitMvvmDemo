package me.xiba.startlearnmvvm;

import android.app.Application;

/**
 * Created by liukun on 2017/12/21.
 */

public class MyApplication extends Application{

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }
}
