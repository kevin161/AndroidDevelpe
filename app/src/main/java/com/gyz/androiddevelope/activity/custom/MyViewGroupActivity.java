package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.MyViewGroupActivity.java
 * @author: ZhaoHao
 * @date: 2016-12-31 14:41
 */
public class MyViewGroupActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "MyViewGroupActivity";

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_view_group);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected String currActivityName() {
        return null;
    }
}
