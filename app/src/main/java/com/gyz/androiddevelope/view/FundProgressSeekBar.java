package com.gyz.androiddevelope.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 基金进度展示View
 *
 * @version V1.0
 * @author: GuoYazhou
 */
public class FundProgressSeekBar extends View {
    public static final String tag = "FundProgressSeekBar";
    private Paint mLinePaint, mTextPaint, mTextDownPaint, mCirclePaint, mCircleWhitePaint;
    private List<Entire> listData;
    private int width, height, centerWidth, centerHeight;
    private int linePadding, circleDiameter;
    private int viewMargin;
    private int sectionLong;
    private int lineColor, upTxtSize, downTxtSize, upTxtColor, downTxtColor, upTxtMargin, downTxtMargin;

    public FundProgressSeekBar(Context context) {
        this(context, null);
    }

    public FundProgressSeekBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FundProgressSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = getContext().obtainStyledAttributes(attrs,R.styleable.FundProgressSeekBar);
        lineColor = array.getColor(R.styleable.FundProgressSeekBar_lineColor, Color.parseColor("#EF5350"));
        upTxtSize = array.getDimensionPixelSize(R.styleable.FundProgressSeekBar_upTxtSize, 30);
        downTxtSize = array.getDimensionPixelSize(R.styleable.FundProgressSeekBar_downTxtSize, 30);
        upTxtColor = array.getColor(R.styleable.FundProgressSeekBar_upTxtColor, Color.parseColor("#000000"));
        downTxtColor = array.getColor(R.styleable.FundProgressSeekBar_downTxtColor, Color.parseColor("#D7D4DF"));
        upTxtMargin = array.getDimensionPixelOffset(R.styleable.FundProgressSeekBar_upTxtMargin, DensityUtils.dp2px(getContext(), 12));
        downTxtMargin = array.getDimensionPixelOffset(R.styleable.FundProgressSeekBar_downTxtMargin, DensityUtils.dp2px(getContext(), 18));
        array.recycle();
        init();
    }

    private void init() {
        circleDiameter = 25;
        linePadding = 40;
        viewMargin = 10;

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);//锯齿不显示
        mLinePaint.setStrokeWidth(13);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mLinePaint.setColor(lineColor);

        mTextPaint = new Paint(Paint.DITHER_FLAG);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(upTxtSize);
        mTextPaint.setColor(upTxtColor);

        mTextDownPaint = new Paint(Paint.DITHER_FLAG);
        mTextDownPaint.setAntiAlias(true);
        mTextDownPaint.setTextSize(downTxtSize);
        mTextDownPaint.setColor(downTxtColor);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(lineColor);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(6);

        mCircleWhitePaint = new Paint();
        mCircleWhitePaint.setAntiAlias(true);
        mCircleWhitePaint.setColor(getResources().getColor(R.color.colorWhite));
        mCircleWhitePaint.setStyle(Paint.Style.FILL);
        mCircleWhitePaint.setStrokeWidth(8);

        listData = new ArrayList<>();
        Entire entire1 = new Entire("买入提交", "今日15点前");
        Entire entire2 = new Entire("确认份额", "08-03(星期四)");
        Entire entire3 = new Entire("查看盈亏", "08-05(星期五)");
        listData.add(entire1);
        listData.add(entire2);
        listData.add(entire3);
    }

    public void setListData(ArrayList<Entire> list) {
        this.listData = list;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        centerWidth = w / 2;
        centerHeight = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //划线
        canvas.drawLine(viewMargin, centerHeight, width - viewMargin, centerHeight, mLinePaint);

        if (listData == null) return;
        sectionLong = (width - 2 * linePadding) / (listData.size() - 1);

//            画圆点及文字
        for (int i = 0; i < listData.size(); i++) {
            Entire entire = listData.get(i);
            if (i == 0) {
                canvas.drawCircle(viewMargin + linePadding + i * sectionLong, centerHeight, circleDiameter / 2, mCircleWhitePaint);
                canvas.drawCircle(viewMargin + linePadding + i * sectionLong, centerHeight, circleDiameter / 2, mCirclePaint);
//                第一个点  文字需顶格
                canvas.drawText(entire.getUpTxt(), viewMargin, centerHeight - upTxtMargin, mTextPaint);
                canvas.drawText(entire.getDownTxt(), viewMargin, centerHeight + downTxtMargin, mTextDownPaint);

            } else if (i == listData.size() - 1) {
                canvas.drawCircle(width - linePadding - viewMargin, centerHeight, circleDiameter / 2, mCircleWhitePaint);
                canvas.drawCircle(width - linePadding - viewMargin, centerHeight, circleDiameter / 2, mCirclePaint);
//                最后一个点  文字需靠右
                int upTxtLength = (int) mTextPaint.measureText(entire.getUpTxt());
                int downTxtLength = (int) mTextDownPaint.measureText(entire.getDownTxt());

                canvas.drawText(entire.getUpTxt(), width - upTxtLength - viewMargin, centerHeight - upTxtMargin, mTextPaint);
                canvas.drawText(entire.getDownTxt(), width - downTxtLength - viewMargin, centerHeight + downTxtMargin, mTextDownPaint);

            } else {

                canvas.drawCircle(viewMargin + linePadding + i * sectionLong, centerHeight, circleDiameter / 2, mCircleWhitePaint);
                canvas.drawCircle(viewMargin + linePadding + i * sectionLong, centerHeight, circleDiameter / 2, mCirclePaint);
                int upTxtLength = (int) mTextPaint.measureText(entire.getUpTxt());
                int downTxtLength = (int) mTextDownPaint.measureText(entire.getDownTxt());

                canvas.drawText(entire.getUpTxt(), viewMargin + linePadding + i * sectionLong - upTxtLength / 2, centerHeight - upTxtMargin, mTextPaint);
                canvas.drawText(entire.getDownTxt(), viewMargin + linePadding + i * sectionLong - downTxtLength / 2, centerHeight + downTxtMargin, mTextDownPaint);
            }
        }
    }

    class Entire {
        private String upTxt;
        private String downTxt;

        public Entire(String upTxt, String downTxt) {
            this.upTxt = upTxt;
            this.downTxt = downTxt;
        }

        public String getUpTxt() {
            return upTxt;
        }

        public void setUpTxt(String upTxt) {
            this.upTxt = upTxt;
        }

        public String getDownTxt() {
            return downTxt;
        }

        public void setDownTxt(String downTxt) {
            this.downTxt = downTxt;
        }
    }
}
