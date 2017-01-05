package com.gyz.androiddevelope.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 热门标签  自定义View
 *
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.MyViewGroupActivity.java
 * @author: ZhaoHao
 * @date: 2016-12-31 14:23
 */
public class TagViewGroup extends ViewGroup {

    public TagViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int childCount = getChildCount();
        int width = 0;//width=所有行里面最宽的一行
        int height = 0;//height=所有行的高度相加
        //一行的宽度=一行当中的所有view的宽度的和
        int lineWidth = 0;
        int lineHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            //子控件真实占用的宽和高度
            int childWidth = child.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            //当一行放不下的时候需要换行
            if (lineWidth + childWidth > sizeWidth) {
                //换行
                width = Math.max(width, lineWidth);
                lineWidth = childWidth;

                height += lineHeight;
                lineHeight = childHeight;
            } else {
                //不换行  累加数值
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }

            if (i == childCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }

        //2.测量并定义自身的大小
        int measuredWidth = modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width;
        int measuredHeight = modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height;

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    //记录每一行有多高
    private List<Integer> lineHeights = new ArrayList<>();
    private List<List<View>> views = new ArrayList<>();

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        views.clear();
//        lineHeights.clear();
//
//        List<View> childList = new ArrayList<>();
//        int width = getMeasuredWidth();
//        int lineWidth = 0;
//        int lineHeight = 0;//一行中最高的高度
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = getChildAt(i);
//            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
//            int childWidth = child.getMeasuredWidth();
//            int childHeight = child.getMeasuredHeight();
//
//            if (lp.rightMargin+lp.leftMargin+childWidth+lineWidth>width){
//                //换行
//                lineHeights.add(lineHeight);
//                views.add(childList);
//                lineWidth = 0;
//                childList = new ArrayList<>();
//            }
//                childList.add(child);
//                lineWidth +=lp.rightMargin+lp.leftMargin+childWidth;
//                lineHeight = Math.max(lineHeight,childHeight+lp.topMargin+lp.bottomMargin);
//        }
//        lineHeights.add(lineHeight);
//        views.add(childList);
//
//        int left = 0;
//        int top = 0;
//        //2.摆放
//        int size = views.size();
//        for (int i = 0; i < size; i++) {
//           List<View> list =  views.get(i);
//            lineHeight = lineHeights.get(i);
//            for (int j = 0; j < list.size(); j++) {
//                View child = list.get(j);
//                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
//                int lc  = left+lp.leftMargin;
//                int tc  = top + lp.topMargin;
//                int rc = lc + child.getMeasuredWidth();
//                int bc  = tc + child.getMeasuredHeight();
//
//                child.layout(lc,tc,rc,bc);
//                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
//            }
//            left = 0;
//            top+=lineHeight;
//        }
//    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        views.clear();
        lineHeights.clear();
        List<View> list = new ArrayList<>();
        int sizeWidth = getMeasuredWidth();
        int count = getChildCount();
        int lineWidth = 0, lineHeight = 0;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = lp.leftMargin + lp.rightMargin + child.getMeasuredWidth();
            int childHeight = lp.topMargin + lp.bottomMargin + child.getMeasuredHeight();
            if (childWidth + lineWidth > sizeWidth) {
//                换行
                lineHeights.add(lineHeight);
                views.add(list);
                list = new ArrayList<>();
                list.add(child);
                lineWidth  = childWidth;
                lineHeight = childHeight;

            } else {
//                不换行
                list.add(child);
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);

            }
            if (i == count-1){
                views.add(list);
                lineHeights.add(lineHeight);
            }
        }

        int left=0,top=0;
        for (int i = 0; i < views.size(); i++) {
            List<View> list1 = views.get(i);
            int height = lineHeights.get(i);
            for (int j = 0; j < list1.size(); j++) {
                View child = list1.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                int ll = left+lp.leftMargin;
                int tt = top+lp.topMargin;
                int rr = left+lp.rightMargin+child.getMeasuredWidth();
                int bb = top+lp.topMargin+child.getMeasuredHeight();
                child.layout(ll,tt,rr,bb);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = 0;
            top+=height;
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

}
