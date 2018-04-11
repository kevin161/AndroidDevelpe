package com.gyz.androiddevelope.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.response_bean.BaseResponTngouBean;

import java.util.List;


/**
 * 可横向滑动的事件线
 * Created by lsp on 2017/2/24.
 */
public class ScrollChooseView extends View {
    //    private String[] titles = null;
    private List<BaseResponTngouBean> datas;
    private Paint paint, pointPaint;
    private Rect timeBound, txtBound;
    private int downX;

    private Scroller mScroller;
    private int lastScrollX = 0;
    private boolean isScroll = false;

    private boolean isClick = false;
    private OnScrollEndListener onScrollEndListener;
//    private int picIds[] = null;

    public ScrollChooseView(Context context) {
        this(context, null);
    }

    public ScrollChooseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        timeBound = new Rect();
        txtBound = new Rect();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setTextSize(sp2px(getContext(), 10));
        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mScroller = new Scroller(context);

    }

//    public void setPicIds(int[] picIds) {
//        this.picIds = picIds;
//    }

    public void setDatas(List<BaseResponTngouBean> data) {
        this.datas = data;
        invalidate();
    }

    public void setOnScrollEndListener(OnScrollEndListener onScrollEndListener) {
        this.onScrollEndListener = onScrollEndListener;
    }


    private int getCurrentPosition() {
        int position = 1;

        if (getScrollX() > getMeasuredWidth() / 6 * 1 * -3 && getScrollX() <= getMeasuredWidth() / 6 * 1 * -1) {
            position = 0;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * -1 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 1) {
            position = 1;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * 1 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 3) {
            position = 2;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * 3 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 5) {
            position = 3;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * 5 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 7) {
            position = 4;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * 7 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 9) {
            position = 5;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * 9 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 11) {
            position = 6;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * 11 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 13) {
            position = 7;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * 13 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 15) {
            position = 8;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * 15 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 17) {
            position = 9;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * 17 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 19) {
            position = 10;
        } else if (getScrollX() > getMeasuredWidth() / 6 * 1 * 19 && getScrollX() <= getMeasuredWidth() / 6 * 1 * 21) {
            position = 11;
        }

        return position;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (datas.size() <= 1) {
            return super.onTouchEvent(event);
        }
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isClick = true;
                downX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                isClick = false;
                int offset = x - downX;
                if (getScrollX() < -getMeasuredWidth() / 3.0 || getScrollX() > getMeasuredWidth() / 3.0 * datas.size() - getMeasuredWidth() + getMeasuredWidth() / 3.0) {
                    return super.onTouchEvent(event);
                } else {
                    scrollX(lastScrollX - offset, true);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isClick) {
                    return true;
                }
                if (getScrollX() < -getMeasuredWidth() / 3.0) {
                    scrollX((int) (-getMeasuredWidth() / 3.0), true);
                }
                if (getScrollX() > getMeasuredWidth() / 3.0 * datas.size() - getMeasuredWidth() + getMeasuredWidth() / 3.0) {
                    scrollX((int) (getMeasuredWidth() / 3.0 * datas.size() - getMeasuredWidth() + getMeasuredWidth() / 3.0), true);
                }

                scrollX(getMeasuredWidth() / 3 * (getCurrentPosition() - 1), false);

                break;
        }
        return true;
    }

    private void scrollX(int endX, boolean b) {
        if (b) {
            scrollTo(endX, 0);
        } else {
            isScroll = true;
            lastScrollX = endX;
            smoothScrollTo(endX, 0);
        }
    }

    public void smoothScrollTo(int destX, int destY) {
        mScroller.startScroll(getScrollX(), 0, destX - getScrollX(), 0, 200);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        } else {
            if (isScroll) {
                setScrollX(lastScrollX);
                isScroll = false;
                if (onScrollEndListener != null) {
                    onScrollEndListener.currentPosition(getCurrentPosition());
                }
            }
        }
    }

    int width, height, centerX, centerY;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widhtMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        setMeasuredDimension(widhtMode == MeasureSpec.EXACTLY ? widthSize : widthSize, heightMode == MeasureSpec.EXACTLY ? heightSize : dp2px(getContext(), 100));
        width = getMeasuredWidth();
        centerX = width / 2;
        height = getMeasuredHeight();
        centerY = height / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBottomLine(canvas);
        if (datas == null) {
            return;
        }
        drawText(canvas);

    }

    private void drawBottomLine(Canvas canvas) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(20);

        int[] colors = {0xFF2894FF, 0xFF6DB3ff, 0xFF96D9F8, 0xFF96D9F8, 0xFF6DB3ff, 0xFF2894FF};
        float[] positions = {0, 1f / 5, 2f / 5, 3f / 5, 4f / 5, 1};
        LinearGradient linearGradient = new LinearGradient(0 + getScrollX(), centerY, width + getScrollX(), centerY, colors, positions, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);

        canvas.drawLine(0 + getScrollX(), centerY, width + getScrollX(), centerY, paint);
    }

    private void drawText(Canvas canvas) {
        for (int i = 0; i < datas.size(); i++) {
            BaseResponTngouBean bean = datas.get(i);
            if (getCurrentPosition() == i) {
//                正中心的内容
                paint.setTextSize(sp2px(getContext(), 20));
//                上部日期
                paint.getTextBounds(bean.status + "", 0, (bean.status + "").length(), timeBound);
                canvas.drawText(bean.status + "", width / 6 * (2 * i + 1) - (timeBound.width() / 2),
                        centerY - timeBound.height(), paint);
//                下部文字
                paint.getTextBounds(bean.total + "", 0, (bean.total + "").length(), txtBound);
                canvas.drawText(bean.total + "", width / 6 * (2 * i + 1) - (txtBound.width() / 2),
                        centerY + txtBound.height() * 2f, paint);
//                中间点
                //        绘制中心点的数据
                pointPaint.setStrokeWidth(27);
                pointPaint.setColor(getResources().getColor(R.color.color_ffffff));
                canvas.drawCircle(width / 6 * (2 * i + 1), centerY, 23, pointPaint);

                pointPaint.setStrokeWidth(24);
                pointPaint.setColor(getResources().getColor(R.color.color_ef5350));
                canvas.drawCircle(width / 6 * (2 * i + 1), centerY, 16, pointPaint);

                pointPaint.setStrokeWidth(20);
                pointPaint.setColor(getResources().getColor(R.color.color_ffffff));
                canvas.drawCircle(width / 6 * (2 * i + 1), centerX, 10, pointPaint);

            } else {
                paint.setTextSize(sp2px(getContext(), 10));
                paint.getTextBounds(bean.status + "", 0, (bean.status + "").length(), timeBound);
                canvas.drawText(bean.status + "", width / 6 * (2 * i + 1) - (timeBound.width() / 2),
                        centerY - timeBound.height() * 2, paint);

                paint.getTextBounds(bean.total + "", 0, (bean.total + "").length(), txtBound);
                canvas.drawText(bean.total + "", width / 6 * (2 * i + 1) - (txtBound.width() / 2),
                        centerY + txtBound.height() * 3, paint);


                pointPaint.setStrokeWidth(26);
                pointPaint.setColor(getResources().getColor(R.color.transparent));
                canvas.drawCircle(width / 6 * (2 * i + 1), centerY, 22, pointPaint);

                pointPaint.setStrokeWidth(23);
                pointPaint.setColor(getResources().getColor(R.color.color_ef5350));
                canvas.drawCircle(width / 6 * (2 * i + 1), centerY, 13, pointPaint);


            }
//            for (int i = 0; i < titles.length; i++) {
//            if (getCurrentPosition() == i) {
//                paint.setTextSize(sp2px(getContext(), 20));
//                paint.getTextBounds(titles[i], 0, titles[i].length(), timeBound);
//                canvas.drawText(titles[i], getMeasuredWidth() / 6 * (2 * i + 1) - (timeBound.width() / 2),
//                        (getMeasuredHeight() / 2) + timeBound.height() / 2, paint);
//                paint.setStrokeWidth(3);
//                canvas.drawLine(getMeasuredWidth() / 6 * (2 * i + 1) - (timeBound.width() / 2) - 10, (getMeasuredHeight() / 2) + timeBound.height(),
//                        getMeasuredWidth() / 6 * (2 * i + 1) + (timeBound.width() / 2) + 10, (getMeasuredHeight() / 2) + timeBound.height(), paint);
//            } else {
//                paint.setTextSize(sp2px(getContext(), 10));
//                paint.getTextBounds(titles[i], 0, titles[i].length(), timeBound);
//                canvas.drawText(titles[i], getMeasuredWidth() / 6 * (2 * i + 1) - (timeBound.width() / 2), (getMeasuredHeight() / 2) + timeBound.height() / 2, paint);
//            }
//            if (getCurrentPosition() >= picIds.length) {
//                return;
//            }
//            setBackgroundResource(picIds[getCurrentPosition()]);
        }
    }

    public interface OnScrollEndListener {
        void currentPosition(int position);
    }


    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }
}

