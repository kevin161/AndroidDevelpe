package com.gyz.androiddevelope.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.util.DeviceUtils.java
 * @author: ZhaoHao
 * @date: 2016-12-22 13:34
 */
public class DeviceUtils {
    private static final String TAG = "DeviceUtils";

    public static String getImei(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }

    public static String getVersionName(Context context){
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),0).versionName;

        } catch (PackageManager.NameNotFoundException e) {
            verName = "";
        }
        return verName;
    }

    public static int getVersionCode(Context context){
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }

    public static String getMacAddress(Context context){

        String mac = "";

        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        mac = wm.getConnectionInfo().getMacAddress();

        return mac;
    }

    public static String getAndroidId(Context context){
        String androidId = "";
        androidId = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        return androidId;

    }

}
