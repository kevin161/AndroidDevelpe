package com.gyz.androiddevelope.view.searchview;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.searchview.BaseControl.java
 * @author: ZhaoHao
 * @date: 2016-12-07 15:48
 */
public abstract class BaseControl {
    private static final String TAG = "BaseControl";

    public static final int STATE_ANIM_NONE = 0;
    public static final int STATE_ANIM_START = 1;
    public static final int STATE_ANIM_STOP = 2;
    public static final int DEFAULT_ANIM_TIME = 5000;
    public static final float DEFAULT_ANIM_STARTF = 0;
    public static final float DEFAULT_ANIM_ENDF = 1;
    private MySearchView mySearchView;
    public int mState = STATE_ANIM_NONE;
    public float mpro = -1;

    public abstract void draw(Canvas canvas, Paint paint);

    public void startAnimation(){}

    public void resetAnimation(){}

    public void setSearchView(MySearchView searchView){
        this.mySearchView = searchView;
    }

    public int getWidth(){
        return mySearchView.getWidth();
    }

    public int getHeight(){
        return mySearchView.getHeight();
    }

    public ValueAnimator startViewAnimation(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(801);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mpro = animation.getAnimatedFraction();
                mySearchView.invalidate();
            }
        });

        valueAnimator.start();
        mpro = 0;
        return valueAnimator;
    }

}
