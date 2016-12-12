package com.gyz.androiddevelope.view.disscrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gyz.androiddevelope.R;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.disscrollview.DiscrollViewContent.java
 * @author: ZhaoHao
 * @date: 2016-07-26 14:21
 */
public class DiscrollViewContent extends LinearLayout {
    private static final String TAG = "DiscrollViewContent";

    public DiscrollViewContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //得到xml里面传过来的参数
        return new MyLayoutParams(getContext(), attrs);

    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        //从child里面拿到我自定义的属性，传到discrollvableView里面
        MyLayoutParams p = (MyLayoutParams) params;
        //判断该view是否穿了自定义属性值，不是就不需要执行动画，不包一层FrameLayout
        if (!isDiscrollvable(p)) {
            super.addView(child, index, params);
        } else {
            //在addView里面插一脚，往child外面包裹一层FrameLayout
            DiscrollvableView discrollvableView = new DiscrollvableView(getContext());
            discrollvableView.setmDiscrollveAlpha(p.mDiscrollveAlpha);
            discrollvableView.setmDisCrollveTranslation(p.mDisCrollveTranslation);
            discrollvableView.setmDiscrollveScaleX(p.mDiscrollveScaleX);
            discrollvableView.setmDiscrollveScaleY(p.mDiscrollveScaleY);
            discrollvableView.setmDiscrollveFromBgColor(p.mDiscrollveFromBgColor);
            discrollvableView.setmDiscrollveToBgColor(p.mDiscrollveToBgColor);

            discrollvableView.addView(child);
            super.addView(discrollvableView,index,params);
        }
    }

    private boolean isDiscrollvable(MyLayoutParams p) {
        // 判断是否是自定义属性
        return p.mDiscrollveAlpha ||
                p.mDiscrollveScaleX ||
                p.mDiscrollveScaleY ||
                p.mDisCrollveTranslation != -1 ||
                (p.mDiscrollveFromBgColor != -1 &&
                        p.mDiscrollveToBgColor != -1);
    }

    public static class MyLayoutParams extends LinearLayout.LayoutParams {
        public int mDiscrollveFromBgColor;//背景颜色变化开始值
        public int mDiscrollveToBgColor;//背景颜色变化结束值
        public boolean mDiscrollveAlpha;//是否需要透明度动画
        public int mDisCrollveTranslation;//平移值
        public boolean mDiscrollveScaleX;//是否需要x轴方向缩放
        public boolean mDiscrollveScaleY;//是否需要y轴方向缩放

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            //从child里拿到自定义的属性值
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.DiscrollView_LayoutParams);

            mDiscrollveAlpha = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_alpha, false);
            mDiscrollveScaleX = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleX, false);
            mDiscrollveScaleY = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleY, false);
            mDisCrollveTranslation = a.getInt(R.styleable.DiscrollView_LayoutParams_discrollve_translation, -1);
            mDiscrollveFromBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_fromBgColor, -1);
            mDiscrollveToBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_toBgColor, -1);
            a.recycle();
        }
    }
}
