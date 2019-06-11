package com.gyz.androiddevelope.view;

/**
 * @version V3.0
 * @FileName: com.gyz.androiddevelope.view.ScrollTextView.java
 * @author: GYZ
 * asia161@qq.com
 * @date: 2019-03-20 10:48
 */


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.activity.custom.ScrollTextViewActivity;

import java.util.List;

/**
 * 上下滚动的 textView
 */
public class ScrollTextView extends LinearLayout {

    private int textColor, textSize;

    private TextView mBannerTV1, mBannerTV2;
    private ImageView image1, image2;
    private LinearLayout layout1, layout2;
    private Handler handler;
    private boolean isShow = false;
    private int startY1, endY1, startY2, endY2;
    private Runnable runnable;
    private List<ScrollTextViewActivity.ScrollItem> list;
    private int position = 0;
    private int offsetY = 300;
    private boolean hasPostRunnable = false;

    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScrollTextView);
        textSize = typedArray.getDimensionPixelSize(R.styleable.ScrollTextView_textSize, 24);
        textColor = typedArray.getColor(R.styleable.ScrollTextView_textColor, Color.parseColor("#d8d8d8"));

        typedArray.recycle();

        View view = LayoutInflater.from(context).inflate(R.layout.widget_scroll_text_layout, this);
        mBannerTV1 = (TextView) view.findViewById(R.id.tv_banner1);
        mBannerTV2 = (TextView) view.findViewById(R.id.tv_banner2);
        image1 = (ImageView) view.findViewById(R.id.image1);
        image2 = (ImageView) view.findViewById(R.id.image2);
        layout1 = (LinearLayout) view.findViewById(R.id.layout1);
        layout2 = (LinearLayout) view.findViewById(R.id.layout2);

        mBannerTV1.setTextColor(textColor);
        mBannerTV1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mBannerTV2.setTextColor(textColor);
        mBannerTV2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                isShow = !isShow;
                if (position == list.size() - 1) {
                    position = 0;
                }

                if (isShow) {

                    mBannerTV1.setText(list.get(position++).getText());
                    mBannerTV2.setText(list.get(position).getText());

                    image1.setImageResource(list.get(position-1).getDrawable());
                    image2.setImageResource(list.get(position).getDrawable());

                } else {
                    mBannerTV2.setText(list.get(position++).getText());
                    mBannerTV1.setText(list.get(position).getText());

                    image2.setImageResource(list.get(position-1).getDrawable());
                    image1.setImageResource(list.get(position).getDrawable());


                }

                startY1 = isShow ? 0 : offsetY;
                endY1 = isShow ? -offsetY : 0;
                ObjectAnimator.ofFloat(layout1, "translationY", startY1, endY1).setDuration(300).start();

                startY2 = isShow ? offsetY : 0;
                endY2 = isShow ? 0 : -offsetY;
                ObjectAnimator.ofFloat(layout2, "translationY", startY2, endY2).setDuration(300).start();

                handler.postDelayed(runnable, 3000);
            }
        };
    }

    public List<ScrollTextViewActivity.ScrollItem> getList() {
        return list;
    }

    public void setList(List<ScrollTextViewActivity.ScrollItem> list) {
        this.list = list;

        //处理最后一条数据切换到第一条数据 太快的问题
        if (list.size() > 1) {
            list.add(list.get(0));
        }
    }

    public void startScroll() {
        mBannerTV1.setText(list.get(0).getText());
        image1.setImageResource(list.get(0).getDrawable());

        if (list.size() > 1) {
            if (!hasPostRunnable) {
                hasPostRunnable = true;
                //处理第一次进入 第一条数据切换第二条 太快的问题
                handler.postDelayed(runnable, 3000);
            }
        } else {
            //只有一条数据不进行滚动
            hasPostRunnable = false;
//            mBannerTV1.setText(list.get(0));
        }
    }

    public void stopScroll() {
        handler.removeCallbacks(runnable);
        hasPostRunnable = false;
    }


}