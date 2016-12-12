package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.Win10ProgressActivity.java
 * @author: ZhaoHao
 * @date: 2016-09-20 09:33
 */
public class Win10ProgressActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "Win10ProgressActivity";

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_win_loading);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected String currActivityName() {
        return "win10加载进度条";
    }
}
