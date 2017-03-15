package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.widget.Button;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.view.PieGraphView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.PieChartActivity.java
 * @author: ZhaoHao
 * @date: 2017-03-15 10:47
 */
public class PieChartActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "PieChartActivity";
    @Bind(R.id.pieChartView)
    PieGraphView pieChartView;
    @Bind(R.id.btnChange)
    Button btnChange;
    private int[] colors = new int[]{0xfff9bdbb, 0xfff36c60, 0xffce93d8, 0xffafbfff, 0xffb2dfdb, 0xff00acc1, 0xffcddc39, 0xff259b24};
    private int colorIndex;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pie_chart);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {



        // 造例子数据
        PieGraphView.ItemGroup[] groups = new PieGraphView.ItemGroup[3];

        for (int i = 0; i < groups.length; i++) {
            PieGraphView.ItemGroup itemGroup = new PieGraphView.ItemGroup();
            groups[i] = itemGroup;
            itemGroup.id = "zu@" + i;
            PieGraphView.Item[] items = new PieGraphView.Item[4];
            itemGroup.items = items;

            for (int j = 0; j < items.length; j++) {
                PieGraphView.Item item = new PieGraphView.Item();
                item.id = "it@" + j;
                item.value = j * 24 + 24;
                item.color = colors[colorIndex++ % colors.length];
                items[j] = item;
            }
        }

        pieChartView.setNeedAnimation(false);
        pieChartView.setNeedCenterPoint(false);
        pieChartView.setNeedTouchMode(false);
        pieChartView.setTotalAssets("总资产(元)");
        pieChartView.setTotalMoneyStr("992,999.98");
        pieChartView.setNumColor(getResources().getColor(R.color.color_cf3035));
        pieChartView.setData(groups);
    }

    @Override
    protected String currActivityName() {
        return "饼状图";
    }


    @OnClick(R.id.btnChange)
    public void onClick() {
        //改变饼状图内数据
        PieGraphView.ItemGroup group = new PieGraphView.ItemGroup();
        group.id = "id1";

        PieGraphView.Item item1 = new PieGraphView.Item();
        item1.color = 0xfff9bdbb;
        item1.id = "item1";
        item1.value = 10;
        PieGraphView.Item item2 = new PieGraphView.Item();
        item2.color = 0xfff36c60;
        item2.id = "item2";
        item2.value = 15;
        PieGraphView.Item item3 = new PieGraphView.Item();
        item3.color = 0xffce93d8;
        item3.id = "item3";
        item3.value = 20;
        PieGraphView.Item item4 = new PieGraphView.Item();
        item4.color = 0xffafbfff;
        item4.id = "item4";
        item4.value = 25;
        PieGraphView.Item item5 = new PieGraphView.Item();
        item5.color = 0xffb2dfdb;
        item5.id = "item5";
        item5.value = 30;

        group.items=new PieGraphView.Item[5];
        group.items[0] = item1;
        group.items[1] = item2;
        group.items[2] = item3;
        group.items[3] = item4;
        group.items[4] = item5;

        PieGraphView.ItemGroup[] groups = new PieGraphView.ItemGroup[1];
        groups[0]=group;
        pieChartView.setData(groups);
    }
}
