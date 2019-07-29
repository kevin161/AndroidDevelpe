package com.gyz.androiddevelope.fragment.huaban;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.gyz.androiddevelope.activity.huaban.ImageDetailActivity;
import com.gyz.androiddevelope.adapter.HuabanRecyclerAdapter;
import com.gyz.androiddevelope.adapter.BaseRecyclerAdapter;
import com.gyz.androiddevelope.base.BaseRecyclerFragment;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.listener.OnSwipeRefreshFragmentListener;
import com.gyz.androiddevelope.net.retrofit.ReUtil;
import com.gyz.androiddevelope.net.retrofit.RxUtil;
import com.gyz.androiddevelope.response_bean.ListPinsBean;
import com.gyz.androiddevelope.response_bean.PinsMainEntity;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.util.UserUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.fragment.huaban.UserLikeAndPinFragment.java 采集、喜欢界面
 * @author: ZhaoHao
 * @date: 2016-06-16 16:09
 */
public class UserPinAndLikeFragment extends BaseRecyclerFragment {
    private static final String TAG = "UserPinAndLikeFragment";
    public static final String USERID = "USERID";
    public static final String TYPE = "isPin";
    HuabanRecyclerAdapter adapter;
    OnSwipeRefreshFragmentListener swipeRefreshFragmentListener;

    private String userId;
    //最后的id值，为加载下一页用。
    private long mMaxId;
    private boolean isPin;

    public static UserPinAndLikeFragment newInstance(String userId, boolean isPin) {
        UserPinAndLikeFragment instance = new UserPinAndLikeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERID, userId);
        bundle.putBoolean(TYPE, isPin);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSwipeRefreshFragmentListener) {
            swipeRefreshFragmentListener = (OnSwipeRefreshFragmentListener) context;
        }
    }

    /**
     * 获取list数据
     *
     * @param isAdd
     */
    @Override
    protected void addListNetData(boolean isAdd) {
        LogUtils.e(TAG, "isadd====" + isAdd);
        if (!isAdd) {
            swipeRefreshFragmentListener.OnRefreshState(true);
        } else {
            requestData(isAdd);
        }
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

        adapter.setRecyclerAdapterListener(new HuabanRecyclerAdapter.RecyclerAdapterListener() {
            @Override
            public void onClickImage(PinsMainEntity bean, View view) {
                ImageDetailActivity.startActivity(getContext(),bean);
            }

            @Override
            public void onClickTitleInfo(PinsMainEntity bean, View view) {
                ImageDetailActivity.startActivity(getContext(),bean);
            }

            @Override
            public void onClickInfoGather(PinsMainEntity bean, View view) {

            }

            @Override
            public void onClickInfoLike(PinsMainEntity bean, View view) {

            }
        });
        return adapter;
    }

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

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void getBundleDatas(Bundle bundle) {
        userId = bundle.getString(USERID);
        isPin = bundle.getBoolean(TYPE);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        requestData(false);
    }

    private void requestData(final boolean isAdd) {

        RxUtil.subscribeAll(new Func1<String, Observable<ListPinsBean>>() {
            @Override
            public Observable<ListPinsBean> call(String s) {
                if (isPin) {
                    //采集 网络请求
                    if (isAdd){
                        return ReUtil.getApiManager(AppContants.HUABAN_HTTP) .httpsUserPinsMaxRx(UserUtils.getAuthorizations(true, getContext()), userId, mMaxId, AppContants.LIMIT);
                    }else {
                        return ReUtil.getApiManager(AppContants.HUABAN_HTTP) .httpsUserPinsRx(UserUtils.getAuthorizations(true, getContext()), userId, AppContants.LIMIT);

                    }
                } else {
                    //喜欢  网络请求
                    if (isAdd) {
                        return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsUserLikePinsMaxRx(UserUtils.getAuthorizations(true, getContext()), userId, mMaxId, AppContants.LIMIT);
                    } else {
                        return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsUserLikePinsRx(UserUtils.getAuthorizations(true, getContext()), userId, AppContants.LIMIT);
                    }
                }
            }
        }, new Subscriber<ListPinsBean>() {
            @Override
            public void onCompleted() {
                swipeRefreshFragmentListener.OnRefreshState(false);
            }

            @Override
            public void onError(Throwable e) {
                swipeRefreshFragmentListener.OnRefreshState(false);
                e.printStackTrace();
            }

            @Override
            public void onNext(ListPinsBean listPinsBean) {
                List<PinsMainEntity> data = listPinsBean.getPins();
                //保存maxId值 后续加载需要
                mMaxId = data.get(data.size() - 1).getPin_id();
                //TODO 这里数据返回有问题，先屏蔽
//                if (!isAdd) {
                adapter.clearDatas();
//                }
                adapter.addDatas(data);
            }
        });

    }

    @Override
    public String getTitle() {
        return null;
    }
}
