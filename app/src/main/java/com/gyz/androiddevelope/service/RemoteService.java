package com.gyz.androiddevelope.service;


import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.gyz.androiddevelope.IMyAidlInterface;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.util.ToastUtil;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.service.LocalService.java
 * @author: ZhaoHao
 * @date: 2016-05-20 08:47
 */
public class RemoteService extends Service {
    private static final String TAG = "RemoteService";

    private MyBind myBind;
    private MyConn myConn;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return myBind;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myBind = new MyBind();
        if (myConn == null) {
            myConn = new MyConn();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        RemoteService.this.bindService(new Intent( RemoteService.this, LocalService.class), myConn, Context.BIND_IMPORTANT);
        PendingIntent contentIntent = PendingIntent.getService(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("360")
                .setContentIntent(contentIntent)
                .setContentTitle("我是360，我怕谁!")
                .setAutoCancel(true)
                .setContentText("hehehe")
                .setWhen( System.currentTimeMillis());

        //把service设置为前台运行，避免手机系统自动杀掉改服务。
//        startForeground(startId, builder.build());
        return START_STICKY;
    }


    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ToastUtil.show(getBaseContext(), "与本地连接建立关联", Toast.LENGTH_SHORT);
            LogUtils.e(TAG, "与本地连接建立关联");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ToastUtil.show(getBaseContext(), "本地连接断开", Toast.LENGTH_SHORT);
            RemoteService.this.startService(new Intent(RemoteService.this, LocalService.class));
            RemoteService.this.bindService(new Intent(RemoteService.this, LocalService.class), myConn, Context.BIND_IMPORTANT);
        }
    }
    class MyBind extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getServiceName() throws RemoteException {
            return "RemoteService";
        }
    }

}
