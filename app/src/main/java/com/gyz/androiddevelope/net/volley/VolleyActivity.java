package com.gyz.androiddevelope.net.volley;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.net.retrofit.MySubscriber;
import com.gyz.androiddevelope.net.retrofit.ReUtil;
import com.gyz.androiddevelope.net.retrofit.RxUtil;
import com.gyz.androiddevelope.net.volley.bean.BaseInput;
import com.gyz.androiddevelope.net.volley.bean.HotSearchBean;
import com.gyz.androiddevelope.net.volley.bean.HtfLoginBean;
import com.gyz.androiddevelope.net.volley.bean.LastProfitBean;
import com.gyz.androiddevelope.net.volley.bean.ServiceCheckBean;
import com.gyz.androiddevelope.util.DeviceUtils;
import com.gyz.androiddevelope.util.EncryptUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Func1;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.VolleyActivity.java
 * @author: ZhaoHao
 * @date: 2016-11-23 16:34
 */
public class VolleyActivity extends VolleyBaseActivity {
    private static final String TAG = "VolleyActivity";
//    @Bind(R.id.edtName)
//    EditText edtName;
//    @Bind(R.id.edtPwd)
//    EditText edtPwd;
    private Object data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_group);
//        setContentView(R.layout.activity_volley_test);
//        ButterKnife.bind(this);
        getData();
    }

    public int HLC_DETAILS_TYPE = 2929;
    public int HTF_LOGIN_TYPE = 2000;
    public int HTF_SERVICE_CHECK_TYPE = 2001;
    public int HLC_LOGIN_TYPE = 2002;
    public int HLC_DETAILS_TYPE2 = 29292;

    public void getData() {
//        BaseInput<Object> input = HotSearchBean.Input.buildInput("1", "topword", 1, 10);
//        httpRequestEntrance(input, new MyHttpRequestListener(), HLC_DETAILS_TYPE, true, true);

//        BaseInput<Object> input1 = LastProfitBean.Input.buildInput();
//        httpRequestEntrance(input1, new MyHttpRequestListener(), HLC_DETAILS_TYPE2, true, true);
//        checkservice
//        BaseInput<Object> input1 = ServiceCheckBean.Input.buildInput();
//        httpRequestEntrance(input1, new MyHttpRequestListener(), HTF_SERVICE_CHECK_TYPE, true, true);



    }

//    @OnClick(R.id.btnHtf)
//    public void onClick() {
//
////        BaseInput<Object> input = HtfLoginBean.Input.buildInput(edtName.getText().toString(),
////                EncryptUtils.encode(edtPwd.getText().toString())
////                , DeviceUtils.getImei(getApplicationContext()),
////                DeviceUtils.getVersionName(getApplicationContext()),
////                DeviceUtils.getVersionCode(getApplicationContext())+"",
////                DeviceUtils.getMacAddress(getApplicationContext()),
////                DeviceUtils.getAndroidId(getApplicationContext()));
////        httpRequestEntrance(input, new MyHttpRequestListener(), HTF_LOGIN_TYPE, true, true);
//
//
//        RxUtil.subscribeAll(new Func1<String, Observable<HtfLoginBean>>() {
//            @Override
//            public Observable<HtfLoginBean> call(String s) {
//                return ReUtil.getApiManager(AppContants.HTF_HTTP).htfLogin(edtName.getText().toString(),
//                        EncryptUtils.encode(edtPwd.getText().toString()),"0",DeviceUtils.getImei(getApplicationContext()),
//                        Build.MODEL, "android"+Build.VERSION.RELEASE+"xiaomi","3.57","35", "WiFi接入","WiFi",DeviceUtils.getImei(getApplicationContext()),
//                        DeviceUtils.getMacAddress(getApplicationContext()),"f269703311169d64",DeviceUtils.getAndroidId(getApplicationContext()));
//            }
//        },new MySubscriber<HtfLoginBean>(){
//            @Override
//            public void onNext(HtfLoginBean o) {
//                super.onNext(o);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//            }
//
//            @Override
//            public void onCompleted() {
//                super.onCompleted();
//            }
//        });
//
//    }


    private class MyHttpRequestListener implements RequestListener {

        @Override
        public void success(int iType, Object response) {
            if (iType == HLC_DETAILS_TYPE) {
                HotSearchBean hotSearchBean = (HotSearchBean) response;
                Log.e(TAG, "" + hotSearchBean.tianGouList.get(1).description);
            } else if (iType == HLC_DETAILS_TYPE2) {
                LastProfitBean lastProfitBean = (LastProfitBean) response;
                Log.e(TAG, "" + lastProfitBean.lastProfit.info);
            }else if (iType ==HTF_SERVICE_CHECK_TYPE){
                ServiceCheckBean bean = (ServiceCheckBean) response;
            }
        }

        @Override
        public void fail(int iType, String error) {
            Log.e(TAG, "error==" + iType);
        }
    }
}
