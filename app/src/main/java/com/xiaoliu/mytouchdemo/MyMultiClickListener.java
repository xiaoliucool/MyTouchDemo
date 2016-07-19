package com.xiaoliu.mytouchdemo;

import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.xiaoliu.mytouchdemo.MyMultiClickListener
 *
 * @author Administrator
 * @date 2016/7/19-14:02
 * @desc 多次点击事件的监听类，可以实现双击效果
 */

public abstract class MyMultiClickListener implements View.OnTouchListener{
    /**
     * 上次 onTouch 发生的时间
     */
    private long lastTouchTime = 0;
    /**
     * 已经连续 touch 的次数
     */
    private AtomicInteger touchCount = new AtomicInteger(0);

    private Runnable mRun = null;

    public void removeCallback() {
        if (mRun != null) {
            MyApplication.sharedInstance().getMainLoopHandler().removeCallbacks(mRun);
            mRun = null;
        }
    }
    @Override
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (motionEvent.getAction()==MotionEvent.ACTION_UP){
            final long now = System.currentTimeMillis();
            lastTouchTime = now;
            touchCount.incrementAndGet();
            removeCallback();

            mRun = new Runnable() {
                @Override
                public void run() {
                    if (now==lastTouchTime){
                        onMultiTouch(view, motionEvent, touchCount.get());
                        touchCount.set(0);
                    }
                }
            };
            MyApplication.sharedInstance().postTaskInUIThread(mRun, getMultiTouchInterval());
        }
        return false;
    }
    /**
     * 连续touch的最大间隔, 超过该间隔将视为一次新的touch开始， 默认是400，推荐值，也可以由客户代码指定
     *
     * @return
     */
    protected int getMultiTouchInterval() {
        return 400;
    }

    /**
     * 连续点击事件回调
     *
     * @param v
     * @param event
     * @param touchCount 连续点击的次数
     * @return
     */
    public abstract void onMultiTouch(View v, MotionEvent event, int touchCount);
}