package com.gyz.androiddevelope.listener;

import android.support.v7.widget.RecyclerView;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.listener.StartDragListener.java
 * @author: ZhaoHao
 * @date: 2016-06-17 14:32
 */
public interface StartDragListener {
    /**
     * 该接口用于RecyclerView需要主动回调拖拽效果的
     * @param viewHolder
     */
    public void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
