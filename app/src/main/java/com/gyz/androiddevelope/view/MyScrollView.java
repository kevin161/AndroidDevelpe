package com.gyz.androiddevelope.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * @version V1.0   用于平行空间引导页
 * @FileName: com.gyz.androiddevelope.view.MyScrollView.java
 * @author: ZhaoHao
 * @date: 2016-08-02 15:08
 */
public class MyScrollView extends HorizontalScrollView {
    private static final String TAG = "MyScrollView";

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
