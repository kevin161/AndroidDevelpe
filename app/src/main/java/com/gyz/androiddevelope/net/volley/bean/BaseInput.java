package com.gyz.androiddevelope.net.volley.bean;

import android.util.Log;

import com.android.volley.Request;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.bean.BaseInput.java
 * @author: ZhaoHao
 * @date: 2016-09-06 16:27
 */
public abstract class BaseInput<T> {
    private static final String TAG = "BaseInput";

    public final String url;

    public final int method;

    public final Class<T> clazz;

    public BaseInput(String url, int method, Class clazz) {
        this.url = url;
        this.method = method;
        this.clazz = clazz;
    }

    public String getUrl() {
        if (this.method == Request.Method.GET || this.method == Request.Method.DELETE) {
            Map<String, Object> params = getMapObjParams();
            if (params.isEmpty()) {
                return url;
            } else {
                return url + "?" + paramsToString(params);
            }
        } else {
            return this.url;
        }
    }


    private static String paramsToString(Map<String, Object> params) {
        if (params != null && params.size() > 0) {
            String paramsEncoding = "UTF-8";
            StringBuilder encodedParams = new StringBuilder();
            try {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder.encode(entry.getValue().toString(), paramsEncoding));
                    encodedParams.append('&');

                }
                encodedParams.deleteCharAt(encodedParams.length() - 1);
                String data = encodedParams.toString();
                Log.e(TAG, "request data=" + data);
                return data;
            } catch (UnsupportedEncodingException uee) {
                throw new RuntimeException("Encoding not supported: "
                        + paramsEncoding, uee);
            }
        }
        return null;
    }

    public Map<String, Object> getMapObjParams() {
        Class<? extends BaseInput> clazz = this.getClass();
        Map<String, Object> params = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            Class<?> type = field.getType();
            field.setAccessible(true);
            try {
                if (String.class == type || URL.class == type) {
                    params.put(field.getName(), field.get(this).toString());
                } else if (double.class == type || Double.class == type) {
                    params.put(field.getName(), field.getDouble(this));
                } else if (float.class == type || Float.class == type) {
                    params.put(field.getName(), field.getFloat(this));
                } else if (int.class == type || Integer.class == type) {
                    params.put(field.getName(), field.getInt(this));
                } else if (long.class == type || Long.class == type) {
                    params.put(field.getName(), field.getLong(this));
                } else if (short.class == type || Short.class == type) {
                    params.put(field.getName(), field.getShort(this));
                } else if (byte.class == type || Byte.class == type) {
                    params.put(field.getName(), field.getByte(this));
                } else if (boolean.class == type || Boolean.class == type) {
                    params.put(field.getName(), field.getBoolean(this) ? 1 : 0);
                } else if (Date.class == type) {
                    params.put(field.getName(), ((java.sql.Date) field.get(this)).getTime());
                } else if (byte[].class == type) {
                    params.put(field.getName(), field.get(this));
                } else if (Enum.class == type) {
                    params.put(field.getName(), ((Enum) field.get(this)).ordinal());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }


    public Map<String, String> getParams() {
        Class<? extends BaseInput> clazz = this.getClass();
        Map<String, String> params = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            Class<?> type = field.getType();
            field.setAccessible(true);
            try {
                if (String.class == type || URL.class == type) {
                    params.put(field.getName(), field.get(this).toString());
                } else if (double.class == type || Double.class == type) {
                    params.put(field.getName(), String.valueOf(field.getDouble(this)));
                } else if (float.class == type || Float.class == type) {
                    params.put(field.getName(), String.valueOf(field.getFloat(this)));
                } else if (int.class == type || Integer.class == type) {
                    params.put(field.getName(), String.valueOf(field.getInt(this)));
                } else if (long.class == type || Long.class == type) {
                    params.put(field.getName(), String.valueOf(field.getLong(this)));
                } else if (short.class == type || Short.class == type) {
                    params.put(field.getName(), String.valueOf(field.getShort(this)));
                } else if (byte.class == type || Byte.class == type) {
                    params.put(field.getName(), String.valueOf(field.getByte(this)));
                } else if (boolean.class == type || Boolean.class == type) {
                    params.put(field.getName(), String.valueOf(field.getBoolean(this) ? 1 : 0));
                } else if (Date.class == type) {
                    params.put(field.getName(), String.valueOf(((java.sql.Date) field.get(this)).getTime()));
                } else if (byte[].class == type) {
                    params.put(field.getName(), String.valueOf(field.get(this)));
                } else if (Enum.class == type) {
                    params.put(field.getName(), String.valueOf(((Enum) field.get(this)).ordinal()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

}
