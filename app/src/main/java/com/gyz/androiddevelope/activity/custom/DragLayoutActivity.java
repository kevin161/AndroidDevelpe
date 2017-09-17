package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.fragment.HealthInfoListFragment;
import com.gyz.androiddevelope.fragment.TestFragment;
import com.gyz.androiddevelope.fragment.TransformFragment;
import com.gyz.androiddevelope.view.DragLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.DragLayoutActivity.java
 * @author: ZhaoHao
 * @date: 2017-09-04 13:47
 */

public class DragLayoutActivity extends BaseToolbarNormalActivity {
    @Bind(R.id.draglayout)
    DragLayout draglayout;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_drag_layout);
        ButterKnife.bind(this);

        HealthInfoListFragment fragment1 = new HealthInfoListFragment();
        TestFragment fragment2 = new TestFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.first, fragment1)
                .add(R.id.second, fragment2)
                .commit();

        DragLayout.ShowNextPageNotifier nextIntf = new DragLayout.ShowNextPageNotifier() {
            @Override
            public void onDragNext() {
//              fragment3.initView();
            }
        };
        draglayout.setNextPageListener(nextIntf);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected String currActivityName() {
        return null;
    }

}
