package com.gyz.androiddevelope.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Scroller;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.response_bean.BaseResponTngouBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ry on 2016/9/22.
 * 理财日记水平尺
 */
public class RulerCalendarView extends View {

    //标尺开始位置
    private int currLocation = 0;
    //画线Paint
    private Paint paint, txtPaint;
    //尺子控件总宽度
    private float viewWidth;
    //滚动器
    private Scroller scroller;
    // 手势识别
    private GestureDetector gestureDetector;

    //滚动偏移量
    private int scrollingOffset;
    // 是否在滚动
    private boolean isScrollingPerformed;
    // 最低速度
    private static final int MIN_DELTA_FOR_SCROLLING = 1;
    //最后一次滚动到哪
    private int lastScrollX;
    // 消息
    private final int MESSAGE_SCROLL = 0;

    private OnRulerChangeListener onRulerChangeListener;

    private List<BaseResponTngouBean> datas;
    private int viewHeight;
    /**
     * 一个格的宽度
     **/
    private int scaleWidth;
    private int padding;
    private int txtUpPadding = 30, txtDownPadding = 60;

    public void setOnRulerChangeListener(OnRulerChangeListener onRulerChangeListener) {
        this.onRulerChangeListener = onRulerChangeListener;
    }

    public void setCurrLocation(int currLocation) {
        this.currLocation = currLocation;
    }

    public RulerCalendarView(Context context, AttributeSet attrs) {

        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RulerView);

        if (currLocation == 0) {
            currLocation = typedArray.getDimensionPixelOffset(R.styleable.RulerView_start_location, 5);
        }
        typedArray.recycle();
        //滚动计算器
        scroller = new Scroller(context);
        //手势解析器
        gestureDetector = new GestureDetector(context, gestureListener);
        gestureDetector.setIsLongpressEnabled(false);

        BaseResponTngouBean bean = new BaseResponTngouBean(true, 1);
        BaseResponTngouBean bean2 = new BaseResponTngouBean(false, 2);
        BaseResponTngouBean bean3 = new BaseResponTngouBean(true, 3);
        BaseResponTngouBean bean4 = new BaseResponTngouBean(false, 4);
        BaseResponTngouBean bean5 = new BaseResponTngouBean(true, 5);
        BaseResponTngouBean bean6 = new BaseResponTngouBean(false, 6);
        BaseResponTngouBean bean7 = new BaseResponTngouBean(true, 7);
        BaseResponTngouBean bean8 = new BaseResponTngouBean(false, 8);
        datas = new ArrayList<>();
        datas.add(bean);
        datas.add(bean2);
        datas.add(bean3);
        datas.add(bean4);
        datas.add(bean5);
        datas.add(bean6);
        datas.add(bean7);
        datas.add(bean8);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawBottomLine(canvas);
        drawScale(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);

        padding = (int) (viewWidth / 5);
        scaleWidth = (int) (padding * 1.5f);

    }

    private void drawBottomLine(Canvas canvas) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(20);

        int[] colors = {0xFF9A9BF8, 0xFF9AA2F7, 0xFF65CCD1, 0xFF63D0CD, 0xFF68CBD0, 0xFF999AF6, 0xFF9A9BF8};
        float[] positions = {0, 1f / 6, 2f / 6, 3f / 6, 4f / 6, 5f / 6, 1};
        LinearGradient linearGradient = new LinearGradient(0, viewHeight / 2, viewWidth, viewHeight / 2, colors, positions, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);

        canvas.drawLine(0, viewHeight / 2, viewWidth, viewHeight / 2, paint);
    }


    //刻度
    private void drawScale(Canvas canvas) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        绘制中心点的数据
        paint.setStrokeWidth(27);
        paint.setColor(getResources().getColor(R.color.color_ffffff));

        canvas.drawCircle(viewWidth / 2, viewHeight / 2, 23, paint);

        paint.setStrokeWidth(24);
        paint.setColor(getResources().getColor(R.color.color_ef5350));
        canvas.drawCircle(viewWidth / 2, viewHeight / 2, 16, paint);

        paint.setStrokeWidth(20);
        paint.setColor(getResources().getColor(R.color.color_ffffff));
        canvas.drawCircle(viewWidth / 2, viewHeight / 2, 10, paint);

        BaseResponTngouBean bean = datas.get(currLocation);
        txtPaint = new Paint();
        txtPaint.setColor(getResources().getColor(R.color.color_fec500));
        txtPaint.setTextSize(45);
        txtPaint.setAntiAlias(true);

        int statusLength = (int) txtPaint.measureText("" + bean.status);
        int totalLength = (int) txtPaint.measureText("这是第" + bean.total);

        canvas.drawText(bean.status + "", viewWidth / 2 - statusLength / 2, viewHeight / 2 - txtUpPadding, txtPaint);
        canvas.drawText("这是第" + bean.total, viewWidth / 2 - totalLength / 2, viewHeight / 2 + txtDownPadding, txtPaint);

        //左侧
        if ((currLocation - 1) >= 0) {
            paint.setStrokeWidth(26);
            paint.setColor(getResources().getColor(R.color.transparent));

            canvas.drawCircle(padding, viewHeight / 2, 22, paint);

            paint.setStrokeWidth(23);
            paint.setColor(getResources().getColor(R.color.color_ef5350));
            canvas.drawCircle(padding, viewHeight / 2, 13, paint);

            BaseResponTngouBean bean1 = datas.get(currLocation - 1);

            txtPaint.setColor(getResources().getColor(R.color.color_96d9f8));
            txtPaint.setTextSize(35);
            txtPaint.setAntiAlias(true);

            int statusLength1 = (int) txtPaint.measureText("" + bean1.status);
            int totalLength1 = (int) txtPaint.measureText("这是第" + bean1.total);

            canvas.drawText(bean1.status + "", padding - statusLength1 / 2, viewHeight / 2 - txtUpPadding, txtPaint);
            canvas.drawText("这是第" + bean1.total, padding - totalLength1 / 2, viewHeight / 2 + txtDownPadding, txtPaint);

        }

        //画右侧
        if (datas.size() > (currLocation + 1)) {
            paint.setStrokeWidth(26);
            paint.setColor(getResources().getColor(R.color.transparent));

            canvas.drawCircle(viewWidth - padding, viewHeight / 2, 22, paint);

            paint.setStrokeWidth(23);
            paint.setColor(getResources().getColor(R.color.color_ef5350));
            canvas.drawCircle(viewWidth - padding, viewHeight / 2, 13, paint);

            BaseResponTngouBean bean2 = datas.get(currLocation + 1);

            txtPaint.setColor(getResources().getColor(R.color.color_96d9f8));
            txtPaint.setTextSize(35);
            txtPaint.setAntiAlias(true);

            int statusLength1 = (int) txtPaint.measureText("" + bean2.status);
            int totalLength1 = (int) txtPaint.measureText("这是第" + bean2.total);

            canvas.drawText(bean2.status + "", viewWidth - padding - statusLength1 / 2, viewHeight / 2 - txtUpPadding, txtPaint);
            canvas.drawText("这是第" + bean2.total, viewWidth - padding - totalLength1 / 2, viewHeight / 2 + txtDownPadding, txtPaint);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }


    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {

        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            if (!isScrollingPerformed) {
                isScrollingPerformed = true;
            }
            doScroll((int) -distanceX);
            invalidate();
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            float moveX = e.getX();
            if (moveX > (viewWidth / 2)) {
//                right
                if (moveX - viewWidth / 2 > padding) {
                    if (datas.size() > (getCurrentItem() + 1)) {
                        setCurrentItem(getCurrentItem() + 1);
                    }
                }
            } else {
//                left
                if (viewWidth / 2 - moveX > padding) {
                    if (getCurrentItem() - 1 >= 0) {
                        setCurrentItem(getCurrentItem() - 1);
                    }
                }
            }
            return true;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            lastScrollX = getCurrentItem() * getItemWidth() + scrollingOffset;
            int maxX = getItemsCount()
                    * getItemWidth();
            int minX = 0;
            scroller.fling(lastScrollX, 0, (int) (-velocityX / 1.5), 0, minX, maxX, 0, 0);
            setNextMessage(MESSAGE_SCROLL);
            return true;
        }
    };

    private void setNextMessage(int message) {
        animationHandler.removeMessages(MESSAGE_SCROLL);
        animationHandler.sendEmptyMessage(message);
    }

    // 动画处理
    private Handler animationHandler = new Handler() {
        public void handleMessage(Message msg) {
            scroller.computeScrollOffset();
            int currX = scroller.getCurrX();
            int delta = lastScrollX - currX;
            lastScrollX = currX;
            if (delta != 0) {
                doScroll(delta);
            }
            // 滚动还没有完成，到最后，完成手动
            if (Math.abs(currX - scroller.getFinalX()) < MIN_DELTA_FOR_SCROLLING) {
                scroller.forceFinished(true);
            }
            if (!scroller.isFinished()) {
                animationHandler.sendEmptyMessage(msg.what);
            } else {
                finishScrolling();
            }
        }
    };


    private void finishScrolling() {
        if (isScrollingPerformed) {
            isScrollingPerformed = false;
        }
        invalidate();
    }

    private void doScroll(int delta) {
        //偏移量叠加
        scrollingOffset += delta;
        //总共滚动了多少个Item
        int count = scrollingOffset / getItemWidth();
        //当前位置
        int pos = getCurrentItem() - count;
        //限制滚到范围
        if (isScrollingPerformed) {
            if (pos < 0) {
                count = getCurrentItem();
                pos = 0;
            } else if (pos >= getItemsCount()) {
                count = getCurrentItem() - getItemsCount() + 1;
                pos = getItemsCount() - 1;
            }
        }
        int offset = scrollingOffset;
        //移动了一个Item的距离，就更新页面
        if (pos != getCurrentItem()) {
            setCurrentItem(pos);
        }


        // 重新更新一下偏移量
        scrollingOffset = offset - count * getItemWidth();

    }

    //一个小刻度的大小
    public int getItemWidth() {
        return scaleWidth;
    }

    public int getCurrentItem() {
        return currLocation;
    }

    public int getItemsCount() {
        return datas.size();
    }

    public void setCurrentItem(int index) {
        scrollingOffset = 0;
        currLocation = index;
        invalidate();
        if (onRulerChangeListener != null) {
            onRulerChangeListener.onChanged(currLocation);
        }
    }

    public int getCurrLocation() {
        return currLocation;
    }

    public interface OnRulerChangeListener {
        void onChanged(int newValue);
    }
}
