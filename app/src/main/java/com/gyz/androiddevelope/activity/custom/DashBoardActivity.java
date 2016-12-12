package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.DashBoardActivity.java
 * @author: ZhaoHao
 * @date: 2016-08-31 16:17
 */
public class DashBoardActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "DashBoardActivity";

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dash_board);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected String currActivityName() {
        return null;
    }
}
