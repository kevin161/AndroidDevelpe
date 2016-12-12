package com.gyz.androiddevelope.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.gyz.androiddevelope.R;

/**
 * 仪表盘
 *
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.util.TemperatureView.java
 * @author: ZhaoHao
 * @date: 2016-08-31 15:46
 */
public class DashboardView extends View {
    private static final String TAG = "TemperatureView";

    public static final float ARC_STROKE_WIDTH = 75;
//    最大刻度数量
    public static final int MAX_TEM = 40;
//    刻度长度
    public static final int SCALE_LENGTH= 12;
    public float bgCircleRadius;

    private float width, height, centerWidth, centerHeight;
    private Paint mArcPaint,mThinArcPaint,mPanelPaint, mBgPaint,mTxtPaint,
            mPointPaint,mScaleTxtPaint;
    private int degree;
    private RectF rectF;

    public DashboardView(Context context) {
        this(context, null);
    }

    public DashboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(ARC_STROKE_WIDTH);

        mThinArcPaint = new Paint();
        mThinArcPaint.setAntiAlias(true);
        mThinArcPaint.setStyle(Paint.Style.STROKE);
        mThinArcPaint.setStrokeCap(Paint.Cap.BUTT);
        mThinArcPaint.setColor(Color.BLACK);
        mThinArcPaint.setStrokeWidth(3);

        mPanelPaint = new Paint();
        mPanelPaint.setStyle(Paint.Style.FILL);
        mThinArcPaint.setColor(Color.BLACK);
        mPanelPaint.setStrokeWidth(3);

        mTxtPaint = new Paint();
        mTxtPaint.setColor(Color.BLACK);
        mTxtPaint.setTextSize(30);

        mPointPaint = new Paint();
        mPointPaint.setColor(Color.GRAY);
        mPointPaint.setStyle(Paint.Style.FILL);

        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setColor(getResources().getColor(R.color.colorWhite));
        mBgPaint.setStyle(Paint.Style.FILL);

        mScaleTxtPaint = new Paint();
        mScaleTxtPaint.setAntiAlias(true);
        mScaleTxtPaint.setTextSize(24);
        mScaleTxtPaint.setColor(getResources().getColor(R.color.colorBlack));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画背景白色圆
        drawBgCircle(canvas);

        //画3段圆弧
        drawArc(canvas);

        //画圆弧上的文字
        drawArcTxt(canvas);

//       画刻度弧
        drawThinArc(canvas);

//       画刻度
        drawArcScale(canvas);

        //中心圆
        drawCenterCircle(canvas);
    }

    private void drawCenterCircle(Canvas canvas) {
        canvas.drawCircle(centerWidth,centerHeight,85,mPointPaint);
    }

    private void drawArcTxt(Canvas canvas) {

        canvas.save();
        //正常
        canvas.rotate(-60,centerWidth,centerHeight);
        canvas.drawText("正常",centerWidth,centerHeight - bgCircleRadius+3*ARC_STROKE_WIDTH/5,mTxtPaint);
        //预警
        canvas.rotate(90,centerWidth,centerHeight);
        canvas.drawText("预警",centerWidth,centerHeight - bgCircleRadius+3*ARC_STROKE_WIDTH/5,mTxtPaint);
        //警告
        canvas.rotate(60,centerWidth,centerHeight);
        canvas.drawText("警告",centerWidth,centerHeight - bgCircleRadius+3*ARC_STROKE_WIDTH/5 ,mTxtPaint);
        canvas.restore();

    }

    private void drawArcScale(Canvas canvas) {
        float mAngle = 240f / MAX_TEM;

        canvas.save();

        canvas.rotate(-120,centerWidth,centerHeight);
        for (int i = 0; i <= MAX_TEM; i++) {
            if (i%5 ==0){
                canvas.drawLine(centerWidth,centerHeight - bgCircleRadius+ARC_STROKE_WIDTH,
                        centerWidth,centerHeight - bgCircleRadius+ARC_STROKE_WIDTH+SCALE_LENGTH*2,mThinArcPaint);
                //写刻度
                canvas.drawText(String.valueOf(i),centerWidth - mScaleTxtPaint.measureText(String.valueOf(i))/2,centerHeight - bgCircleRadius+ARC_STROKE_WIDTH+SCALE_LENGTH*2+
                        28, mScaleTxtPaint);
            }else {
                canvas.drawLine(centerWidth,centerHeight - bgCircleRadius+ARC_STROKE_WIDTH,
                        centerWidth,centerHeight - bgCircleRadius+ARC_STROKE_WIDTH+SCALE_LENGTH,mThinArcPaint);
            }
           canvas.rotate(mAngle,centerWidth,centerHeight);
        }

        canvas.restore();
    }

    private void drawThinArc(Canvas canvas) {
//        刻度弧
        canvas.drawArc(new RectF(centerWidth -bgCircleRadius+ARC_STROKE_WIDTH,centerHeight - bgCircleRadius+ARC_STROKE_WIDTH
                ,centerWidth +bgCircleRadius-ARC_STROKE_WIDTH,centerHeight+ bgCircleRadius-ARC_STROKE_WIDTH ),150,240,false,mThinArcPaint);
    }

    private void drawArc(Canvas canvas) {
        //green
        mArcPaint.setColor(Color.GREEN);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF,150, 120, false,mArcPaint);
        //red
        mArcPaint.setColor(Color.RED);

        mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF,330,60,false,mArcPaint);
        //yellow
        mArcPaint.setColor(Color.YELLOW);
        mArcPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawArc(rectF,270,60,false,mArcPaint);
    }

    private void drawBgCircle(Canvas canvas) {
        rectF = new RectF(centerWidth -bgCircleRadius+ARC_STROKE_WIDTH/2,centerHeight - bgCircleRadius+ARC_STROKE_WIDTH/2
                ,centerWidth +bgCircleRadius-ARC_STROKE_WIDTH/2,centerHeight+ bgCircleRadius-ARC_STROKE_WIDTH/2 );
        canvas.drawCircle(centerWidth,centerHeight,bgCircleRadius,mBgPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        centerHeight = h / 2;
        centerWidth = w / 2;
        bgCircleRadius = centerWidth-20;
    }

    public void setDegree(int degree1 ){
        this.degree = degree1;
        invalidate();
    }
}
