package com.bwie.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by 董绍华 on 2017/9/16.
 * ViewPager适配器
 */

public class ViewPagerAdapter extends PagerAdapter{
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
