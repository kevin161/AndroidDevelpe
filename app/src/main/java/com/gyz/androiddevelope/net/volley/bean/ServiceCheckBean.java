package com.gyz.androiddevelope.net.volley.bean;

import com.android.volley.Request;
import com.google.gson.annotations.SerializedName;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.bean.LastProfitBean.java
 * @author: ZhaoHao
 * @date: 2016-11-28 16:26
 */
public class ServiceCheckBean {
    private static final String TAG = "LastProfitBean";

  public  static class Input extends BaseInput<Object>{

        private Input(){
            super("http://10.50.115.99:9089/mobile3/services/check", Request.Method.GET,ServiceCheckBean.class);
        }

        public static BaseInput<Object> buildInput(){
            ServiceCheckBean.Input input = new ServiceCheckBean.Input();
            return input;
        }
    }
}
