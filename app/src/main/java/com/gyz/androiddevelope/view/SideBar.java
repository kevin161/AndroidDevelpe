package com.gyz.androiddevelope.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 右侧边字母索引view
 * @version V3.0
 * @FileName: com.gyz.androiddevelope.view.SideBar.java
 * @author: GYZ
 * asia161@qq.com
 * @date: 2019-07-18 15:27
 */
public class SideBar extends View {
    private String initials[] = {"#","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Paint mPaint;
    private int initialHeight;
    private int touchItem = -1;
    private SideBarItemSelectedListener listener;

    public SideBar(Context context) {
        this(context, null);
    }

    public SideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();//获取控件高度
        int width = getWidth();//获取控件宽度
        initialHeight = height / initials.length;//计算出要分陪给每个字母的高度
        //进行绘制
        for (int i = 0; i < initials.length; i++) {
            mPaint.setColor(i == touchItem ? Color.GREEN : Color.BLUE);//如果当前字母被选中字体颜色就为绿色
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(30);
            float pionx = (width / 2 - mPaint.measureText(initials[i]) / 2);//字体的x轴上的位置
            canvas.drawText(initials[i], pionx, (initialHeight * i) + initialHeight, mPaint);
            mPaint.reset();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                float point = event.getY();
                int index = (int) (point / getHeight() * initials.length);// 通过获取触摸的y轴的位置，计算出是第几个字母
                if (listener != null) {
                    touchItem = index;
                    listener.itemSelected(initials[index]);
                }
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    public void setItemSelectedListener(SideBarItemSelectedListener listener) {
        this.listener = listener;
    }

    public interface SideBarItemSelectedListener {//触摸事件的监听接口
        void itemSelected(String str);
    }
}