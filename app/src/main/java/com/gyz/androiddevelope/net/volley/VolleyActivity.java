package com.gyz.androiddevelope.net.volley;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gyz.androiddevelope.net.volley.bean.BaseInput;
import com.gyz.androiddevelope.net.volley.bean.HotSearchBean;
import com.gyz.androiddevelope.net.volley.bean.LastProfitBean;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.VolleyActivity.java
 * @author: ZhaoHao
 * @date: 2016-11-23 16:34
 */
public class VolleyActivity extends VolleyBaseActivity {
    private static final String TAG = "VolleyActivity";
    private Object data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    public int HLC_DETAILS_TYPE = 2929;
    public int HLC_DETAILS_TYPE2 = 29292;

    public void getData() {
        BaseInput<Object> input = HotSearchBean.Input.buildInput("1", "topword", 1, 10);
        httpRequestEntrance(input, new MyHttpRequestListener(), HLC_DETAILS_TYPE, false, false);

        BaseInput<Object> input1 = LastProfitBean.Input.buildInput();
        httpRequestEntrance(input1, new MyHttpRequestListener(), HLC_DETAILS_TYPE2, false, false);

//        Re 方式
//        RxUtil.subscribeAll(new Func1<String, Observable<HotSearchBean>>() {
//            @Override
//            public Observable<HotSearchBean> call(String s) {
//                return ReUtil.getApiManager(AppContants.TNGOU_HTTP).getSearchList("1","topword",1,10);
//            }
//        },new MySubscriber<HotSearchBean>(){
//            @Override
//            public void onNext(HotSearchBean o) {
//                Log.e(TAG,""+o.tianGouList.size());
//            }
//        });

    }


    private class MyHttpRequestListener implements RequestListener {

        @Override
        public void success(int iType, Object response) {
            if (iType == HLC_DETAILS_TYPE) {
                HotSearchBean hotSearchBean = (HotSearchBean) response;
                Log.e(TAG, "" + hotSearchBean.tianGouList.get(1).description);
            }else if (iType == HLC_DETAILS_TYPE2){
                LastProfitBean lastProfitBean = (LastProfitBean) response;
                Log.e(TAG, "" + lastProfitBean.lastProfit.info);
            }
        }

        @Override
        public void fail(int iType, String error) {
            Log.e(TAG, "error==" + iType);
        }
    }
}
