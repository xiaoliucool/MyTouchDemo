package com.xiaoliu.mytouchdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity implements MyDoubleClickCallback {
    private static final int DOUBLE_CLICK = 2;
    private Button mButton;
    private TextView mTextView;
    private Button mJumpButton;
    private static boolean isColorChanged = false;
    private MyMultiClickListener mMultiClickListener = new MyMultiClickListener() {
        @Override
        public void onMultiTouch(View v, MotionEvent event, int touchCount) {
            if (touchCount == DOUBLE_CLICK) {
                onDoubleClicked();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.btDoubleClick);
        mButton.setOnTouchListener(mMultiClickListener);
        mTextView = (TextView) findViewById(R.id.tvContent);
        mJumpButton = (Button) findViewById(R.id.btnBrowsePic);
        mJumpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BrowsePicActivity.startActivity(MainActivity.this);
            }
        });
    }

    @Override
    public void onDoubleClicked() {
        if (!isColorChanged) {
            mButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            isColorChanged = true;
        } else {
            mButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            isColorChanged = false;
        }
        mTextView.setText(getString(R.string.content, "4"));
    }
}

interface MyDoubleClickCallback {
    void onDoubleClicked();
}