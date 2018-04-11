package com.gyz.androiddevelope.activity.custom;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.response_bean.BaseResponTngouBean;
import com.gyz.androiddevelope.util.ToastUtil;
import com.gyz.androiddevelope.view.ExperienceProgress;
import com.gyz.androiddevelope.view.ScrollChooseView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: guoyazhou
 * @date: 2016-02-03 14:22
 */
public class CircleActivity extends BaseActivity {
    private static final String TAG = "CircleActivity";
    @Bind(R.id.lvProgress)
    ExperienceProgress lvProgress;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.scrollChooseView)
    ScrollChooseView scrollChooseView;

    protected void initVariables() {

    }

    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_circle);
        ButterKnife.bind(this);
    }

    protected void loadData() {

        lvProgress.setLvValue(1839);


        BaseResponTngouBean bean = new BaseResponTngouBean(true, 1);
        BaseResponTngouBean bean2 = new BaseResponTngouBean(false, 2);
        BaseResponTngouBean bean3 = new BaseResponTngouBean(true, 3);
        BaseResponTngouBean bean4 = new BaseResponTngouBean(false, 4);
        BaseResponTngouBean bean5 = new BaseResponTngouBean(true, 5);
        BaseResponTngouBean bean6 = new BaseResponTngouBean(false, 6);
        BaseResponTngouBean bean7 = new BaseResponTngouBean(true, 7);
        BaseResponTngouBean bean8 = new BaseResponTngouBean(false, 8);
        BaseResponTngouBean bean9 = new BaseResponTngouBean(false, 9);
        BaseResponTngouBean bean10 = new BaseResponTngouBean(false, 10);
        BaseResponTngouBean bean11 = new BaseResponTngouBean(false, 11);
        ArrayList<BaseResponTngouBean> datas = new ArrayList<>();
        datas.add(bean);
        datas.add(bean2);
        datas.add(bean3);
        datas.add(bean4);
        datas.add(bean5);
        datas.add(bean6);
        datas.add(bean7);
        datas.add(bean8);
        datas.add(bean9);
        datas.add(bean10);
        datas.add(bean11);

        scrollChooseView.setDatas(datas);
        scrollChooseView.setCurrentPosition(5);
        scrollChooseView.setOnScrollEndListener(new ScrollChooseView.OnScrollEndListener() {
            @Override
            public void currentPosition(int position) {
//                ToastUtil.showShort(getApplicationContext(),"position==="+position);
            }
        });
    }

    boolean isChoose;

    @OnClick(R.id.imageView)
    public void onClick(View view) {
        imageView = (ImageView) view;
        if (isChoose) {
            //已选择
            imageView.setImageResource(R.drawable.animator_star_un_svg);
        } else {
            //未选择
            imageView.setImageResource(R.drawable.animator_star_svg);
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }


        isChoose = !isChoose;
    }
}
