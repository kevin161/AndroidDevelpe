package com.gyz.androiddevelope.net.volley;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.RequestListener.java
 * @author: ZhaoHao
 * @date: 2016-11-23 14:37
 */

public interface RequestListener {
    public void success(int iType, Object response);

    public void fail(int iType, String error);
}
