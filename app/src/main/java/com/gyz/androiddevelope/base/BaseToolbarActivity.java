package com.gyz.androiddevelope.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.view.AppBar;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.base.BaseToolbarNormalActivity.java
 * @author: ZhaoHao
 * @date: 2016-05-26 15:42
 */
public abstract class BaseToolbarActivity extends AppCompatActivity implements SwipeBackActivityBase {
    private static final String TAG = "BaseToolbarNormalActivity";

    private SwipeBackActivityHelper mHelper;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        super.setContentView(R.layout.activity_base_toolbar);
        initVariables();
        initToolbar();
        initViews(savedInstanceState);
        loadData();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });

    }

    protected void finishActivity() {
        finish();
    }

    protected void backActivity() {
        finish();
    }

    /*
        初始化传入参数
         */
    protected abstract void initVariables();

    /*
        初始化界面及view
    	 */
    protected abstract void initViews(Bundle savedInstanceState);

    /*
    网络请求
     */
    protected abstract void loadData();

    /**
     * 描述当前页面的title--便于友盟统计
     */
    protected abstract String currActivityName();


    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        FrameLayout rootLayout = (FrameLayout) findViewById(R.id.contain_home);
        if (rootLayout != null) {
            rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            initToolbar();
        }
    }

    //======SwipeBack==begin===========================
    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
//======SwipeBack==end===========================

}
