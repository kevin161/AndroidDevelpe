package com.gyz.androiddevelope.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 模仿win10的加载条
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.Win10Progress.java
 * @author: ZhaoHao
 * @date: 2016-09-20 09:18
 */
public class Win10Progress extends View{
    private static final String TAG = "Win10Progress";

    private Paint mPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private ValueAnimator valueAnimator;
    //用这个来接受ValueAnimator的返回值，代表整个动画的进度
    private float t;
    private float mWidth,mHeight;

    public Win10Progress(Context context) {
        this(context,null);
    }

    public Win10Progress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Win10Progress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        valueAnimator.start();
    }

    private void init() {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);

//        初始化Path和mPathMeasure
//        这里角度不能选360，否则会测量失误，具体原因和android的内部优化有关
        mPath = new Path();
        RectF rectF  = new RectF(-150,-150,150,150);
        mPath.addArc(rectF,-90,359.9f);
        mPathMeasure =new PathMeasure(mPath,false);

//        初始化ValueAnimator
        valueAnimator = ValueAnimator.ofFloat(0f,1f).setDuration(3000);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                t = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth/2,mHeight/2);
        Path dst = new Path();
        if(t>=0.95){
            canvas.drawPoint(0,-150,mPaint);
        }
//我们设置让t每间隔0.05就画一个点，总共画4个点，注意这里getSegment()的最后一个要设置为true来保证画出来的是多个点而不是一条线
        int num = (int) (t/0.05);
        float s,x, y;
        switch (num){
            default:
            case 3:
                x = t-0.15f*(1-t);
                s = mPathMeasure.getLength();
                y = -s*x*x+2*s*x;
                mPathMeasure.getSegment(y,y+1,dst,true);
            case 2:
                x = t-0.10f*(1-t);
                s = mPathMeasure.getLength();
                y = -s*x*x+2*s*x;
                mPathMeasure.getSegment(y,y+1,dst,true);
            case 1:
                x = t-0.05f*(1-t);
                s = mPathMeasure.getLength();
                y = -s*x*x+2*s*x;
                mPathMeasure.getSegment(y,y+1,dst,true);
            case 0:
                x = t;
                s = mPathMeasure.getLength();
                y = -s*x*x+2*s*x;
                mPathMeasure.getSegment(y,y+1,dst,true);
                break;

        }

//        mPathMeasure.getSegment(mPathMeasure.getLength()*t,mPathMeasure.getLength()*+1,dst,true);
        canvas.drawPath(dst,mPaint);
    }

}
