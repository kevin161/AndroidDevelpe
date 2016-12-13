package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.view.View;
import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.util.hotfix.FixDexUtils;
import com.gyz.androiddevelope.util.hotfix.MyTestClass;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.HotFixActivity.java
 * @author: ZhaoHao
 * @date: 2016-12-13 10:07
 */
public class HotFixActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "HotFixActivity";

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_hotfix);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected String currActivityName() {
        return "热修复（类加载）";
    }


    @OnClick({R.id.btn_test, R.id.btnFix})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                MyTestClass myTestClass = new MyTestClass();
                myTestClass.testFix(getBaseContext());
                break;
            case R.id.btnFix:
                FixDexUtils.fixBug(getApplicationContext());
                break;
        }
    }


}
