package com.gyz.androiddevelope.listener;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.listener.MyItemTouchHelperCallback.java
 * @author: ZhaoHao
 * @date: 2016-06-17 14:03
 */
public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private static final String TAG = "MyItemTouchHelperCallback";

    ItemTouchMoveListener itemTouchMoveListener;

    public MyItemTouchHelperCallback(ItemTouchMoveListener listener) {
        this.itemTouchMoveListener = listener;
    }

    //Callback回调监听时先调用的，用来判断当前是什么动作，比如判断方向（意思就是我要监听哪个方向的拖动）
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        //方向 常量
        int up = ItemTouchHelper.UP;
        int down = ItemTouchHelper.DOWN;
        int left = ItemTouchHelper.LEFT;
        int right = ItemTouchHelper.RIGHT;

        //我要监听的拖拽方向是哪两个方向。
        int dragFlags = ItemTouchHelper.UP| ItemTouchHelper.DOWN;
        //我要监听的swipe侧滑方向是哪个方向
        int swipeFlags = ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT;
        //或运算
        int flags = makeMovementFlags(dragFlags,swipeFlags);

        return flags;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        //是否允许长按拖拽效果
        return true;
    }

    //当移动的时候回调的方法--拖拽
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //判断移动item和目标item 是否是同类型
        if (viewHolder.getItemViewType() != target.getItemViewType()) return false;

        //在拖拽过程中，不断的调用adapter.notifyItemMoved(from,to);
       return itemTouchMoveListener.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());

    }


    //侧滑的时候回调的
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        // 监听侧滑，1.删除数据；2.调用adapter.notifyItemRemove(position)
        itemTouchMoveListener.onItemDrag(viewHolder.getAdapterPosition());
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 恢复
//        viewHolder.itemView.setBackgroundColor(Color.WHITE);
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        //dX:水平方向移动的增量（负：往左；正：往右）范围：0~View.getWidth  0~1
        if(actionState== ItemTouchHelper.ACTION_STATE_SWIPE){
            //透明度动画
            float alpha = 1- Math.abs(dX)/viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);//1~0
            viewHolder.itemView.setScaleX(alpha);//1~0
            viewHolder.itemView.setScaleY(alpha);//1~0
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
