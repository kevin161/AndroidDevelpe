package com.gyz.androiddevelope.base;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.adapter.BaseRecyclerAdapter;
import com.gyz.androiddevelope.listener.OnRecyclerRefreshListener;
import com.gyz.androiddevelope.listener.RecycleViewOnScrollListener;
import com.gyz.androiddevelope.util.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.base.BaseRecyclerFragment.java
 * @author: GYZ
 * @date: 2016-06-07 09:50
 */
public abstract class BaseRecyclerFragment extends BaseFragment implements OnRecyclerRefreshListener {
    private static final String TAG = "BaseRecyclerFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    BaseRecyclerAdapter mAdapter;
    @Bind(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    protected final float percentageScroll = 0.8f;//滑动距离的百分比
    //是否还监听滑动的联网 标志位 默认为true 表示需要监听
    protected boolean isScorllLisener = true;

    @Override
    public int getLayoutId() {
        return R.layout.base_recycler_fragment;
    }

    @Override
    public void initView() {
        if (showFab()) {
            floatingActionButton.setVisibility(View.VISIBLE);
            if (getFabImageSrc()!=0){
                floatingActionButton.setImageResource(getFabImageSrc());
            }
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  if( ! onFabClickListener((FloatingActionButton) v) ){
                     addListNetData(false);
                  }
                }
            });
        }

        recyclerView.setLayoutManager(getLayoutManager());
        mAdapter = getAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recyclerView.addOnScrollListener(new RecycleViewOnScrollListener() {
            @Override
            public void showFab() {
                floatingActionButton.animate().translationY(0);
            }

            @Override
            public void hideFab() {
                floatingActionButton.animate().translationY(floatingActionButton.getHeight() + ((FrameLayout.LayoutParams) floatingActionButton.getLayoutParams()).bottomMargin);
            }

            @Override
            public void normalScroll() {
            }

            @Override
            public void onScrollStateChange(RecyclerView recyclerView, int newState) {
                if (mAdapter == null)
                    return;
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    //滑动停止
                    LogUtils.d("滑动停止 position=" + mAdapter.getPosition());
                    int size = (int) (mAdapter.getItemCount() * percentageScroll);
                    if (mAdapter.getPosition() >= --size && isScorllLisener) {
                        addListNetData(true);
                    }
                } else if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    //用户正在滑动
//                    LogUtils.d("用户正在滑动 position=" + mAdapter.getAdapterPosition());
                } else {
                    //惯性滑动
//                    LogUtils.d("惯性滑动 position=" + mAdapter.getAdapterPosition());
                }
            }
        });
    }


    /**
     * 获取list数据
     */
    protected abstract void addListNetData(boolean isAdd);

    /**
     * 是否设置新的点击事件，不点击返回false ，执行刷新操作
     * @param floatingActionButton
     * @return
     */
    protected abstract boolean onFabClickListener(FloatingActionButton floatingActionButton);


    /**
     * @return 这里初始化 adapter
     */
    protected abstract BaseRecyclerAdapter getAdapter();

    /**
     * 是否显示 fab
     * @return
     */
    protected abstract boolean showFab();

    /**
     * 获取fab图片src
     * @return
     */
    protected abstract int getFabImageSrc();

    /**
     * @return 获取recyclerView的layoutmanager
     */
    protected abstract RecyclerView.LayoutManager getLayoutManager();

    @Override
    public void onRecyclerRefresh() {
        addListNetData(false);
    }


    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
