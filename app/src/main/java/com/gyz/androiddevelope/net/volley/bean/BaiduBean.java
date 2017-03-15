package com.gyz.androiddevelope.net.volley.bean;

import com.android.volley.Request;
import com.google.gson.annotations.SerializedName;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.bean.BaiduBean.java
 * @author: ZhaoHao
 * @date: 2017-02-23 16:12
 */
public class BaiduBean {

    public static class Input extends BaseInput<Object>{

        private String wd;

        private Input() {
            super("https://www.baidu.com/s", Request.Method.GET, BaiduBean.class);
        }

        public static BaseInput<Object> buildInput(String keyWord){

            BaiduBean.Input input = new BaiduBean.Input();
            input.wd = keyWord;
            return input;
        }
    }
    public int requestCode;
}
