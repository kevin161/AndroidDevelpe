package com.gyz.androiddevelope.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.util.DensityUtils;


/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.FlickerProgressBar.java
 * @author: ZhaoHao
 * @date: 2016-09-29 10:00
 */
public class FlickerProgressBar extends View implements Runnable {
    private static final String TAG = "FlickerProgressBar";
    private PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
    private int DEFAULT_HEIGHT_DP = 35;
    private float MAX_PROGRESS = 100f;
    private Paint textPaint;
    private Paint bgPaint;
    private String progressText;
    private Rect textBouds;
    /**
     * 来回移动的滑块
     */
    private Bitmap flikerBitmap;

    /**
     * 滑块移动到最左边位置，作用是控制移动
     */
    private float flickerLeft;

    /**
     * 进度条bitman，包含滑块
     */
    private Bitmap pgBitmap;

    private Canvas pgCanvas;
    /**
     * 当前进度
     */
    private float progress;

    private boolean isFinish;

    private boolean isStop;

    /**
     * 下载中的颜色
     */
    private int loadingColor;

    /**
     * 暂停时颜色
     */
    private int stopColor;
    /**
     * 进度文本、边框、进度条颜色
     */
    private int progressColor;

    private int textSize;

    private Thread thread;

    public FlickerProgressBar(Context context) {
        this(context, null);
    }

    public FlickerProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlickerProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FlickerProgressBar);
        textSize = (int) a.getDimensionPixelSize(R.styleable.FlickerProgressBar_textSize, DensityUtils.dp2px(getContext(), 12));
        loadingColor = a.getColor(R.styleable.FlickerProgressBar_loadingColor, Color.parseColor("#40c4ff"));
        stopColor = a.getColor(R.styleable.FlickerProgressBar_stopColor, Color.parseColor("#ff9800"));
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;

        switch (heightSpecMode) {
            case MeasureSpec.AT_MOST:
                height = DensityUtils.dp2px(getContext(), DEFAULT_HEIGHT_DP);
                break;
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:
                height = heightSpecSize;
                break;
        }
        setMeasuredDimension(widthSpecSize, height);
        init();
    }

    private void init() {
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(textSize);
        textBouds = new Rect();

        progressColor = loadingColor;
        flikerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flicker);
        flickerLeft = -flikerBitmap.getWidth();

        pgBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        pgCanvas = new Canvas(pgBitmap);

        thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        边框
        drawBorder(canvas);
//        进度
        drawProgress();
        canvas.drawBitmap(pgBitmap, 0, 0, null);
//        进度text
        drawProgressText(canvas);
//        变色处理
        drawColorProgressText(canvas);

    }

    private void drawBorder(Canvas canvas) {
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setColor(progressColor);
        bgPaint.setStrokeWidth(DensityUtils.dp2px(getContext(),1));
        canvas.drawRect(0,0,getWidth(),getHeight(),bgPaint);
    }

    private void drawProgress() {
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setStrokeWidth(0);
        bgPaint.setColor(progressColor);

        float right = (progress/MAX_PROGRESS)*getMeasuredWidth();
        pgCanvas.save(Canvas.CLIP_SAVE_FLAG);
        pgCanvas.clipRect(0,0,right,getMeasuredHeight());
        pgCanvas.drawColor(progressColor);
        pgCanvas.restore();

        if (!isStop){
//            画光晕
         bgPaint.setXfermode(xfermode);
            pgCanvas.drawBitmap(flikerBitmap,flickerLeft,0,bgPaint);
            bgPaint.setXfermode(null);
        }
    }

    private void drawProgressText(Canvas canvas) {
        textPaint.setColor(progressColor);
        progressText = getProgressText();
        textPaint.getTextBounds(progressText,0,progressText.length(),textBouds);
        int tWidth = textBouds.width();
        int tHeight = textBouds.height();
        float xCoordinate = (getMeasuredWidth() - tWidth)/2;
        float yCoordinate = (getMeasuredHeight() +tHeight)/2;
        canvas.drawText(progressText,xCoordinate,yCoordinate,textPaint);

    }

    /**
     * 变色处理
     * @param canvas
     */
    private void drawColorProgressText(Canvas canvas) {
        textPaint.setColor(Color.WHITE);
        int tWidth = textBouds.width();
        int tHeight = textBouds.height();
        float xCoordinate = (getMeasuredWidth() - tWidth)/2;
        float yCoordinate = (getMeasuredHeight() +tHeight)/2;
        float progressWidth = (progress/MAX_PROGRESS)*getMeasuredWidth();
        if (progressWidth>xCoordinate){
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            //计算显示成白色的文字  右边长度
            float right = Math.min(progressWidth,xCoordinate+tWidth);
            canvas.clipRect(xCoordinate,0,right,getMeasuredHeight());
            canvas.drawText(progressText,xCoordinate,yCoordinate,textPaint);
            canvas.restore();
        }
    }

    public void setProgress(float progress){
        if (!isStop){
            this.progress = progress;
            invalidate();
        }
    }

    public float getProgress() {
        return progress;
    }

    public void setStop(boolean stop){
        isStop = stop;
        if (isStop){
            progressColor = stopColor;
        }else {
            progressColor = loadingColor;
            thread = new Thread(this);
            thread.start();
        }
        invalidate();
    }

    public void finishLoad() {
        isFinish = true;
        setStop(true);
    }

    public boolean isStop() {
        return isStop;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void toggle(){
        if(!isFinish){
            if(isStop){
                setStop(false);
            } else {
                setStop(true);
            }
        }
    }

    @Override
    public void run() {
        int width = flikerBitmap.getWidth();
        while (!isStop){
            flickerLeft += DensityUtils.dp2px(getContext(),5);
            float progressWidth = (progress / MAX_PROGRESS)*getMeasuredWidth();
            if (flickerLeft >= progressWidth){
                flickerLeft =-width;
            }
            postInvalidate();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getProgressText(){
        String text = "";
        if (!isFinish){
            if (!isStop){
                text = "下载中"+progress+"%";
            }else {
                text = "继续";
            }
        }else {
            text = "下载完成";
        }

        return  text;
    }
}
