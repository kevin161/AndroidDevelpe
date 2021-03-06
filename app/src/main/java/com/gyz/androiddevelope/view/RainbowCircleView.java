package com.gyz.androiddevelope.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * 变色的弧度
 * @author: guoyazhou
 * @date: 2016-02-03 11:48
 */
public class RainbowCircleView extends View {
    private static final String TAG = "CircleView";

    private float mMinSize = 1.88f;
    private Paint paint;
    private RectF mArcRect;
    private int width;
    private int height;

    private Paint paintLine;

    public RainbowCircleView(Context context) {
        this(context, null);
    }

    public RainbowCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RainbowCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(35);
        paint.setAntiAlias(true);

        mArcRect = new RectF();

        paintLine = new Paint();
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeCap(Paint.Cap.ROUND);
        paintLine.setStrokeWidth(35);
        paintLine.setAntiAlias(true);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth();
        height = getHeight();


        int mArcCenterX = width / 2;
        int mArcCenterY = (int) (200 * mMinSize);
        int arcRadius = (int) ((308 / 2) * mMinSize);

        mArcRect.left = mArcCenterX - arcRadius;
        mArcRect.top = mArcCenterY - arcRadius;
        mArcRect.right = mArcCenterX + arcRadius;
        mArcRect.bottom = mArcCenterY + arcRadius;

        int[] colors = {0xFF9A9BF8, 0xFF9AA2F7, 0xFF65CCD1, 0xFF63D0CD, 0xFF68CBD0, 0xFF999AF6, 0xFF9A9BF8};
        float[] positions = {0, 1f / 6, 2f / 6, 3f / 6, 4f / 6, 5f / 6, 1};
        SweepGradient  mSweepGradient = new SweepGradient(mArcCenterX, mArcCenterX, colors, positions);

        paint.setShader(mSweepGradient);

        canvas.drawArc(mArcRect, -240, 300, false, paint);


       // canvas.drawLine(50,50,10,90,paintLine);
    }

//    private void test(){
//        private int colorSweep[] = {getResources().getColor(R.color.color_ffac00),
//                getResources().getColor(R.color.color_cafe00), getResources().getColor(R.color.color_00fcfb)};
//        private float colorPosition[] = {0.2f, 0.5f, 1.0f};
//        private SweepGradient sweepGradient = null;
//        private void drawArc(Canvas canvas, RectF rectF) {
//            if(sweepGradient == null) {
//                sweepGradient = new SweepGradient(getWidth() / 2, getWidth() / 2, colorSweep, colorPosition);
//                arcPaint.setShader(sweepGradient);
//            }
//            canvas.drawArc(rectF, startAngle, unitScale * currVirtuelPercent, false, arcPaint);
//        }
//    }
}
