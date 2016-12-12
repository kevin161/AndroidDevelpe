package com.gyz.androiddevelope.service;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.listener.OnProgressResponseListener;
import com.gyz.androiddevelope.net.retrofit.ReUtil;
import com.gyz.androiddevelope.request_bean.DownloadInfo;
import com.gyz.androiddevelope.util.FileUtil;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.util.NotificationUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * DownloadService 后台下载服务模块
 * 具有两个线程
 * DownloadThread线性负责单线程下载
 * notifyThread线程负责轮询接收下载进度 发出通知 生命周期依赖下载进程
 */
public class DownloadService extends IntentService {

    private static final String TAG = "DownloadService";
    private static final String KEYURL = "KeyUrl";
    private static final String KEYTYPE = "KeyType";

    private static final int MSG_START = 0;
    private static final int MSG_LOADING = 1;
    private static final int MSG_COMPLETE = 2;


    private long mDelayedTime = 1000;
    private NotifyHandler notifyHandler;
    private HandlerThread mHandlerThread;

    private static DownloadInfo mDownloadInfo;
    private Map<Integer, DownloadInfo> mMap = new HashMap<>();
    private static final OnProgressResponseListener mListener = new OnProgressResponseListener() {

        @Override
        public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
            mDownloadInfo.mProcess = (int) (bytesRead * 100 / contentLength);
        }
    };


    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            LogUtils.d(Thread.currentThread().toString() + " mProcess=" + mDownloadInfo.mProcess);
            Message message = notifyHandler.obtainMessage();
            message.what = MSG_LOADING;
            message.obj = mDownloadInfo;
            notifyHandler.sendMessage(message);
            notifyHandler.postDelayed(this, mDelayedTime);
        }
    };

    public DownloadService() {
        super(TAG);
    }


    public static void launch(Activity activity, String url, String type) {
        Intent intent = new Intent(activity, DownloadService.class);
        intent.putExtra(KEYURL, url);
        intent.putExtra(KEYTYPE, type);
        activity.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandlerThread = new HandlerThread("notifyThread");
        mHandlerThread.start();
        notifyHandler = new NotifyHandler(mHandlerThread.getLooper());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //线程不阻塞的回调 能够得到每次的发起操作回调 而不会阻塞
        String url = intent.getStringExtra(KEYURL);
        String type = intent.getStringExtra(KEYTYPE);
        String fileName = System.currentTimeMillis() + FileUtil.getPinsType(type);
        int mWorkId = url.hashCode();
        //根据参数  构造对象
        DownloadInfo info = new DownloadInfo(fileName, type, mWorkId, url, DownloadInfo.StateStart);
        //根据url的hashcode 放入
        mMap.put(mWorkId, info);
        sendNotifyMessage(info);

        //处理队列中的消息 顺序调用 处理完一个再处理下一个
        //这里是线程阻塞方法 刚好可以好判断当前任务
        //从map中取出构造的好的 对象 开始任务
        mDownloadInfo = mMap.get(url.hashCode());
        actionDownload(url, mDownloadInfo, mListener);
    }

    /**
     * 阻塞当前线程的下载方法
     *
     * @param url
     * @param mDownloadInfo
     * @param mListener
     */
    private void actionDownload(String url, final DownloadInfo mDownloadInfo, OnProgressResponseListener mListener) {
        ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpDownImage(url).map(new Func1<ResponseBody, File>() {
            @Override
            public File call(ResponseBody responseBody) {
                //构建文件目录
                File file = FileUtil.getDirsFile();
                return FileUtil.writeResponseBodyToDisk(file, responseBody, mDownloadInfo.fileName);
            }
        }).filter(new Func1<File, Boolean>() {
            @Override
            public Boolean call(File file) {
                return file != null;
            }
        }).subscribe(new Subscriber<File>() {

            @Override
            public void onStart() {
                super.onStart();
                //开始下载 线程开始轮询
                notifyHandler.postDelayed(mRunnable, mDelayedTime);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(File file) {
                notifyHandler.removeCallbacks(mRunnable);
                sendFileNotifyMessage(file, mDownloadInfo);
            }
        });
    }

    private void sendFileNotifyMessage(File file, DownloadInfo mDownloadInfo) {

        mDownloadInfo.mFile = file;
        mDownloadInfo.mState = "下载完成";
        Message message =notifyHandler.obtainMessage();
        message.what = MSG_COMPLETE;
        message.obj = mDownloadInfo;
        notifyHandler.sendMessage(message);

    }

    private void sendNotifyMessage(DownloadInfo info) {
        Message message = notifyHandler.obtainMessage();
        message.what = MSG_START;
        message.obj = info;
        notifyHandler.sendMessage(message);
    }

    //// TODO: 2016/5/18 0018 目前存在内存泄露状态 因为OnProgressResponseListener在Service中实例化 然后弃用 但是okhttp的生命周期比较长
    //哪为什么不在 Handler中实例化 然后和线程一起弃用？
    class NotifyHandler extends Handler {

        //下载操作不频繁 可以当做类变量 使用时候再创建
        private NotificationManager mNotificationManager;

        public NotifyHandler(Looper looper) {
            super(looper);
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }

        @Override
        public void handleMessage(Message msg) {
            DownloadInfo downloadInfo = (DownloadInfo) msg.obj;
            int downId = downloadInfo.mWorkId;
            Notification notification;
            switch (msg.what) {
                case MSG_START:

                    notification = NotificationUtils.showNotification(
                            getApplication(),
                            downloadInfo.fileName,
                            downloadInfo.mState);
                    break;

                case MSG_LOADING:

                    notification = NotificationUtils.showProcessNotification
                            (getApplication(), downloadInfo.fileName, downloadInfo.mProcess);
                    break;

                case MSG_COMPLETE:
                    notification = NotificationUtils.showIntentNotification(
                            getApplication(),
                            downloadInfo.mFile,
                            downloadInfo.mMediaType,
                            downloadInfo.fileName,
                            downloadInfo.mState);
                    break;
                default:
                    notification = NotificationUtils.showNotification(getApplication(),"错误","无法处理接受的消息");
                    break;
            }
            mNotificationManager.notify(downId, notification);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
    }
}
