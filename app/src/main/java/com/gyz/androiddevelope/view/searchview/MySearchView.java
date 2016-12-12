package com.gyz.androiddevelope.view.searchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.searchview.MySearchView.java
 * @author: ZhaoHao
 * @date: 2016-12-07 15:47
 */
public class MySearchView extends View {
    private static final String TAG = "MySearchView";
    private Paint mPaint;
    private BaseControl mController;

    public MySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
    }

    public void setController(BaseControl controller){
        this.mController = controller;
        this.mController.setSearchView(this);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mController.draw(canvas,mPaint);
    }

    public void startAnimation(){
        if(mController!=null){
            mController.startAnimation();
        }
    }

    public void resetAnimation(){
        if(mController!=null){
            mController.resetAnimation();
        }
    }

}
