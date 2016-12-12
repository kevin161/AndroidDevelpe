package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.view.searchview.Control;
import com.gyz.androiddevelope.view.searchview.MySearchView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.SearchViewActivity.java
 * @author: ZhaoHao
 * @date: 2016-12-07 14:09
 */
public class SearchViewActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "SearchViewActivity";
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.sv)
    MySearchView searchView;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_view);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        searchView.setController(new Control());
    }

    @Override
    protected String currActivityName() {
        return "搜索";
    }


    @OnClick({R.id.button, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
//                start
                searchView.startAnimation();
                break;
            case R.id.button2:
//                reset
                searchView.resetAnimation();
                break;
        }
    }

}
