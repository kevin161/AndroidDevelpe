package com.gyz.androiddevelope.activity.custom;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.view.ExperienceProgress;

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

    protected void initVariables() {

    }

    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_circle);
        ButterKnife.bind(this);
    }

    protected void loadData() {

        lvProgress.setLvValue(1839);


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
