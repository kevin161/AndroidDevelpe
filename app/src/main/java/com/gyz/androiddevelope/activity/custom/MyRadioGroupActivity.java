package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.widget.RadioButton;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.util.ToastUtil;
import com.gyz.androiddevelope.view.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.MyRadioGroupActivity.java
 * @author: ZhaoHao
 * @date: 2017-01-09 09:59
 */
public class MyRadioGroupActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "MyRadioGroupActivity";
    @Bind(R.id.btnRadioGroup1)
    RadioButton btnRadioGroup1;
    @Bind(R.id.btnRadioGroup2)
    RadioButton btnRadioGroup2;
    @Bind(R.id.btnRadioGroup3)
    RadioButton btnRadioGroup3;
    @Bind(R.id.btnRadioGroup4)
    RadioButton btnRadioGroup4;
    @Bind(R.id.btnRadioGroup5)
    RadioButton btnRadioGroup5;
    @Bind(R.id.btnRadioGroup6)
    RadioButton btnRadioGroup6;
    @Bind(R.id.btnRadioGroup7)
    RadioButton btnRadioGroup7;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;


    RadioButton radioDate;
    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.my_radio_group);
        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioDate !=null){
                    radioDate.setTextColor(getResources().getColor(R.color.color_615d71));
                }
                radioDate = (RadioButton) MyRadioGroupActivity.this.findViewById(checkedId);
                radioDate.setTextColor(getResources().getColor(R.color.color_d81B60));
                ToastUtil.showShort(MyRadioGroupActivity.this,radioDate.getText().toString());
                LogUtils.e("zhaohao",radioDate.getText().toString());
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected String currActivityName() {
        return null;
    }

}
