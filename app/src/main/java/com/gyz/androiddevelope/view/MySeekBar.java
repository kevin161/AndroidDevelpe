package com.gyz.androiddevelope.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.util.LogUtils;

import java.util.ArrayList;

/**
 * 添富智投风险等级选择
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.MySeekBar.java
 * @author: ZhaoHao
 * @date: 2017-04-28 11:29
 */
public class MySeekBar extends View {
    public static final String tag = "MySeekBar";
    private Paint mLinePaint,mTextPaint;

    private int downX = 0;
    private int downY = 0;
    private int upX = 0;
    private int upY = 0;
    private int moveX = 0;
    private int moveY = 0;
    private float width,height ,centerWidth,centerHeight,wholeLineLen,
    /**
     * 可用线段总长度
     */
    usableLineLen,
    perUsableLineLen;
    private Bitmap spot;
    private Bitmap spotOn;
    private ArrayList<String> sectionTitle;
    private float linePadding = 30;
    private float hotArea = 100;//点击的热区
//    首末空出的线段
    private float freeLineLen = 2*linePadding;
//    当前选中
    private int curSection = 2;
    //推荐项
    private int recommendSection=2;

    //字与下方点的距离，因为字体字体是40px，再加上10的间隔
    private int textMove = 60;
    private int spotWidth,spotHeight,spotOnWidth,spotOnHeight;

    public MySeekBar(Context context) {
        this(context,null);
    }

    public MySeekBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);//锯齿不显示
        mLinePaint.setStrokeWidth(13);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mLinePaint.setColor(getResources().getColor(R.color.color_96d9f8));
       int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
        mTextPaint = new Paint(Paint.DITHER_FLAG);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(getResources().getColor(R.color.color_63C3FF));

        BitmapFactory.Options spotOptions = new BitmapFactory.Options();
        spotOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),R.mipmap.ic_my_seekbar_spon,spotOptions);
        spotOptions.inSampleSize = 1;
        spotOptions.inJustDecodeBounds = false;
        spot = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_my_seekbar_spon,spotOptions);
        spotWidth = spotOptions.outWidth;
        spotHeight = spotOptions.outHeight;

        BitmapFactory.Options spotOnOptions = new BitmapFactory.Options();
        spotOnOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),R.mipmap.ic_my_seekbar_spot_on,spotOnOptions);
        spotOnOptions.inSampleSize = 1;
        spotOnOptions.inJustDecodeBounds = false;
        spotOn = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_my_seekbar_spot_on,spotOnOptions);
        spotOnWidth = spotOnOptions.outWidth;
        spotOnHeight = spotOnOptions.outHeight;

        textMove = spotHeight*2;
        sectionTitle = new ArrayList<>();
        sectionTitle.add("保守");
        sectionTitle.add("稳健");
        sectionTitle.add("平衡");
        sectionTitle.add("进取");
        sectionTitle.add("积极");

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width =w;
        height = h;
        centerWidth= w/2;
        centerHeight=h/2;
        wholeLineLen = width-2*linePadding;
        usableLineLen = wholeLineLen-2*freeLineLen;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //划线
        canvas.drawLine(linePadding,centerHeight,width-linePadding,centerHeight,mLinePaint);

        if (sectionTitle == null) return;
        perUsableLineLen = usableLineLen/(sectionTitle.size()-1);
        hotArea = perUsableLineLen/2;
        //线上各点
        int section = 0;
        while(section < sectionTitle.size()){
            if (section!=curSection){
                //left top
                canvas.drawBitmap(spot,linePadding+freeLineLen+section*perUsableLineLen-spotWidth/2,centerHeight-spotHeight/2,mLinePaint);
                mTextPaint.setColor(getResources().getColor(R.color.color_6393ad));
            }else {
                canvas.drawBitmap(spotOn,linePadding+freeLineLen+section*perUsableLineLen-spotOnWidth/2,centerHeight-spotOnHeight/2,mLinePaint);
                //当前选中则颜色改变为黄色
                mTextPaint.setColor(getResources().getColor(R.color.color_ff8811));
            }


            if (section==recommendSection){
//                推荐位文字展示在下方
                //计算出文字横坐标偏离度
                float len= mTextPaint.measureText(sectionTitle.get(section)+"(推荐)");
                canvas.drawText(sectionTitle.get(section)+"(推荐)",freeLineLen+linePadding+section*perUsableLineLen-len/2,centerHeight+textMove*3/2,mTextPaint);
            }else {
                //计算出文字横坐标偏离度
                float len= mTextPaint.measureText(sectionTitle.get(section));
                canvas.drawText(sectionTitle.get(section),freeLineLen+linePadding+section*perUsableLineLen-len/2,centerHeight-textMove,mTextPaint);
            }

            section++;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
          super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_back);
                downX = (int) event.getX();
                downY = (int) event.getY();
                responseTouch(downX, downY);
                break;
            case MotionEvent.ACTION_MOVE:
//                thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_back);
                moveX = (int) event.getX();
                moveY = (int) event.getY();
                responseTouch(moveX, moveY);
                break;
            case MotionEvent.ACTION_UP:
//                thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.dot_focus);
                upX = (int) event.getX();
                upY = (int) event.getY();
                responseTouch(upX, upY);
//                responseOnTouch.onTouchResponse(cur_sections);
                break;
        }
        return true;
    }

    private void responseTouch(int x, int y){

        float free = linePadding+freeLineLen;
        if (x <(free+perUsableLineLen/2)){
            curSection = 0;
        }else {
            float actLen = x-free;
            int baseSection = (int) (actLen/perUsableLineLen);
            int surplusLen =(int) (actLen%perUsableLineLen);
            if (surplusLen >(perUsableLineLen/2)){
                curSection = baseSection+1;
            }else {
                curSection = baseSection;
            }
        }

        if (curSection>= sectionTitle.size()){
            curSection = sectionTitle.size()-1;
        }
        invalidate();
    }

    //设置进度
    public void setProgress(int progress){
        curSection = progress;
        recommendSection = progress;
        invalidate();
    }
    public void setSectionTitle(ArrayList<String> sectionTitle) {
        this.sectionTitle = sectionTitle;
    }
}
