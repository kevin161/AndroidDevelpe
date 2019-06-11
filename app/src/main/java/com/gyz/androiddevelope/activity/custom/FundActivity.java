package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 投资收益率测算界面
 *
 * @version V3.0
 * @FileName: com.gyz.androiddevelope.activity.custom.FundActivity.java
 * @author: GYZ
 * asia161@qq.com
 * @date: 2018-08-30 10:21
 */

public class FundActivity extends BaseToolbarNormalActivity {
    @Bind(R.id.edtQm)
    EditText edtQm;
    @Bind(R.id.edtXq)
    EditText edtXq;
    @Bind(R.id.btnCalculate)
    Button btnCalculate;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fund);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    private double calculate(double qmProfit, double xqProfit) {
        double qmSum = 1500 * 150;
        double xqSum = 8800 * 30;

        double sum = qmSum + xqSum;

        double result = (Math.abs(qmProfit + xqProfit)) / sum * 100;
        return result;
    }

    @Override
    protected String currActivityName() {
        return "收益率测算";
    }


    @OnClick(R.id.btnCalculate)
    public void onViewClicked() {
        String qm = edtQm.getText().toString();
        String xq = edtXq.getText().toString();
        double result = calculate( Double.parseDouble(qm),Double.parseDouble(xq));
        ToastUtil.showLong(getApplicationContext(),result+"%");
    }
}
