package com.gyz.androiddevelope.net.volley;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyz.androiddevelope.net.volley.bean.BaiduBean;
import com.gyz.androiddevelope.net.volley.bean.BaseInput;
import com.gyz.androiddevelope.util.LogUtils;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.TestVolleyActivity.java
 * @author: ZhaoHao
 * @date: 2017-02-23 16:08
 */
public class TestVolleyActivity extends VolleyBaseActivity  {
    private static final int i_type = 121;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseInput<Object> input = BaiduBean.Input.buildInput("天");
        httpRequestEntrance(input,new MyHttpRequestListener(),i_type,false,true);
    }

    private class MyHttpRequestListener implements RequestListener {


        @Override
        public void success(int iType, Object response) {
            if (iType ==i_type){
                LogUtils.e("zhaohao","response==="+response.toString());
            }
        }

        @Override
        public void fail(int iType, String error) {

            LogUtils.e("zhaohao","error="+error);
        }
    }
}
