package com.gyz.androiddevelope.net.volley;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gyz.androiddevelope.net.volley.bean.BaseInput;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.VolleyBaseActivity.java
 * @author: ZhaoHao
 * @date: 2016-11-23 14:41
 */
public class VolleyBaseActivity extends AppCompatActivity {
    private static final String TAG = "VolleyBaseActivity";
    /**
     * http tag
     */
    private List<Integer> arrayTag = new ArrayList<Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * net request
     * @param input
     * @param listener
     * @param iType
     * @param isTouchOutSide
     * @param isDialogDisplay
     */
    public void httpRequestEntrance(BaseInput<Object> input, final RequestListener listener,
                                    final int iType, boolean isTouchOutSide, final boolean isDialogDisplay) {
        if (isDialogDisplay) {
            //TODO  dialogShow(isTouchOutSide);
        }

        GsonRequest<Object> request = new GsonRequest<>(input,new Response.Listener<Object>(){
            @Override
            public void onResponse(Object response) {
                if(isDialogDisplay){
//                    dialogDismiss();
                }
                if(response != null){
                    if(listener != null){
                        //在分发之前做一次过滤

                        if(httpResponsedOtherDeviceLogin(iType, response)){
                            return ;
                        }
                        if(httpResponsedFailManager(iType, response)){
                            return ;
                        }
                        if(arrayTag.contains(iType)){
                            listener.success(iType, response);
                        }
                    }
                }
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

                //fail 回调统一调用dismiss
              //  dialogDismiss();
                //下方统一错误显示，故此回调传空字符串即可
                listener.fail(iType, "");
                LogUtils.e(TAG,"error======"+error.getMessage());
                ToastUtil.showShort(VolleyBaseActivity.this,"VolleyError....");
            }
        });

        request.setTag(iType);
        arrayTag.add(iType);
        VolleyInit.request(request);

    }


    /**
     * http responsed other device
     * @return true的话在此函数后面的代码不执行
     */
    private boolean httpResponsedOtherDeviceLogin(int iType, Object response){
//        将response转化成定义好的错误类，然后做一些返回内容的判断。如 是否登录，是否在其他账号登录而被踢下线等
//        ErrorInfo errorCode = (ErrorInfo) response;
//        if(errorCode.getErrorCode() == ErrorCode.OTHER_DEVICE_LOGIN_ERROR_NO){
//            login();
//            //如果是首页的消息，则不finish当前界面
//            if(iType == RequestType.HLC_HOME_TYPE || iType == RequestType.CHOOSE_RECHARGE_TYPE ||
//                    iType == RequestType.WITHDRAW_LOAD_TYPE || iType == RequestType.DEVICE_REGISTER_TYPE ||
//                    iType == RequestType.SERVER_LIST_TYPE || iType == RequestType.SUM_INFO_TYPE ||
//                    iType == RequestType.UNLOGIN_BEAN_TYPE){
//            }else{
//                finish();
//            }
//            return true;
//        }
        return false;
    }


    /**http responsed fail manager
     * @param iType
     * @param response
     * @return 返回true函数后面的success回调代码不执行
     */
    private boolean httpResponsedFailManager(int iType, Object response){
        boolean bFlag = false;

//        ErrorInfo errorCode = (ErrorInfo) response;
//        //成功的情况下，success回调必须执行
//        if(errorCode.getErrorCode() == ErrorCode.SUCCESS){
//            bFlag = false;
//            return bFlag;
//        }
//
//        //不成功的情况下，如果只是显示Toast，不执行其他代码的话返回true,Toast显示的工作在这个函数完成
//        //如果还要执行其他代码的话，某些特定的请求类型返回false，让其执行其他代码
//        switch(iType){
//            /**PayActivity*/
//            case RequestType.RECHARGE_SEND_PAY_TYPE:
//            case RequestType.RECHARGE_LOADING_TYPE:
//            case RequestType.RECHARGE_QUERY_PAY_RESULT_TYPE:
//                bFlag = false;
//                break;
//            /**HlcInvestDetailesActivity*/
//            case RequestType.HLC_DETAILS_TYPE:
//            case RequestType.HLC_DETAILS_BUY_TYPE:
//            case  RequestType.HLC_DETAILS_BUY_STATUS_TYPE:
//                bFlag = false;
//                break;
//
//        }
//
//        //返回true，不执行success回调的给出Toast请求错误提示
//        if(bFlag){
//            WordUtil.showToast(BaseActivity.this, errorCode.getErrorString(), 0);
//        }

        return bFlag;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  dialogDismiss();
        cancelAllRequest();
    }

    /**
     * 取消当前界面的所有Http请求
     */
    @SuppressWarnings("static-access")
    protected void cancelAllRequest() {
        if (arrayTag != null) {
            for (int tag : arrayTag) {
                if (VolleyInit.requestQueue != null) {
                    VolleyInit.requestQueue.cancelAll(tag);
                }
            }
            arrayTag.clear();
        }
    }

}
