package com.gyz.androiddevelope.fragment.huaban;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.activity.huaban.HuabanSearchActivity;
import com.gyz.androiddevelope.activity.huaban.ImageDetailActivity;
import com.gyz.androiddevelope.adapter.BaseRecyclerAdapter;
import com.gyz.androiddevelope.adapter.HuabanRecyclerAdapter;
import com.gyz.androiddevelope.base.BaseRecyclerFragment;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.listener.OnSwipeRefreshFragmentListener;
import com.gyz.androiddevelope.net.retrofit.ReUtil;
import com.gyz.androiddevelope.net.retrofit.RxUtil;
import com.gyz.androiddevelope.response_bean.ListPinsBean;
import com.gyz.androiddevelope.response_bean.PinsMainEntity;
import com.gyz.androiddevelope.util.LogUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.fragment.huaban.HuabanFragment.java
 * @author: ZhaoHao
 * @date: 2016-06-07 13:18
 */
public class HuabanFragment extends BaseRecyclerFragment {
    private static final String TAG = "HuabanFragment";
    public static final String KEY = "key";

    //花瓣联网的授权字段
    protected String mAuthorization;
    protected String mKey = "all";//用于联网查询的关键字
    protected static int mLimit = AppContants.LIMIT;
    private String mMaxId ="0";
    private HuabanRecyclerAdapter adapter;
    //与Activity 交互接口 SWIpe 刷新结束后，停止转圈
    private OnSwipeRefreshFragmentListener mRefreshListener;

    @Override
    public void getBundleDatas(Bundle bundle) {
        mKey = bundle != null ? (String) bundle.get(KEY) : "all";
    }

    @Override
    public void initData() {
        requestData(false);
    }

    @Override
    public String getTitle() {
        return "花瓣";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSwipeRefreshFragmentListener) {
            mRefreshListener = (OnSwipeRefreshFragmentListener) context;
        }
    }

    @Override
    protected void addListNetData(boolean isAdd) {
        //下拉刷新新数据
        if (!isAdd) {
            mRefreshListener.OnRefreshState(true);
        }
        requestData(isAdd);
    }

    int i = 0;

    private void requestData(final boolean isAddData) {

//        Observable<ListPinsBean> observable = ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsTypeLimitRx(mAuthorization, mKey, mLimit);
//
//        // 步骤4：发送网络请求 & 通过repeatWhen（）进行轮询
//        observable.repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
//            @Override
//            public Observable<?> call(Observable<? extends Void> objectObservable) {
//                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
//                // 以此决定是否重新订阅 & 发送原来的 Observable，即轮询
//                // 此处有2种情况：
//                // 1. 若返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable，即轮询结束
//                // 2. 若返回其余事件，则重新订阅 & 发送原来的 Observable，即继续轮询
//                return objectObservable.flatMap(new Func1<Void, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(Void aVoid) {
//                        LogUtils.d("call===========" + i);
//                        // 加入判断条件：当轮询次数 = 5次后，就停止轮询
//                        if (i > 3) {
//                            // 此处选择发送onError事件以结束轮询，因为可触发下游观察者的onError（）方法回调
//                            return Observable.error(new Throwable("轮询结束"));
//                        }
//                        i++;
//                        // 若轮询次数＜4次，则发送1Next事件以继续轮询
//                        // 注：此处加入了delay操作符，作用 = 延迟一段时间发送（此处设置 = 2s），以实现轮询间间隔设置
//                        return Observable.just(1).delay(2000, TimeUnit.MILLISECONDS);
//                    }
//
////                    @Override
////                    public Observable<?> apply(@NonNull Object throwable) throws Exception {
////                        // 加入判断条件：当轮询次数 = 5次后，就停止轮询
////                        if (i > 3) {
////                            // 此处选择发送onError事件以结束轮询，因为可触发下游观察者的onError（）方法回调
////                            return Observable.error(new Throwable("轮询结束"));
////                        }
////                        // 若轮询次数＜4次，则发送1Next事件以继续轮询
////                        // 注：此处加入了delay操作符，作用 = 延迟一段时间发送（此处设置 = 2s），以实现轮询间间隔设置
////                        return Observable.just(1).delay(2000, TimeUnit.MILLISECONDS);
////                    }
//                });
//            }
//
////            @Override
////            // 在Function函数中，必须对输入的 Observable<Object>进行处理，此处使用flatMap操作符接收上游的数据
////            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
////                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
////                // 以此决定是否重新订阅 & 发送原来的 Observable，即轮询
////                // 此处有2种情况：
////                // 1. 若返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable，即轮询结束
////                // 2. 若返回其余事件，则重新订阅 & 发送原来的 Observable，即继续轮询
////                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
////                    @Override
////                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {
////
////                        // 加入判断条件：当轮询次数 = 5次后，就停止轮询
////                        if (i > 3) {
////                            // 此处选择发送onError事件以结束轮询，因为可触发下游观察者的onError（）方法回调
////                            return Observable.error(new Throwable("轮询结束"));
////                        }
////                        // 若轮询次数＜4次，则发送1Next事件以继续轮询
////                        // 注：此处加入了delay操作符，作用 = 延迟一段时间发送（此处设置 = 2s），以实现轮询间间隔设置
////                        return Observable.just(1).delay(2000, TimeUnit.MILLISECONDS);
////                    }
////                });
////
////            }
//        }).subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
//                .observeOn(AndroidSchedulers.mainThread())  // 切换回到主线程 处理请求结果
//                .subscribe(new Observer<ListPinsBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtils.d("onError===" + e.getLocalizedMessage());
//                        // 获取轮询结束信息
//                    }
//
//                    @Override
//                    public void onNext(ListPinsBean listPinsBean) {
//                        // e.接收服务器返回的数据
//                        LogUtils.d("donext===" + listPinsBean.toString());
//                    }
//                });


        // TODO: 2019/6/25 guoyazhou todo
//        Observable.interval(2, 5, TimeUnit.SECONDS)
//                .doOnNext(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        LogUtils.e("InternetService", "第 " + aLong + " 次轮询");
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .flatMap(new Func1<Long, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(Long aLong) {
//                        return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsTypeLimitRx(mAuthorization, mKey, mLimit);
//                    }
//                }).observeOn(AndroidSchedulers.mainThread())
//               .takeUntil(new Func1<Object, Boolean>() {
//            @Override
//            public Boolean call(Object o) {
//                i++;
//                LogUtils.e("=============================i" + i);
//                if (i == 4) {
//
//                    return true;
//                } else {
//                    return false;
//
//                }
//            }
//        });


//                .subscribe(new Action1<Object>() {
//                    @Override
//                    public void call(Object o) {
//                        //next
//                        LogUtils.d("donext===" + o.toString());
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
////                        error
//
//                        LogUtils.d("error===" + throwable.getMessage());
//                    }
//                });


        RxUtil.subscribeAll(new Func1<String, Observable<ListPinsBean>>() {
            @Override
            public Observable<ListPinsBean> call(String s) {
                if (isAddData) {
                    return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsTypeMaxLimitRx(mAuthorization, mKey, mMaxId, mLimit);
                } else {
                    return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsTypeLimitRx(mAuthorization, mKey, mLimit);
                }
            }
        }, new Subscriber<ListPinsBean>() {
            @Override
            public void onCompleted() {
                mRefreshListener.OnRefreshState(false);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mRefreshListener.OnRefreshState(false);
            }

            @Override
            public void onNext(ListPinsBean listPinsBean) {
                List<PinsMainEntity> result = listPinsBean.getPins();
                //保存maxId值 后续加载需要
                mMaxId = result.get(result.size() - 1).getPin_id();
                if (!isAddData) {
                    adapter.clearDatas();
                }
                adapter.addDatas(result);
            }
        });

    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        adapter = new HuabanRecyclerAdapter(getContext());

        adapter.setRecyclerAdapterListener(new HuabanRecyclerAdapter.RecyclerAdapterListener() {
            @Override
            public void onClickImage(PinsMainEntity bean, View view) {
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//
//                    Explode explode = new Explode();
//                    explode.setDuration(400);
//                    getActivity().getWindow().setExitTransition(explode);//出去的动画
//                    getActivity().getWindow().setEnterTransition(explode);//进来的动画
//                    //如果有共享元素，可以设置共享元素，那么它就会按照共享元素动画执行，其他的子view就会按照Fade动画执行。
//                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity());
//                    Intent intent = new Intent(context, ImageDetailActivity.class);
//                    intent.putExtra(ImageDetailActivity.IMAGE_BEAN, bean);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent, optionsCompat.toBundle());
//                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    //转场动画
////                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, "imgDetail");
////                    Intent intent = new Intent(context, ImageDetailActivity.class);
////                    intent.putExtra(ImageDetailActivity.IMAGE_BEAN, bean);
////                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    context.startActivity(intent, optionsCompat.toBundle());
//
//
//                } else {
                ImageDetailActivity.startActivity(getContext(), bean);
//                }

            }

            @Override
            public void onClickTitleInfo(PinsMainEntity bean, View view) {
                ImageDetailActivity.startActivity(getContext(), bean);
            }

            @Override
            public void onClickInfoGather(PinsMainEntity bean, View view) {
//TODO
            }

            @Override
            public void onClickInfoLike(PinsMainEntity bean, View view) {
            }
        });

        return adapter;
    }

    @Override
    protected boolean showFab() {
        return true;
    }

    /**
     * 获取fab图片src
     *
     * @return
     */
    @Override
    protected int getFabImageSrc() {
        return R.drawable.ic_search_black_24dp;
    }

    @Override
    protected boolean onFabClickListener(FloatingActionButton floatingActionButton) {
        HuabanSearchActivity.startActivity(getActivity());
        return true;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

}
