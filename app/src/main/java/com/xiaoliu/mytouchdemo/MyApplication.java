package com.xiaoliu.mytouchdemo;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * com.xiaoliu.mytouchdemo.MyApplication
 *
 * @author 刘文超
 * @date 2016/7/19-13:51
 * @desc 自定义application
 */

public class MyApplication extends Application{
    private static MyApplication instance;
    protected Handler handler = new Handler(Looper.getMainLooper());

    public static MyApplication sharedInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public void postTaskInUIThread(Runnable runable) {
        this.handler.post(runable);
    }

    public void postTaskInUIThread(Runnable runable, int delayMillis) {
        this.handler.postDelayed(runable, delayMillis);
    }

    public Handler getMainLoopHandler() {
        return this.handler;
    }
}
