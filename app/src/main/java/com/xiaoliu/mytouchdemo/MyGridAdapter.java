package com.xiaoliu.mytouchdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * ${CLASS_NAME}
 *
 * @author Administrator
 * @date 2016/7/20-14:33
 * @desc ${描述类实现的功能}
 */

public class MyGridAdapter extends BaseAdapter {
    private List<String> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public MyGridAdapter(List<String> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == convertView) {
            View root = mInflater.inflate(R.layout.activity_gridshowpic_item, viewGroup, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) root.findViewById(R.id.ivPic);
            convertView = root;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 构建完成
        //holder.imageView.setImageResource(R.mipmap.ic_launcher);
        ImageLoader.getInstance().displayImage(getItem(position), holder.imageView, options);
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
    }
}
