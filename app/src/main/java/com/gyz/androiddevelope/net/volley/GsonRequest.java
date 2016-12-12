package com.gyz.androiddevelope.net.volley;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.gyz.androiddevelope.net.volley.bean.BaseInput;
import com.gyz.androiddevelope.util.GsonUtils;
import com.gyz.androiddevelope.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.GsonRequest.java
 * @author: ZhaoHao
 * @date: 2016-11-23 14:37
 */
public class GsonRequest<T> extends Request<T> {
    private static final String TAG = "GsonRequest";

    private Response.Listener<T> mListener;
    private Map<String, String> params = new HashMap<>();
    public Class<T> mClazz;

    public GsonRequest(BaseInput<T> input, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(input.method,input.getUrl(),errorListener);
        this.mClazz = input.clazz;
        this.mListener = listener;
        buildRequestParams(input);
        setShouldCache(false);
    }

    private void buildRequestParams(BaseInput<T> input) {
        params = input.getParams();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        LogUtils.e(TAG,"GETparams..."+params);
        return params;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
           // String defaultCharset = HttpHeaderParser.parseCharset(response.headers);

            String data = new String(response.data,"utf-8");
            // 服务器返回errNo=0 status: true,
            if (!TextUtils.isEmpty(data)) {
                //如果有编码解密操作
//                byte[] decodeData = Base64.decode(data, Base64.DEFAULT);
//                String json = new String(SubmitHelper.decryptMode(decodeData));
                Log.e("NetRequest", "Request AFTER Decryption--->" + data);
                JSONObject result = new JSONObject(data);
                //这里做一些初级判断（未登录、返回数据初判断、网络问题等）
//                if (result.has("s")) {
//                    int error = result.getInt("s");
//                    if (error == 2000) {
//                        AccountManager.getInstance(VolleyInit.getApplication())
//                                .logout();
//                    }else if(error == 404){
//                        Log.e(TAG,"404");
//                      //可以做网络动态切换地址等操作
//                    }
//                }

                T responseObject;
                // 其他属于Gson请求，解析对象返回
                Gson gson = GsonUtils.createBuilder().create();
                responseObject = gson.fromJson(data, mClazz);
                return Response.success(responseObject,
                        HttpHeaderParser.parseCacheHeaders(response));
            }
            // 服务器返回errNo != 0
            else {
                return Response.error(new ParseError());
            }

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (OutOfMemoryError e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        return new DefaultRetryPolicy();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("X-Wap-Proxy-Cookie", "none");
        // WIFI或者非Wap网络下，添加cookie
//        if (NetworkUtils.isWifi() || !Init.isWap()) {
            headerMap.put("Cookie", getCookie());
//        }
        return headerMap;
    }

    /**
     * 获取Cookie形式的通用参数
     *
     * @return
     */
    private static String getCookie() {
        Map<String, String> commonParams = buildCommonParams();
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : commonParams.keySet()) {
            stringBuilder.append(key).append("=").append(commonParams.get(key));
            stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }

    /**
     * 构造通用参数列表
     */
    private static Map<String, String> buildCommonParams() {
        Map<String, String> params = new HashMap<String, String>();
        String bduss = "";
        String deviceId;
//        try {
//            deviceId = URLEncoder.encode(VolleyInit.getDeviceId(), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//        params.put("TERMINAL", "android_" + deviceId);
        params.put("APP_VERSION", "android_" + VolleyInit.getVersionName());
      //  params.put("CHANNEL", VolleyInit.getChannel());
        params.put("BDUSS", bduss);
        params.put("APP_TIME", System.currentTimeMillis() + "");
        return params;
    }
}
