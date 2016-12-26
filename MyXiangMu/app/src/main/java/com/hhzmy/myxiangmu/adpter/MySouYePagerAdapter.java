package com.hhzmy.myxiangmu.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class MySouYePagerAdapter extends PagerAdapter {
    private List<ImageView> imglist;

    public MySouYePagerAdapter(List<ImageView> imglist) {
        this.imglist = imglist;
    }

    @Override
    public int getCount() {
        return imglist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imglist.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=imglist.get(position);
        ViewParent parent= imageView.getParent();
        if (parent!=null)
        {
            ViewGroup group= (ViewGroup) parent;
            group.removeView(imageView);
        }
        container.addView(imageView);
        return imageView;
    }
}
