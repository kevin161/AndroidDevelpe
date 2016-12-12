package com.gyz.androiddevelope.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.SlidingItemMenuLayout.java
 * @author: ZhaoHao
 * @date: 2016-08-19 13:43
 */
public class SlidingItemMenuLayout extends LinearLayout {
    private static final String TAG = "SlidingItemMenuLayout";

    private Scroller mScroller;
    private View leftChild;
    private View rightChild;

    public SlidingItemMenuLayout(Context context) {
        this(context, null);
    }

    public SlidingItemMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        mScroller = new Scroller(getContext(), null, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.leftChild = getChildAt(0);
        this.rightChild = getChildAt(1);
    }


    private float startX;
    private float startY;
    private float dx;
    private float dy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                startX = event.getX();
                startY = event.getY();

                super.dispatchTouchEvent(event);
                return true;
            case MotionEvent.ACTION_MOVE:
                dx = event.getX() - startX;
                dy = event.getY() - startY;
                //dx>0右滑   <0左滑
                //触发移动事件的最短距离，如果小于这个距离就不触发移动控件
                if (Math.abs(dx) - Math.abs(dy) > ViewConfiguration.getTouchSlop()) {

                    //滑动的距离不能大于rightWidth
                    if (getScrollX() + (-dx) > rightChild.getWidth() || getScrollX() + (-dx) < 0) {
                        return true;
                    }
                    this.scrollBy((int) -dx, 0);
                    startX = event.getX();
                    startY = event.getY();
                }


                break;
            case MotionEvent.ACTION_UP:

                //仅仅只是把滑动的情况和参数描述和记录。
                //判断当前松开手是往左滑还是往右滑
                 int offset = (getScrollX()/(float)rightChild.getWidth()) > 0.5f ? rightChild.getWidth()-getScrollX() : -getScrollX();
                mScroller.startScroll(getScrollX(), getScrollY(), offset, 0);
                invalidate();
                startX = 0;
                startY = 0;
                dx = 0;
                dy = 0;

                break;
        }
        return super.onTouchEvent(event);
    }

    //在开启滑动的情况下（mScroller.startScroll），滑动的过程当中此方法会被不断调用
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getStartX(), mScroller.getStartY());
            postInvalidate();
        }
    }
}
