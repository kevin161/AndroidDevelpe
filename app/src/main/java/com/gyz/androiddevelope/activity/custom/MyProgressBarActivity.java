package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.view.CustomSeekbar;
import com.gyz.androiddevelope.view.HorizontalProgressBar;
import com.gyz.androiddevelope.view.LoveLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.MyProgressBarActivity.java
 * @author: ZhaoHao
 * @date: 2016-05-18 16:09
 */
public class MyProgressBarActivity extends BaseActivity {
    private static final String TAG = "MyProgressBarActivity";
    @Bind(R.id.progress)
    HorizontalProgressBar progress;
    @Bind(R.id.myCustomSeekBar)
    CustomSeekbar myCustomSeekBar;
    @Bind(R.id.loveLayout)
    LoveLayout loveLayout;

    Timer timer;
    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        setContentView(R.layout.activity_my_progress_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        MyHandler myHandler = new MyHandler(this);
        myHandler.sendEmptyMessage(MSG_UPDATE);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                loveLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        loveLayout.addHeart();
                    }
                });
            }
        }, 100, 200);
        //===============myCustomSeekBar 设置数据========================================
          ArrayList<String> volume_sections = new ArrayList<String>();
        volume_sections.add("静音");
        volume_sections.add("低");
        volume_sections.add("中");
        volume_sections.add("高");
        myCustomSeekBar.initData(volume_sections);
        myCustomSeekBar.setProgress(2);
        //activity实现了下面的接口ResponseOnTouch，每次touch会回调onTouchResponse
        myCustomSeekBar.setResponseOnTouch(new CustomSeekbar.ResponseOnTouch() {
            @Override
            public void onTouchResponse(int currentSelect) {
                LogUtils.e("onTouchResponse....."+currentSelect);
            }
        });

    }

    private static final int MSG_UPDATE = 23;
    //规避handler内存泄露
    private static class MyHandler extends Handler {
    //设置软引用保存，当内存一发生GC的时候就会回收。
    private WeakReference<MyProgressBarActivity> myProgressBarActivity;

    public MyHandler(MyProgressBarActivity progressBarActivity){
        this.myProgressBarActivity = new WeakReference<MyProgressBarActivity>((progressBarActivity));
    }
        @Override
        public void handleMessage(Message msg) {
            MyProgressBarActivity progressBarActivity = myProgressBarActivity.get();
            int pro = progressBarActivity.progress.getProgress();
            progressBarActivity.progress.setProgress(++pro);
            if (pro > 100) {
                removeMessages(MSG_UPDATE);
            } else {
                sendEmptyMessageDelayed(MSG_UPDATE, 100);
            }
        }
    }


    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}

