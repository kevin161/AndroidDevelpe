package com.gyz.androiddevelope.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.response_bean.LatestNewsBean;
import com.gyz.androiddevelope.util.ImageUtils;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.util.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: guoyazhou
 * @date: 2016-03-11 15:34
 */
public class MarqueeView extends FrameLayout implements View.OnClickListener {

    private static final String TAG = "MarqueeView";
    private List<LatestNewsBean.TopStory> topStoriesEntities;
    private List<View> views;
    public Context context;
    private ViewPager vp;
    private boolean isAutoPlay;
    private int currentItem;
    private List<ImageView> iv_dots;
    private Handler handler = new Handler();
    private OnItemClickListener mItemClickListener;
    private MyTask myTask;


    public MarqueeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    private void initView() {
        views = new ArrayList<View>();
        iv_dots = new ArrayList<ImageView>();
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(Context context) {
        this(context, null);
    }

    public void setTopEntities(List<LatestNewsBean.TopStory> topEntities) {
        if (topStoriesEntities != null) {
            topStoriesEntities.clear();
        }
        this.topStoriesEntities = topEntities;
        reset();
    }

    private void reset() {
        views.clear();
        if (myTask != null) {
            handler.removeCallbacks(myTask);
            vp.clearOnPageChangeListeners();
        }
        initUI();
    }

    private void initUI() {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(
                R.layout.kanner_layout, this, true);
        vp = (ViewPager) view.findViewById(R.id.vp);
        LinearLayout ll_dot = (LinearLayout) view.findViewById(R.id.ll_dot);
        ll_dot.removeAllViews();
        iv_dots.clear();

        int len = topStoriesEntities.size();
        for (int i = 0; i < len; i++) {
            ImageView iv_dot = new ImageView(context.getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            ll_dot.addView(iv_dot, params);
            iv_dots.add(iv_dot);
        }

        for (int i = 0; i <= len + 1; i++) {
            View fm = LayoutInflater.from(context.getApplicationContext()).inflate(
                    R.layout.kanner_content_layout, null);
            ImageView iv = (ImageView) fm.findViewById(R.id.iv_title);
            TextView tv_title = (TextView) fm.findViewById(R.id.tv_title);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (i == 0) {
                ImageUtils.loadImageByPicasso(context.getApplicationContext(), topStoriesEntities.get(len - 1).getImage(), iv);
//                Picasso.with(context).load(topStoriesEntities.get(len - 1).getImage()).into(iv);
                tv_title.setText(topStoriesEntities.get(len - 1).getTitle());

            } else if (i == len + 1) {
                ImageUtils.loadImageByPicasso(context.getApplicationContext(), topStoriesEntities.get(0).getImage(), iv);
//                Picasso.with(context).load(topStoriesEntities.get(0).getImage()).into(iv);
                tv_title.setText(topStoriesEntities.get(0).getTitle());

            } else {
                ImageUtils.loadImageByPicasso(context.getApplicationContext(), topStoriesEntities.get(i - 1).getImage(), iv);
//                Picasso.with(context).load(topStoriesEntities.get(i-1).getImage()).into(iv);
                tv_title.setText(topStoriesEntities.get(i - 1).getTitle());
            }
            fm.setOnClickListener(this);
            views.add(fm);
        }

        vp.setAdapter(new MyPagerAdapter());
        vp.setFocusable(true);
        vp.setCurrentItem(1);
        currentItem = 1;
        vp.addOnPageChangeListener(new MyOnPageChangeListener());
        startPlay();
    }

    private void startPlay() {
        isAutoPlay = true;

        myTask = new MyTask(this);
        handler.postDelayed(myTask, 3000);

    }


    static class MyTask implements Runnable {
        private final WeakReference<MarqueeView> marqueeViewWeakReference;

        public MyTask(MarqueeView mMarqueeView) {
            this.marqueeViewWeakReference = new WeakReference<MarqueeView>(mMarqueeView);
        }


        @Override
        public void run() {
            MarqueeView marqueeView = marqueeViewWeakReference.get();
            if (marqueeView != null) {
                if (marqueeView.isAutoPlay) {
                    marqueeView.currentItem = marqueeView.currentItem % (marqueeView.topStoriesEntities.size() + 1) + 1;
                    if (marqueeView.currentItem == 1) {
                        marqueeView.vp.setCurrentItem(marqueeView.currentItem, false);
                        marqueeView.handler.post(this);
                    } else {
                        marqueeView.vp.setCurrentItem(marqueeView.currentItem);
                        marqueeView.handler.postDelayed(this, 5000);
                    }
                } else {
                    marqueeView.handler.postDelayed(this, 5000);
                }
            }
        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:
                    isAutoPlay = false;
                    break;
                case 2:
                    isAutoPlay = true;
                    break;
                case 0:
                    if (vp.getCurrentItem() == 0) {
                        vp.setCurrentItem(topStoriesEntities.size(), false);
                    } else if (vp.getCurrentItem() == topStoriesEntities.size() + 1) {
                        vp.setCurrentItem(1, false);
                    }
                    currentItem = vp.getCurrentItem();
                    isAutoPlay = true;
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < iv_dots.size(); i++) {
                if (i == arg0 - 1) {
                    iv_dots.get(i).setImageResource(R.mipmap.dot_focus);
                    LogUtils.e("onPageSelected....i" + i);
                } else {
                    iv_dots.get(i).setImageResource(R.mipmap.dot_blur);
                }
            }

        }

    }

    public void removeCallback() {
        handler.removeCallbacks(myTask);
    }


    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void click(LatestNewsBean.TopStory entity);
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            LatestNewsBean.TopStory entity = topStoriesEntities.get(vp.getCurrentItem() - 1);
            mItemClickListener.click(entity);
        }
    }
}
