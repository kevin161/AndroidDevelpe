package com.gyz.androiddevelope.net.volley;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import static com.android.volley.toolbox.Volley.newRequestQueue;


/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.VolleyInit.java
 * @author: ZhaoHao
 * @date: 2016-11-23 13:37
 */
public class VolleyInit {
    private static final String TAG = "VolleyInit";

    /**
     * 设备号，每台设备都有唯一的id
     */
    private static String deviceId;
    private static Application application;
    public static RequestQueue requestQueue;

    /**
     * 应用版本名
     */
    private static String versionName;

    /**
     * 应用版本号
     */
    private static int versionCode;

    private VolleyInit() {
    }

    public static void volleyInit(Application application) {
        if (VolleyInit.application == null) {
            synchronized (VolleyInit.class) {
                if (VolleyInit.application != null) {
                    return;
                }

                VolleyInit.application = application;

                try {
                    PackageManager packageManager = application
                            .getPackageManager();
                    if (packageManager != null) {
                        PackageInfo packageInfo = null;
                        packageInfo = packageManager
                                .getPackageInfo(application.getPackageName(),
                                        PackageManager.GET_SIGNATURES);
                        versionName = packageInfo.versionName;
                        versionCode = packageInfo.versionCode;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
//                初始化网络模块
                NetworkInit(application);
            }
        }
    }

    private static void NetworkInit(Application application) {
        if (requestQueue != null) {
            requestQueue.stop();
        }
        //这里也可以自己重写，指定缓存目录，监听目录变化等操作
        requestQueue = newRequestQueue(application);
    }


    /**
     * 获取设备ID，每台设备都有自己唯一的设备号
     */
    public static String getDeviceId() {
        return deviceId;
    }


    /**
     * 获取应用的版本名称
     */
    public static String getVersionName() {
        return versionName;
    }

    /**
     * 获取应用版本号
     */
    public static int getVersionCode() {
        return versionCode;
    }

    public static Application getApplication() {
        return application;
    }

    /**
     * 异步请求
     */
    public static void request(Request request) {
        requestQueue.add(request);
    }


}
