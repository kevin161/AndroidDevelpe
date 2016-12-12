package com.gyz.androiddevelope.activity.custom;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.view.GallaryHorizonalScrollView;
import com.gyz.androiddevelope.view.RevealDrawable;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.ImageCrapActivity.java
 * @author: ZhaoHao
 * @date: 2016-12-07 14:07
 */
public class ImageCrapActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "ImageCrapActivity";
    private ImageView iv;
    private int[] mImgIds = new int[] { // 7个
            R.drawable.avft, R.drawable.box_stack, R.drawable.bubble_frame,
            R.drawable.bubbles, R.drawable.bullseye, R.drawable.circle_filled,
            R.drawable.circle_outline, R.drawable.avft, R.drawable.box_stack,
            R.drawable.bubble_frame, R.drawable.bubbles, R.drawable.bullseye,
            R.drawable.circle_filled, R.drawable.circle_outline };
    private int[] mImgIds_active = new int[] { R.drawable.avft_active,
            R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active,
            R.drawable.circle_filled_active, R.drawable.circle_outline_active,
            R.drawable.avft_active, R.drawable.box_stack_active,
            R.drawable.bubble_frame_active, R.drawable.bubbles_active,
            R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active };

    public Drawable[] revealDrawables;
    protected int level = 5000;
    private GallaryHorizonalScrollView hzv;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_image_crap);
    }

    @Override
    protected void loadData() {
        revealDrawables = new Drawable[mImgIds.length];
        for (int i = 0; i < mImgIds.length; i++) {
            RevealDrawable rd = new RevealDrawable(getResources().getDrawable(
                    mImgIds[i]), getResources().getDrawable(mImgIds_active[i]),
                    RevealDrawable.HORIZONTAL);
            revealDrawables[i] = rd;
        }
        hzv = (GallaryHorizonalScrollView) findViewById(R.id.hsv);
        hzv.addImageViews(revealDrawables);
    }

    @Override
    protected String currActivityName() {
        return "图片切割";
    }
}
