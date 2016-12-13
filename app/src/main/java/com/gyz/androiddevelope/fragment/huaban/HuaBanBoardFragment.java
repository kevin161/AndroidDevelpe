package com.gyz.androiddevelope.fragment.huaban;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.gyz.androiddevelope.activity.huaban.HuaBanBoardActivity;
import com.gyz.androiddevelope.activity.huaban.ImageDetailActivity;
import com.gyz.androiddevelope.adapter.HuabanRecyclerAdapter;
import com.gyz.androiddevelope.base.BaseApplication;
import com.gyz.androiddevelope.adapter.BaseRecyclerAdapter;
import com.gyz.androiddevelope.base.BaseRecyclerFragment;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.net.retrofit.MySubscriber;
import com.gyz.androiddevelope.net.retrofit.ReUtil;
import com.gyz.androiddevelope.net.retrofit.RxUtil;
import com.gyz.androiddevelope.response_bean.ListPinsBean;
import com.gyz.androiddevelope.response_bean.PinsMainEntity;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.util.UserUtils;

import rx.Observable;
import rx.functions.Func1;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.fragment.huaban.HuaBanBoardFragment.java
 * @author: ZhaoHao
 * @date: 2016-06-22 16:48
 */
public class HuaBanBoardFragment extends BaseRecyclerFragment {

    private static final String TAG = "HuaBanBoardFragment";
    private HuabanRecyclerAdapter adapter;
    private String mBoardId;
    private int maxId;

    /**
     * 获取list数据
     *
     * @param isAdd
     */
    @Override
    protected void addListNetData(boolean isAdd) {
        requestData(isAdd);
    }

    /**
     * 是否设置新的点击事件，不点击返回false ，执行刷新操作
     *
     * @param floatingActionButton
     * @return
     */
    @Override
    protected boolean onFabClickListener(FloatingActionButton floatingActionButton) {
        return false;
    }

    /**
     * @return 这里初始化 adapter
     */
    @Override
    protected BaseRecyclerAdapter getAdapter() {
        adapter = new HuabanRecyclerAdapter(getContext());
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.huaban_board_headview,getRecyclerView());
//        adapter.setHeaderView(view);
        adapter.setRecyclerAdapterListener(new HuabanRecyclerAdapter.RecyclerAdapterListener() {
            @Override
            public void onClickImage(PinsMainEntity bean, View view) {
                ImageDetailActivity.startActivity(getContext(), bean);
            }

            @Override
            public void onClickTitleInfo(PinsMainEntity bean, View view) {
                ImageDetailActivity.startActivity(getContext(), bean);
            }

            @Override
            public void onClickInfoGather(PinsMainEntity bean, View view) {

            }

            @Override
            public void onClickInfoLike(PinsMainEntity bean, View view) {

            }
        });


        return  adapter;
    }

    /**
     * 是否显示 fab
     *
     * @return
     */
    @Override
    protected boolean showFab() {
        return false;
    }

    /**
     * 获取fab图片src
     *
     * @return
     */
    @Override
    protected int getFabImageSrc() {
        return 0;
    }

    /**
     * @return 获取recyclerView的layoutmanager
     */
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void getBundleDatas(Bundle bundle) {
        mBoardId = bundle.getString(HuaBanBoardActivity.TYPE_ID);

    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        requestData(false);
    }

    @Override
    public String getTitle() {
        return null;
    }


    private void requestData(final boolean isAdd) {
        RxUtil.subscribeAll(new Func1<String, Observable<ListPinsBean>>() {
            @Override
            public Observable<ListPinsBean> call(String s) {
                if (isAdd) {
                    String str = UserUtils.getAuthorizations(BaseApplication.getInstantce().isLogin(), getContext());
                    LogUtils.v(TAG,"auth====================="+str);
                    return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsBoardPinsMaxRx(str
                           , mBoardId, maxId, AppContants.LIMIT);
                } else {
                    return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsBoardPinsRx(UserUtils.getAuthorizations(BaseApplication.getInstantce().isLogin(),getContext()), mBoardId, AppContants.LIMIT);
                }
            }
        }, new MySubscriber<ListPinsBean>() {
            @Override
            public void onNext(ListPinsBean o) {
                super.onNext(o);
                //// TODO: 2016/6/21
                adapter.addDatas(o.getPins());
            }
        });

    }
}
