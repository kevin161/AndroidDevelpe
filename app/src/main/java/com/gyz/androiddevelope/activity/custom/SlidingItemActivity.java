package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.SlidingItemActivity.java
 * @author: ZhaoHao
 * @date: 2016-08-19 14:01
 */
public class SlidingItemActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "SlidingItemActivity";

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_slid_item);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected String currActivityName() {
        return null;
    }
}
