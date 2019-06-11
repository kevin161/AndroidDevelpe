package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.view.ScrollTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @version V3.0
 * @FileName: com.gyz.androiddevelope.activity.custom.ScrollTextViewActivity.java
 * @author: GYZ
 * asia161@qq.com
 * @date: 2019-03-20 10:56
 */

public class ScrollTextViewActivity extends BaseToolbarNormalActivity {
    @Bind(R.id.scrollTextView)
    ScrollTextView scrollTextView;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_scroll_textview);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

        List<ScrollItem> demographicsList = new ArrayList<>();

        ScrollItem scrollItem = new ScrollItem();
        scrollItem.setText("1111111111111111111今日测试股票 上市");
        scrollItem.setDrawable(R.drawable.avft);

        ScrollItem scrollItem1 = new ScrollItem();
        scrollItem1.setText("222222222222222222222222222222222222222222222222222222222222 中国人保 可申购今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购");
        scrollItem1.setDrawable(R.drawable.avft_active);

        ScrollItem scrollItem2 = new ScrollItem();
        scrollItem2.setText("333333333333333333333333国人保 可申购");
        scrollItem2.setDrawable(R.drawable.ic_favorite_accent_24dp);

        ScrollItem scrollItem3 = new ScrollItem();
        scrollItem3.setText("444444444444444444444444444444444444444443234134");
        scrollItem3.setDrawable(R.drawable.avft);

        ScrollItem scrollItem4 = new ScrollItem();
        scrollItem4.setText("555555555555555555555555555555555555555555555513412341人保 可申购今日科伦药业 中国人保 可申购");
        scrollItem4.setDrawable(R.drawable.avft_active);

        ScrollItem scrollItem5 = new ScrollItem();
        scrollItem5.setText("666666666666666666666666666666666666666666666245药业 中国人保 可申购");
        scrollItem5.setDrawable(R.drawable.ic_favorite_accent_24dp);

        demographicsList.add(scrollItem);
        demographicsList.add(scrollItem1);
        demographicsList.add(scrollItem2);
        demographicsList.add(scrollItem3);
        demographicsList.add(scrollItem4);
        demographicsList.add(scrollItem5);

        scrollTextView.setList(demographicsList);
        scrollTextView.startScroll();
    }

    @Override
    protected void onDestroy() {
        scrollTextView.stopScroll();
        super.onDestroy();
    }

    @Override
    protected String currActivityName() {
        return "小控件测试页面";
    }


    public class ScrollItem{
        private String text;
        private int drawable;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getDrawable() {
            return drawable;
        }

        public void setDrawable(int drawable) {
            this.drawable = drawable;
        }
    }
}
