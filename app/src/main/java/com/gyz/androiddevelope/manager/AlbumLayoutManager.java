package com.gyz.androiddevelope.manager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 仿美图详情页 手势画廊  自定义layoutManager ,
 * 自定义item布局
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.manager.AlbumLayoutManager.java
 * @author: ZhaoHao
 * @date: 2016-12-15 14:17
 */
public class AlbumLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = "AlbumLayoutManager";

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
         detachAndScrapAttachedViews(recycler);// 分离所有的itemView
        for (int i = 0; i <  getItemCount(); i++) {
            // 根据position获取一个碎片view，可以从回收的view中获取，也可能新构造一个
            View child = recycler.getViewForPosition(i);
             measureChildWithMargins(child,0,0); // 计算此碎片view包含边距的尺寸
             addView(child);
            int width = getDecoratedMeasuredWidth(child);// 获取此碎片view包含边距和装饰的宽度width
            int height =  getDecoratedMeasuredHeight(child);
            // Important！布局到RecyclerView容器中，所有的计算都是为了得出任意position的item的边界来布局
             layoutDecorated(child,0,0,width,height);
            if (i<this.getItemCount()-1){
                child.setScaleX(0.8f);
                child.setScaleY(0.8f);
            }
        }
    }
}
