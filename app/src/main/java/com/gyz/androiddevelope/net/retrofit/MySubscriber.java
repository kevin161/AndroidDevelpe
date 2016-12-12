package com.gyz.androiddevelope.net.retrofit;

import android.content.Context;

import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.response_bean.BaseResponHuaBanBean;
import com.gyz.androiddevelope.response_bean.BaseResponTngouBean;
import com.gyz.androiddevelope.util.LogUtils;

import rx.Subscriber;

/**
 * @author: guoyazhou
 * @date: 2016-03-21 17:06
 */
public class MySubscriber<T> extends Subscriber<T> {

    Context context;

    public MySubscriber(){

    }
    public MySubscriber(Context ctx){
        context = ctx;
    }

    @Override
    public void onStart() {
        if (context instanceof BaseActivity){
           ((BaseActivity) context).dlg.show();
        }
    }

    @Override
    public void onCompleted() {
        if (context instanceof BaseActivity){
            ((BaseActivity) context).dlg.hide();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(T o) {

        if (o instanceof BaseResponTngouBean){

        }else if (o instanceof BaseResponHuaBanBean){
            if (((BaseResponHuaBanBean) o).getErr()== AppContants.HUABAN_ERR_NUM){
//                LoginActivity.startActivity();
                LogUtils.e("-----------token过期---------");
            }
        }

    }

}
