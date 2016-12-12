package com.gyz.androiddevelope.activity.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowInfoActivity extends BaseActivity {

    @Bind(R.id.webView)
    WebView mWebView;

    public static void startActivity(Context context) {

        Intent intent = new Intent(context, ShowInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_do_sth);
        ButterKnife.bind(this);

        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        mWebView.getSettings().setUserAgentString("Yonglibao.com From A");


        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
            /*设置网页显示效果为pc端布局，不进行自适应操作*/
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mWebView.getSettings().setDisplayZoomControls(false);//设定缩放控件隐藏
        }

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
    }

    @Override
    protected void loadData() {
        //加载本地asset文件夹html
        mWebView.loadUrl("file:///android_asset/htf3.html");
    }

    /**
     * 自定义的Android代码和JavaScript代码之间的桥梁类
     *
     * @author 1
     */
    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        // 如果target 大于等于API 17，则需要加上如下注解
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
        }
    }
}
