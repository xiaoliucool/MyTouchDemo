package com.xiaoliu.mytouchdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.xiaoliu.mytouchdemo.util.Constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ${CLASS_NAME}
 *
 * @author Administrator
 * @date 2016/7/20-16:39
 * @desc ${描述类实现的功能}
 */

public class BrowsePicActivity extends Activity {
    private GridView mGridView;
    private MyGridAdapter mAdapter;
    private List<String> mList;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, BrowsePicActivity.class);
        intent.putExtra(Constants.IMAGES, Constants.images);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridshowpic);
        String[] imageUrls = getIntent().getStringArrayExtra(Constants.IMAGES);
        mList = Arrays.asList(imageUrls);
        init();
    }

    private void init() {
        mGridView = (GridView) findViewById(R.id.gvPic);
        mAdapter = new MyGridAdapter(mList, this);
        mGridView.setAdapter(mAdapter);
    }
}
