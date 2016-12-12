package com.gyz.androiddevelope.behavior;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.gyz.androiddevelope.util.LogUtils;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.behavior.FbtnBehavior.java
 * @author: ZhaoHao
 * @date: 2016-05-19 08:30
 */
public class FbtnBehavior extends FloatingActionButton.Behavior {
    private static final String TAG = "FbtnBehavior";

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        LogUtils.e(TAG, "layoutDependsOn++++判断是否为想要监听的对象++++++++++++++++++");
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        LogUtils.e(TAG, "onDependentViewChanged++++++ 监听对象发生改变时，需要做的动作++++++++++++++++");
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, FloatingActionButton child, int layoutDirection) {
        LogUtils.e(TAG, "onLayoutChild++++++++++++++++++++++");
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        LogUtils.e(TAG, "onStartNestedScroll++++++++++判断是否为想要监听的方向++++++++++++");
        return (nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL) || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);

    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dx, int dy, int[] consumed) {
        //被监听对象发生变化时   需要做的操作
        int scrollY = target.getScrollY();
        child.setScrollY(scrollY);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        LogUtils.e(TAG, "onNestedScroll-------------------");
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, float velocityX, float velocityY, boolean consumed) {
        //滚动惯性时的操作
//        ((NestedScrollView)child).fling((int) velocityY);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
}
