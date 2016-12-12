package com.gyz.androiddevelope.listener;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.listener.ItemTouchMoveListener.java
 * @author: ZhaoHao
 * @date: 2016-06-17 14:13
 */
public interface ItemTouchMoveListener {

//    用于adapter ，实现实时item上下相互变换位置
    public boolean onItemMove(int form, int to);

//用于adapter ，实现实时左右侧滑时 调用   // 监听侧滑，1.删除数据；2.调用adapter.notifyItemRemove(position)
    public void onItemDrag(int position);
}
