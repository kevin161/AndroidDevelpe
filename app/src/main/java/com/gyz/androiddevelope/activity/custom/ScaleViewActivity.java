package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.widget.TextView;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.view.RulerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @version V1.0
 * @author: ZhaoHao
 */

public class ScaleViewActivity extends BaseActivity  implements RulerView.OnRulerChangeListener {

    @Bind(R.id.tvValue)
    TextView tvValue;
    @Bind(R.id.rulerView)
    RulerView rulerView;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_scale_layout);
        ButterKnife.bind(this);

        rulerView.setOnRulerChangeListener(this);
        rulerView.setCurrLocation(10000);
        rulerView.setMaxValue(160000);
        tvValue.setText(10000 + "");

    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onChanged(int newValue) {
        tvValue.setText(newValue+"");
    }
}
