package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.QQSlidingActivity.java
 * @author: ZhaoHao
 * @date: 2016-08-19 13:20
 */
public class QQSlidingActivity extends BaseActivity {
    private static final String TAG = "QQSlidingActivity";

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.qq_menu_activity);
        //禁用左划手势
        getSwipeBackLayout().setEnableGesture(false);
    }

    @Override
    protected void loadData() {

    }

}
