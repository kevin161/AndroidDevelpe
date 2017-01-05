package com.gyz.androiddevelope.net.volley.bean;

import android.os.Build;

import com.android.volley.Request;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.bean.HtfLoginBean.java
 * @author: ZhaoHao
 * @date: 2016-12-22 11:37
 */
public class HtfLoginBean {
    private static final String TAG = "HtfLoginBean";

    public static class Input extends BaseInput<Object>{
        private String certNum;
        private String password;

        private String certType;
        private String deviceId;
        private String device;
        private String os;
        private String version;
        private String versionCode;
        private String access;
        private String carrier;
        private String imei;
        private String macAddress;
        private String androidId;
        private String uniqueId;

        private Input() {

            super("http://10.50.115.99:9089/mobile3/services/account/login", Request.Method.POST, HtfLoginBean.class);
//            super("https://app.99fund.com:443/mobileEC/services/account/login", Request.Method.POST, HtfLoginBean.class);
        }

        public static BaseInput<Object> buildInput(String name, String encode,String imei,String version,
                                                   String versionCode,String mac,String uniqueId) {
            Input input = new Input();
            input.certNum = name;
            input.password = encode;

            input.certType = "0";
            input.deviceId = imei;
            input.device = Build.MODEL;
            input.os = "android"+Build.VERSION.RELEASE+"xiaomi";
            input.version = "3.57";
            input.versionCode = "35";
            input.access = "WiFi接入";
            input.carrier = "WiFi";
            input.imei = imei;
            input.macAddress =mac ;
            input.androidId = "f269703311169d64";
            input.uniqueId = uniqueId;

             return input;
        }
    }

}
