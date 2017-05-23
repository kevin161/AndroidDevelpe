package com.gyz.androiddevelope.fragment;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.activity.HomeActivity;
import com.gyz.androiddevelope.activity.custom.AlbumListActivity;
import com.gyz.androiddevelope.activity.custom.CalenderActivity;
import com.gyz.androiddevelope.activity.custom.CircleActivity;
import com.gyz.androiddevelope.activity.custom.ConcatMatrixActivity;
import com.gyz.androiddevelope.activity.custom.DashBoardActivity;
import com.gyz.androiddevelope.activity.custom.DisScrollActivity;
import com.gyz.androiddevelope.activity.custom.FlickerProgressActivity;
import com.gyz.androiddevelope.activity.custom.FlyViewActivity;
import com.gyz.androiddevelope.activity.custom.GradientActivity;
import com.gyz.androiddevelope.activity.custom.HotFixActivity;
import com.gyz.androiddevelope.activity.custom.ImageCrapActivity;
import com.gyz.androiddevelope.activity.custom.McalendarActivity;
import com.gyz.androiddevelope.activity.custom.MountainViewActivity;
import com.gyz.androiddevelope.activity.custom.MyListViewActivity;
import com.gyz.androiddevelope.activity.custom.MyProgressBarActivity;
import com.gyz.androiddevelope.activity.custom.MyRadioGroupActivity;
import com.gyz.androiddevelope.activity.custom.MyRecyclerActivity;
import com.gyz.androiddevelope.activity.custom.MyWebActivity;
import com.gyz.androiddevelope.activity.custom.NearBySearchActivity;
import com.gyz.androiddevelope.activity.custom.NoBoringActionBarActivity;
import com.gyz.androiddevelope.activity.custom.PieChartActivity;
import com.gyz.androiddevelope.activity.custom.QQSlidingActivity;
import com.gyz.androiddevelope.activity.custom.SearchViewActivity;
import com.gyz.androiddevelope.activity.custom.ShowInfoActivity;
import com.gyz.androiddevelope.activity.custom.SlidingItemActivity;
import com.gyz.androiddevelope.activity.custom.ToolbarTestActivity;
import com.gyz.androiddevelope.activity.custom.Transform3DActivity;
import com.gyz.androiddevelope.activity.custom.TransformActivity;
import com.gyz.androiddevelope.activity.custom.WaveActivity;
import com.gyz.androiddevelope.activity.custom.Win10ProgressActivity;
import com.gyz.androiddevelope.activity.huaban.LoginActivity;
import com.gyz.androiddevelope.base.BaseFragment;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.engine.User;
import com.gyz.androiddevelope.net.okhttp.OkHttpClientManager;
import com.gyz.androiddevelope.net.retrofit.ReUtil;
import com.gyz.androiddevelope.net.retrofit.RxUtil;
import com.gyz.androiddevelope.net.volley.TestVolleyActivity;
import com.gyz.androiddevelope.net.volley.VolleyActivity;
import com.gyz.androiddevelope.proxy.EvilInstrumentation;
import com.gyz.androiddevelope.request_bean.ReqUserInfoBean;
import com.gyz.androiddevelope.response_bean.Axiba;
import com.gyz.androiddevelope.response_bean.UserInfo;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.util.Utils;
import com.gyz.androiddevelope.view.PwdView;
import com.gyz.androiddevelope.view.parallax.SplashActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Request;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class TestFragment extends BaseFragment {

    public static final String TAG = "TestFragment";
    public static final int GO_TO_INFO = 3001;

    @Bind(R.id.btnOnClick)
    Button btnOnClick;
    @Bind(R.id.txtInfo)
    TextView txtInfo;
    @Bind(R.id.btnHealthList)
    Button btnHealthList;
    @Bind(R.id.btnHome)
    Button btnHome;
    @Bind(R.id.pwdView)
    PwdView pwdView;

    ProgressDialog dlg;
    Context context;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void getBundleDatas(Bundle bundle) {

    }


    @Override
    public void initView() {

        context = getContext();
        dlg = Utils.createProgressDialog(context, this.getString(R.string.str_loading));
    }

    @Override
    public void initData() {
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getTitle() {
        return "test";
    }

    @OnClick({R.id.btnShareSdk, R.id.btnAutoScroll, R.id.btnPieChart, R.id.btnVolleyTest, R.id.btnRadioGroup, R.id.btnAlbumList, R.id.btnHotFix, R.id.btnSearchView, R.id.btnImageCrap, R.id.flickerProgressBar, R.id.btnWinLoad, R.id.btnDashView, R.id.btnTouchView, R.id.btnslidingMenu, R.id.btnQQslidingMenu, R.id.btnParallax, R.id.btnDisscrollView, R.id.btnTransform, R.id.btnAndfix, R.id.btnToolbar, R.id.btnWebView, R.id.btnProgress, R.id.btnMyListview, R.id.btnWave, R.id.noToolBar, R.id.btnCalendar, R.id.btnMCalendar, R.id.btnM, R.id.btnFly, R.id.btnNearBy, R.id.btnMatrix,
            R.id.btnHome, R.id.btnOnClick, R.id.statusBar, R.id.btnGo, R.id.btnOkHttp, R.id.btnOkHttp3, R.id.view, R.id.retrofit, R.id.btnHealth, R.id.btnHealthList})
    public void OnClick(View view) {

        switch (view.getId()) {
            case R.id.btnShareSdk:
                //分享sdk
                shareSdk();

                break;

            case R.id.btnAutoScroll:
                break;
            case R.id.btnPieChart:
                startActivity(new Intent(getActivity().getApplicationContext(), PieChartActivity.class));

                break;
            case R.id.btnVolleyTest:

                startActivity(new Intent(getActivity().getApplicationContext(), TestVolleyActivity.class));
                break;
            case R.id.btnRadioGroup:
                startActivity(new Intent(getActivity().getApplicationContext(), MyRadioGroupActivity.class));
                break;
            case R.id.btnAlbumList:
                startActivity(new Intent(getActivity().getApplicationContext(), AlbumListActivity.class));
                break;
            case R.id.btnHotFix:
                startActivity(new Intent(getActivity().getApplicationContext(), HotFixActivity.class));
                break;
            case R.id.btnSearchView:
                startActivity(new Intent(getActivity().getApplicationContext(), SearchViewActivity.class));
                break;
            case R.id.btnImageCrap:
                startActivity(new Intent(getActivity().getApplicationContext(), ImageCrapActivity.class));
                break;
            case R.id.flickerProgressBar:
                startActivity(new Intent(getContext(), FlickerProgressActivity.class));
                break;
            case R.id.btnWinLoad:
                startActivity(new Intent(getContext(), Win10ProgressActivity.class));
                break;
            case R.id.btnDashView:
                startActivity(new Intent(getContext(), DashBoardActivity.class));
                break;
            case R.id.btnTouchView:
                startActivity(new Intent(getContext(), GradientActivity.class));
                break;
            case R.id.btnQQslidingMenu:

                startActivity(new Intent(getContext(), QQSlidingActivity.class));
                break;
            case R.id.btnslidingMenu:

                startActivity(new Intent(getContext(), SlidingItemActivity.class));
                break;
            case R.id.btnAndfix:
                String str = "abcdefg123";
                String encodeStr = new String(Base64.encode(str.getBytes(), Base64.DEFAULT));
                LogUtils.e(TAG, "encodeStr===" + encodeStr);

                String decodeStr = new String(Base64.decode(encodeStr.getBytes(), Base64.DEFAULT));
                LogUtils.e(TAG, "decodeStr====" + decodeStr);

////                AndFix
//                PatchManager patchManager = BaseApplication.getInstantce().getPatchManager();
//                File patchFile = new File(Environment.getExternalStorageDirectory().getPath() + "/new.apatch");
//                Log.e(TAG, "patchFile=toString==" + patchFile.toString());
//                if (patchFile.exists()) {
//                    try {
//                        patchManager.addPatch(patchFile.getPath());
//                        ToastUtil.showLong(context, "andfix 合并patch 成功！！！");
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                break;
            case R.id.btnToolbar:

                gotoToolbarActivity();

                break;
            case R.id.btnTransform:

                startActivity(new Intent(getContext(), TransformActivity.class));
                break;
            case R.id.btnParallax:

                startActivity(new Intent(getContext(), SplashActivity.class));
                break;
            case R.id.btnProgress:
                startActivity(new Intent(context, MyProgressBarActivity.class));
                break;
            case R.id.btnWebView:
                startActivity(new Intent(context, MyWebActivity.class));
                break;
            case R.id.btnMyListview:
                startActivity(new Intent(context, MyListViewActivity.class));
                break;
            case R.id.btnMatrix:
                startActivity(new Intent(context, ConcatMatrixActivity.class));
                break;
            case R.id.btnNearBy:
                startActivity(new Intent(context, NearBySearchActivity.class));
                break;
            case R.id.btnMCalendar:
                startActivity(new Intent(context, McalendarActivity.class));
                break;
            case R.id.btnFly:

                startActivity(new Intent(context, FlyViewActivity.class));
                break;
            case R.id.btnM:

                startActivity(new Intent(context, MountainViewActivity.class));
                break;

            case R.id.btnCalendar:
                startActivity(new Intent(context, CalenderActivity.class));
                break;
            case R.id.noToolBar:
                startActivity(new Intent(context, NoBoringActionBarActivity.class));
                break;
            case R.id.btnWave:
                startActivity(new Intent(context, WaveActivity.class));
                break;
            case R.id.btnHome:
                context.startActivity(new Intent(context, HomeActivity.class));
                break;
            case R.id.btnHealthList:
                startActivity(new Intent(context, Transform3DActivity.class));

                break;

            case R.id.btnHealth:
                startActivity(new Intent(getActivity().getApplicationContext(), VolleyActivity.class));
//                RxUtil.subscribeAll(new Func1<String, Observable<Tngou>>() {
//                    @Override
//                    public Observable<Tngou> call(String s) {
//                        return ReUtil.getApiManager(AppContants.ZHIHU_HTTP).getInfoList();
//                    }
//                }, new Subscriber<Tngou>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(context, "onCompleted==", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(context, "onError==", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(Tngou tngou) {
//                        Toast.makeText(context, "infoClasses.size==" + tngou.infoList.size(), Toast.LENGTH_SHORT).show();
//                    }
//                });

                break;

            case R.id.retrofit:

                RxUtil.subscribeAll(new Func1<String, Observable<UserInfo>>() {
                    @Override
                    public Observable<UserInfo> call(String s) {

                        ReqUserInfoBean bean = new ReqUserInfoBean("a4fe0465b9464ae8fbl54da04bfd6e2f");

//                        Observable<UserInfo> observable =  ReUtil.getApiManager().getUserInfo(bean);
//                        return observable;
                        return ReUtil.getApiManager(AppContants.ZHIHU_HTTP).getUserInfo("a4fe0465b9464ae8fbl54da04bfd6e2f");
                    }
                }, new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(context, "onCompleted==", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "onError==", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        Toast.makeText(context, "userInfo==" + userInfo.getAccount(), Toast.LENGTH_SHORT).show();
                    }
                });


//
//                RxUtil.subscribeAll("", new Func1<String, Observable<Axiba>>() {
//                    @Override
//                    public Observable<Axiba> call(String s) {
//                        return ReUtil.getApiManager().getWeather("111", "Beijing");
//
//                    }
//                }, new Subscriber<Axiba>() {
//                    @Override
//                    public void onCompleted() {
//                        LogUtils.e(TAG, "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(Axiba axiba) {
//                        LogUtils.d(TAG, "axiba==" + axiba.getWeatherinfo().getCity() + "    |   " + axiba.getWeatherinfo().getTime());
//                        Toast.makeText(TestFragment.this, "axiba==" + axiba.getWeatherinfo().getCity(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });

//                Observable.just("","").flatMap(new Func1<String, Observable<Axiba>>() {
//                    @Override
//                    public Observable<Axiba> call(String s) {
////
////                        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.weather.com.cn")
////                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
////                        .addConverterFactory(GsonConverterFactory.create()).build();
////                         ApiManagerService service = retrofit.create(ApiManagerService.class);
////
////                        return  service.getWeather("111", "Beijing");
//                    return     ReUtil.getApiManager().getWeather("111", "Beijing");
//
//                    }
//                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeAll(new Subscriber<Axiba>() {
//                    @Override
//                    public void onCompleted() {
//                        LogUtils.e(TAG, "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(Axiba axiba) {
//                        LogUtils.d(TAG, "axiba==" + axiba.getWeatherinfo().getCity() + "    |   " + axiba.getWeatherinfo().getTime());
//                        Toast.makeText(TestFragment.this,"axiba==" + axiba.getWeatherinfo().getCity(),Toast.LENGTH_SHORT).show();
//                    }
//                });


                break;

            case R.id.view:


                try {
                    Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
                    Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
                    currentActivityThreadMethod.setAccessible(true);
                    Object currentActivityThread = currentActivityThreadMethod.invoke(null);
                    // 拿到原始的 mInstrumentation字段
                    Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
                    mInstrumentationField.setAccessible(true);
                    Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

                    // 创建代理对象
                    Instrumentation evilInstrumentation = new EvilInstrumentation(mInstrumentation);
                    // 偷梁换柱
                    mInstrumentationField.set(currentActivityThread, evilInstrumentation);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                context.startActivity(new Intent(context, CircleActivity.class));

                break;
            case R.id.btnDisscrollView:
                startActivity(new Intent(getActivity(), DisScrollActivity.class));
                break;
            case R.id.btnOkHttp3:


                OkHttpClientManager.Param[] params = new OkHttpClientManager.Param[2];

                OkHttpClientManager.Param param = new OkHttpClientManager.Param("cityId", "111");
                OkHttpClientManager.Param param1 = new OkHttpClientManager.Param("cityName", "Beijing");

                params[0] = param;
                params[1] = param1;

                OkHttpClientManager.postAsyn("http://www.weather.com.cn/data/sk/101010100.html"
                        , params, new OkHttpClientManager.ResultCallback<Axiba>() {
                            @Override
                            public void onError(Request request, Exception e) {

                                Log.e(TAG, "request=" + request + "-----e=" + e);
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Axiba response) {
                                Log.e(TAG, "response=" + response.getWeatherinfo().getCity() + "   " + response.getWeatherinfo().getWD());

                            }
                        }, null);
                break;

            case R.id.btnOkHttp:

                OkHttpClientManager.Param[] params2 = new OkHttpClientManager.Param[2];

                OkHttpClientManager.Param param2 = new OkHttpClientManager.Param("cityId", "111");
                OkHttpClientManager.Param param12 = new OkHttpClientManager.Param("cityName", "Beijing");

                params2[0] = param2;
                params2[1] = param12;

                OkHttpClientManager.postAsyn("http://www.weather.com.cn/data/sk/101010100.html", params2,
                        new OkHttpClientManager.ResultCallback<Axiba>() {
                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "request=" + request + "-----e=" + e);
                                e.printStackTrace();

                            }

                            @Override
                            public void onResponse(Axiba response) {
                                Log.e(TAG, "response=" + response.getWeatherinfo().getCity() + "   " + response.getWeatherinfo().getWD());
                            }
                        }
                );
                break;

            case R.id.btnOnClick:

                startActivity(new Intent(getContext(), MyRecyclerActivity.class));

                break;

            case R.id.btnGo:

                if (User.getInstantce().isLogin()) {
                    ShowInfoActivity.startActivity(context);
                } else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra(AppContants.NEED_CALLBACK, true);
                    startActivityForResult(intent, GO_TO_INFO);
                }

                break;
            case R.id.btnJs:
                ShowInfoActivity.startActivity(context);

        }

    }

    private void shareSdk() {

        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getActivity());

    }

    private void gotoToolbarActivity() {
        startActivity(new Intent(context, ToolbarTestActivity.class));
//        startActivity(new Intent(context, MyProgressBarActivity.class));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case GO_TO_INFO:
                    ShowInfoActivity.startActivity(context);
                    break;
            }
        }

    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }

}
