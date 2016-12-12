package com.gyz.androiddevelope.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

/**
 * 图片
 *
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.RevealDrawable.java
 * @author: ZhaoHao
 * @date: 2016-12-07 14:11
 */
public class RevealDrawable extends Drawable {
    private static final String TAG = "RevealDrawable";
    private final Rect mTmpRect = new Rect();
    private Drawable mUnselectedDrawable;
    private Drawable mSelectedDrawable;
    private int mOrientation;
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;

    public RevealDrawable(Drawable selectedDrawable, Drawable unselectedDrawable, int orientation) {
        this.mSelectedDrawable = selectedDrawable;
        this.mUnselectedDrawable = unselectedDrawable;
        this.mOrientation = orientation;
    }

    @Override
    public void draw(Canvas canvas) {
        //绘制
        int level = getLevel();
        //from 0 (minimum) to 10000
        //三个区间
        //右边区间和左边区间--设置成灰色
        if (level == 10000 || level == 0) {
            mUnselectedDrawable.draw(canvas);
        } else if (level == 5000) {
            //全部选中  代表彩色
            mSelectedDrawable.draw(canvas);
        } else {
            //混合效果
            /**
             * 将画板切割成两块-左边和右边
             */
            Rect r =mTmpRect;
            Rect bounds = getBounds();
            {
//                1.绘制灰色部分
//                比例
//                0~5000~10000
                float ratio = level/5000f-1f;
                int w = bounds.width();
                int h = bounds.height();
                if (mOrientation == HORIZONTAL) {
                    w = (int) (w*Math.abs(ratio));
                }
                if (mOrientation ==VERTICAL){
                    h = (int) (h*Math.abs(ratio));
                }

                int gravity = ratio<0? Gravity.LEFT:Gravity.RIGHT;
                //从一个已有的bounds矩形边界范围中抠出一个矩形r
                Gravity.apply(
                        gravity,//从左边还是右边开始抠
                        w,//目标矩形的宽
                        h, //目标矩形的高
                        bounds, //被抠出来的rect
                        r);//目标rect

                canvas.save();//保存画布
                canvas.clipRect(r);//切割
                mUnselectedDrawable.draw(canvas);//画
                canvas.restore();//恢复之前保存的画布
            }
            {
                //2.再绘制彩色部分
                //level 0~5000~10000
                //比例
                float ratio = (level/5000f) - 1f;
                int w = bounds.width();
                if(mOrientation==HORIZONTAL){
                    w -= (int) (w*Math.abs(ratio));
                }
                int h = bounds.height();
                if(mOrientation==VERTICAL){
                    h -= (int) (h*Math.abs(ratio));
                }

                int gravity = ratio < 0 ? Gravity.RIGHT : Gravity.LEFT;
                //从一个已有的bounds矩形边界范围中抠出一个矩形r
                Gravity.apply(
                        gravity,//从左边还是右边开始抠
                        w,//目标矩形的宽
                        h, //目标矩形的高
                        bounds, //被抠出来的rect
                        r);//目标rect

                canvas.save();//保存画布
                canvas.clipRect(r);//切割
                mSelectedDrawable.draw(canvas);//画
                canvas.restore();//恢复之前保存的画布
            }
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
//        定好两个Drawable图片的宽高---边界bounds
        mUnselectedDrawable.setBounds(bounds);
        mSelectedDrawable.setBounds(bounds);
    }

    @Override
    public int getIntrinsicHeight() {
        //得到Drawable的实际高度
        return Math.max(mSelectedDrawable.getIntrinsicHeight(), mUnselectedDrawable.getIntrinsicHeight());
    }

    @Override
    public int getIntrinsicWidth() {
        //得到Drawable的实际宽度
        return Math.max(mSelectedDrawable.getIntrinsicWidth(), mUnselectedDrawable.getIntrinsicWidth());
    }

    @Override
    protected boolean onLevelChange(int level) {
        // 当设置level的时候回调---提醒自己重新绘制
        invalidateSelf();
        return true;
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
