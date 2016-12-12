package com.gyz.androiddevelope.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.SlidingMenu.java
 * @author: ZhaoHao
 * @date: 2016-08-19 11:21
 */
public class SlidingMenu extends HorizontalScrollView {
    private static final String TAG = "SlidingMenu";
    private int mScreenWidth;
    private ViewGroup mMenu;
    private ViewGroup mMain;
    private int mMenuRightPadding = 100;
    private int mMenuWidth;
    private boolean isOnce;

    public SlidingMenu(Context context) {
        this(context,null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wn = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wn.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isOnce) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) wrapper.getChildAt(0);
            mMain = (ViewGroup) wrapper.getChildAt(1);

            mMenuWidth = mScreenWidth - mMenuRightPadding;
            mMenu.getLayoutParams().width = mMenuWidth;
            mMain.getLayoutParams().width = mScreenWidth;

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (changed) {
            this.scrollTo(mMenuWidth,0);
            isOnce = true;
        }
        super.onLayout(changed, l, t, r, b);

    }

    float downX;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case  MotionEvent.ACTION_DOWN:
                downX = ev.getX();
            break;

            case MotionEvent.ACTION_UP:

                float dx = ev.getX() - downX;
                if (dx< mScreenWidth/3){
                    this.smoothScrollTo(mMenuWidth,0);
                }else {
                    this.smoothScrollTo(0,0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //������
        //�����ٷֱ�����
        float factor = (float)l / mMenuWidth;
        //1.ƽ��Ч��(����2.x��nineOldeAndroid.jar)
//		mMenu.setTranslationX(mMenuWidth*);
        //�������ٶ�Ӧ�ñ���������һ��
        ViewHelper.setTranslationX(mMenu, mMenuWidth*factor*0.6f);
        //2.����Ч��
        float leftScale = 1-0.4f*factor;
        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);

        float rightScale = 0.8f+0.2f*factor;
        ViewHelper.setScaleX(mMain, rightScale);
        ViewHelper.setScaleY(mMain, rightScale);
        //3.͸����Ч��
        ViewHelper.setAlpha(mMenu, 1-factor);
    }
}
