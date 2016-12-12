package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.view.FlickerProgressBar;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.FlickerProgressActivity.java
 * @author: ZhaoHao
 * @date: 2016-09-29 14:06
 */
public class FlickerProgressActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "FlickerProgressActivity";
    @Bind(R.id.flickerProgressBar)
    FlickerProgressBar flickerProgressBar;

     static MyHandler myHandler;

    @Override
    protected void initVariables() {

    }

    private static class MyHandler extends Handler {
        private WeakReference<FlickerProgressActivity> activityWeakReference;

        public MyHandler(FlickerProgressActivity flickerProgressActivity) {
            this.activityWeakReference = new WeakReference<FlickerProgressActivity>(flickerProgressActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FlickerProgressActivity flickerProgressActivity = activityWeakReference.get();
            flickerProgressActivity.flickerProgressBar.setProgress(msg.arg1);
            if (msg.arg1 == 100) {
                flickerProgressActivity.flickerProgressBar.finishLoad();
            }
        }

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_flicker_progress);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        myHandler = new MyHandler(this);
        downLoad();
    }

    @Override
    protected String currActivityName() {
        return null;
    }

    private static void downLoad() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(200);
                        Message message = myHandler.obtainMessage();
                        message.arg1 = i + 1;
                        myHandler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
