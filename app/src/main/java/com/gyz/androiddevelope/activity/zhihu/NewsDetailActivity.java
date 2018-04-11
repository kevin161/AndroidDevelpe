package com.gyz.androiddevelope.activity.zhihu;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.base.BaseApplication;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.net.retrofit.MySubscriber;
import com.gyz.androiddevelope.net.retrofit.ReUtil;
import com.gyz.androiddevelope.net.retrofit.RxUtil;
import com.gyz.androiddevelope.response_bean.NewsDetailBean;
import com.gyz.androiddevelope.response_bean.StoryExtraBean;
import com.gyz.androiddevelope.util.ImageUtils;
import com.gyz.androiddevelope.util.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author: guoyazhou
 * @date: 2016-03-17 11:40
 */
public class NewsDetailActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    private static final String TAG = "NewsDetailActivity";

    public static final String NEWS_ID = "news_id";
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbarLayout)
    AppBarLayout appbarLayout;
    @Bind(R.id.imgTitle)
    ImageView imgTitle;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.imgShare)
    ImageView imgShare;
    @Bind(R.id.imgComment)
    ImageView imgComment;
    @Bind(R.id.txtCommentCount)
    TextView txtCommentCount;
    @Bind(R.id.imgZan)
    ImageView imgZan;
    @Bind(R.id.txtZanCount)
    TextView txtZanCount;
    private int newsID;

    NewsDetailBean detailBean;

    public static void startActivity(Context context, int id) {
        context.startActivity(new Intent(context, NewsDetailActivity.class).putExtra(NEWS_ID, id));

    }

    @Override
    protected void initVariables() {
        newsID = getIntent().getIntExtra(NEWS_ID, 0);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        ShareSDK.initSDK(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        webView.getSettings().setDatabaseEnabled(true);
        // 开启Application Cache功能
        webView.getSettings().setAppCacheEnabled(true);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
        }

    }

    @Override
    protected void loadData() {
        RxUtil.subscribeAll(new Func1<String, Observable<NewsDetailBean>>() {
            @Override
            public Observable<NewsDetailBean> call(String s) {
                return ReUtil.getApiManager(AppContants.ZHIHU_HTTP).getNewsDetail(newsID);
            }
        }, new MySubscriber<NewsDetailBean>(this) {

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                //从数据库取出数据
                SQLiteDatabase database = BaseApplication.getInstantce().getWebCacheDbHelper().getReadableDatabase();
                Cursor cursor = database.rawQuery("select * from webCache where newsId = " + newsID, null);
                if (cursor.moveToFirst()) {
                    initWebContent(cursor.getString(cursor.getColumnIndex("newsId")));
                }
                cursor.close();
                database.close();
            }

            @Override
            public void onNext(NewsDetailBean newsDetailBean) {
                    super.onNext(newsDetailBean);
                txtTitle.setText(newsDetailBean.getTitle());
                detailBean = newsDetailBean;
                initWebContent(newsDetailBean.getBody());
                ImageUtils.loadImageByPicasso(getApplicationContext(),newsDetailBean.getImage(),imgTitle);
//                Picasso.with(getApplicationContext()).load(newsDetailBean.getImage()).into(imgTitle);
                //存入db
                SQLiteDatabase database = BaseApplication.getInstantce().getWebCacheDbHelper().getWritableDatabase();
                database.execSQL("replace into webCache(newsId,json) values( " + newsDetailBean.getId() + ",'" + newsDetailBean.getBody() + "')");
                database.close();
            }
        });
        //文章评论 点赞数
        RxUtil.subscribeAll(new Func1<String, Observable<StoryExtraBean>>() {
            @Override
            public Observable<StoryExtraBean> call(String s) {
                return ReUtil.getApiManager(AppContants.ZHIHU_HTTP).getNewsExtra(newsID);
            }
        }, new MySubscriber<StoryExtraBean>() {
            @Override
            public void onNext(StoryExtraBean bean) {
                txtCommentCount.setText(String.valueOf(bean.getComments()));
                txtZanCount.setText(String.valueOf(bean.getPopularity()));
            }
        });
    }

    private void initWebContent(String body) {

        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + body + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }


    @OnClick({R.id.imgShare, R.id.imgComment, R.id.imgZan})
    public void onClick(View view) {
        String msg = "";
        switch (view.getId()) {
            case R.id.imgShare:
                showShare(NewsDetailActivity.this,null,true);
                break;

            case R.id.imgComment:
                msg += "Click imgComment";
                break;

            case R.id.imgZan:

                msg += "Click imgZan";
                break;

        }
        if (!msg.equals("")) {
            Toast.makeText(NewsDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

//    private void shareAuth() {
//
//
//        ShareSDK.initSDK(NewsDetailActivity.this);
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//         if (detailBean==null){
//             return;
//         }
//// text是分享文本，所有平台都需要这个字段
//        oks.setText( detailBean.getTitle());
//// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
////oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//// url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl(detailBean.getShareUrl());
//// site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
//
//// 启动分享GUI
//        oks.show(NewsDetailActivity.this);
//
//    }


    /**
     * 演示调用ShareSDK执行分享
     *
     * @param context
     * @param platformToShare  指定直接分享平台名称（一旦设置了平台名称，则九宫格将不会显示）
     * @param showContentEdit  是否显示编辑页
     */
    public  void showShare(Context context, String platformToShare, boolean showContentEdit) {
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(!showContentEdit);
        if (platformToShare != null) {
            oks.setPlatform(platformToShare);
        }
        //ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
        oks.setTheme(OnekeyShareTheme.CLASSIC);
        // 令编辑页面显示为Dialog模式
        oks.setDialogMode();
        // 在自动授权时可以禁用SSO方式
        oks.disableSSOWhenAuthorize();
        //oks.setAddress("12345678901"); //分享短信的号码和邮件的地址
        oks.setTitle(detailBean.getTitle());
        oks.setTitleUrl("http://mob.com");
        oks.setText("test");
        //oks.setImagePath("/sdcard/test-pic.jpg");  //分享sdcard目录下的图片
        oks.setImageUrl(detailBean.getImage());
        oks.setUrl(detailBean.getShareUrl()); //微信不绕过审核分享链接h
        //filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供
        oks.setFilePath(AppContants.FILE_PATH);  //filePath用于视频分享
       // oks.setComment("test"); //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
        oks.setSite(context.getString(R.string.app_name));  //QZone分享完之后返回应用时提示框上显示的名称
        oks.setSiteUrl("http://mob.com");//QZone分享参数
//        oks.setVenueName("ShareSDK");
//        oks.setVenueDescription("This is a beautiful place!");
//        oks.setLatitude(23.169f);
//        oks.setLongitude(112.908f);
        // 将快捷分享的操作结果将通过OneKeyShareCallback回调
        // oks.setCallback(new OneKeyShareCallback());
        // 去自定义不同平台的字段内容
        // oks.setShareContentCustomizeCallback(new
        // ShareContentCustomizeDemo());
        // 在九宫格设置自定义的图标
//        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
//        String label = "ShareSDK";
//        View.OnClickListener listener = new View.OnClickListener() {
//            public void onClick(View v) {
//
//            }
//        };
//        oks.setCustomerLogo(logo, label, listener);

        // 为EditPage设置一个背景的View
        //oks.setEditPageBackground(getPage());
        // 隐藏九宫格中的新浪微博
        // oks.addHiddenPlatform(SinaWeibo.NAME);

        // String[] AVATARS = {
        // 		"http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg",
        // 		"http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339485237265.jpg",
        // 		"http://diy.qqjay.com/u/files/2012/0523/f466c38e1c6c99ee2d6cd7746207a97a.jpg",
        // 		"http://diy.qqjay.com/u2/2013/0422/fadc08459b1ef5fc1ea6b5b8d22e44b4.jpg",
        // 		"http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339510584349.jpg",
        // 		"http://diy.qqjay.com/u2/2013/0401/4355c29b30d295b26da6f242a65bcaad.jpg" };
        // oks.setImageArray(AVATARS);              //腾讯微博和twitter用此方法分享多张图片，其他平台不可以

        // 启动分享
        oks.show(context);
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
