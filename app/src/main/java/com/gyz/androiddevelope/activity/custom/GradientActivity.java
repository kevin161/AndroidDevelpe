package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;

import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.view.MyGradientView;
import com.gyz.androiddevelope.view.ZoomImageView;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.GradientActivity.java
 * @author: ZhaoHao
 * @date: 2016-08-24 11:28
 */
public class GradientActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "GradientActivity";

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ZoomImageView zoomImageView = new ZoomImageView(this);
        MyGradientView myGradientView = new MyGradientView(this);
        setContentView(myGradientView);
//        setContentView(R.layout.activity_touch_view);
        //禁用左划手势
        getSwipeBackLayout().setEnableGesture(false);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected String currActivityName() {
        return null;
    }
}
