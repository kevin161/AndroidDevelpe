package com.gyz.androiddevelope.net.volley.bean;

import com.android.volley.Request;
import com.google.gson.annotations.SerializedName;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.bean.LastProfitBean.java
 * @author: ZhaoHao
 * @date: 2016-11-28 16:26
 */
public class LastProfitBean {
    private static final String TAG = "LastProfitBean";

  public  static class Input extends BaseInput<Object>{

        private Input(){
//            super("http://172.16.8.166:6081/mobileEC/services/last_profit", Request.Method.GET,LastProfitBean.class);
            super("https://app.99fund.com:443/mobileEC/services/last_profit", Request.Method.GET,LastProfitBean.class);
        }

        public static BaseInput<Object> buildInput(){
            LastProfitBean.Input input = new LastProfitBean.Input();
            return input;
        }

    }

    public int returnCode;
    public String returnMsg;
    public String successMsg;
    @SerializedName(value = "body")
    public LastProfit lastProfit;


    public class LastProfit{
        public String incomeDay;
        public String title;
        public String incomeInfo;
        public String date;
        public String info;

    }
}
