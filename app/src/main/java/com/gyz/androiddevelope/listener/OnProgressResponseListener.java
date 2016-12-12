package com.gyz.androiddevelope.listener;

/**
 * 下载图片接口
 */
public interface OnProgressResponseListener {
    void onResponseProgress(long bytesRead, long contentLength, boolean done);
}
