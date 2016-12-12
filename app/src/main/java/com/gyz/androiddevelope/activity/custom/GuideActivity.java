package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.fragment.TransformFragment;
import com.gyz.androiddevelope.view.WelcompagerTransformer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @version V1.0   平行空间引导页
 * @FileName: com.gyz.androiddevelope.activity.custom.GuideActivity.java
 * @author: ZhaoHao
 * @date: 2016-08-02 15:00
 */
public class GuideActivity extends BaseActivity {
    private static final String TAG = "GuideActivity";

    @Bind(R.id.vp)
    ViewPager vp;
    private int[] layouts = {
            R.layout.welcome11,
            R.layout.welcome2,
            R.layout.welcome3
    };
    private WelcompagerTransformer transformer;

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        getSwipeBackLayout().setEnableGesture(false);

        WelcomePagerAdapter adapter = new WelcomePagerAdapter(getSupportFragmentManager());
        System.out.println("offset:"+vp.getOffscreenPageLimit());
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(adapter);

        transformer = new WelcompagerTransformer();
        vp.setPageTransformer(true, transformer);

        vp.addOnPageChangeListener(transformer);
    }

    @Override
    protected void loadData() {
    }

    class WelcomePagerAdapter extends FragmentPagerAdapter {

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new TransformFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", layouts[position]);
            bundle.putInt("pageIndex", position);
            f.setArguments(bundle );
            return f;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
