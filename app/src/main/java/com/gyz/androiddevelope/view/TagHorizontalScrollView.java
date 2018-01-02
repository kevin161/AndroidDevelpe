package com.gyz.androiddevelope.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 热门标签  水平滚动自定义View
 *
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.MyViewGroupActivity.java
 * @author: GYZ
 * @date: 2016-12-31 14:23
 */
public class TagHorizontalScrollView extends HorizontalScrollView {

    private int textSize, textColor, txtPaddingLeft, txtPaddingRight, txtPaddingTop, txtPaddingBottom,
            txtMarginLeft, txtMarginRight, txtMarginTop, txtMarginBottom, backGround;

    public TagHorizontalScrollView(Context context) {
        this(context, null);
    }

    public TagHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TagHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TagHorizontalScrollView);

        textSize = array.getDimensionPixelSize(R.styleable.TagHorizontalScrollView_textSize, 12);
        textColor = array.getColor(R.styleable.TagHorizontalScrollView_textColor, Color.parseColor("#40c4ff"));
        txtPaddingLeft = array.getDimensionPixelOffset(R.styleable.TagHorizontalScrollView_text_paddingLeft, DensityUtils.dp2px(getContext(), 15));
        txtPaddingRight = array.getDimensionPixelOffset(R.styleable.TagHorizontalScrollView_text_paddingRight, DensityUtils.dp2px(getContext(), 15));
        txtPaddingTop = array.getDimensionPixelOffset(R.styleable.TagHorizontalScrollView_text_paddingTob, DensityUtils.dp2px(getContext(), 8));
        txtPaddingBottom = array.getDimensionPixelOffset(R.styleable.TagHorizontalScrollView_text_paddingBottom, DensityUtils.dp2px(getContext(), 8));
        txtMarginLeft = array.getDimensionPixelOffset(R.styleable.TagHorizontalScrollView_text_marginLeft, DensityUtils.dp2px(getContext(), 15));
        txtMarginRight = array.getDimensionPixelOffset(R.styleable.TagHorizontalScrollView_text_marginRight, DensityUtils.dp2px(getContext(), 15));
        txtMarginTop = array.getDimensionPixelOffset(R.styleable.TagHorizontalScrollView_text_marginTop, DensityUtils.dp2px(getContext(), 8));
        txtMarginBottom = array.getDimensionPixelOffset(R.styleable.TagHorizontalScrollView_text_marginBottom, DensityUtils.dp2px(getContext(), 8));
        backGround = array.getResourceId(R.styleable.TagHorizontalScrollView_backGround, R.drawable.shape_loading_now_page_bg);

        array.recycle();

        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
    }

    public void addTag(List<String> list, final TagClickListener tagClickListener) {
        this.removeAllViews();

        LinearLayout linearLayout = new LinearLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        this.addView(linearLayout);
        for (final String str : list) {

            final TextView textView = new TextView(getContext());
            textView.setText(str);
            textView.setPadding(txtPaddingLeft, txtPaddingTop, txtPaddingRight, txtPaddingBottom);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            textView.setTextColor(textColor);
            textView.setBackgroundResource(backGround);
            textView.setClickable(true);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tagClickListener!=null){
                        tagClickListener.onTagClickListener(str);
                    }
                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(txtMarginLeft, txtMarginTop, txtMarginRight, txtMarginBottom);
            textView.setLayoutParams(params);

            linearLayout.addView(textView);
        }
    }

   public interface TagClickListener{
         void onTagClickListener(String tagName);
    }

}
