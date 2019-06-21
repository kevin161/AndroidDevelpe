package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.adapter.RvAdapter;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.response_bean.InfoBean;
import com.gyz.androiddevelope.view.NestedScrollingDetailContainer;
import com.gyz.androiddevelope.view.NestedScrollingWebView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @version V3.0
 * @FileName: com.gyz.androiddevelope.activity.custom.NestedScrollViewActivity.java
 * @author: GYZ
 * asia161@qq.com
 * @date: 2019-06-21 08:53
 */
public class NestedScrollViewActivity extends BaseActivity {

    @Bind(R.id.web_container)
    NestedScrollingWebView webContainer;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.nested_container)
    NestedScrollingDetailContainer nestedContainer;

    @Override
    protected void initVariables() {




    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_nested_scroll);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

        initWebView();
        initRecyclerView();
    }

    private void initWebView() {
        webContainer.getSettings().setJavaScriptEnabled(true);
        webContainer.setWebViewClient(new WebViewClient());
        webContainer.setWebChromeClient(new WebChromeClient());
        webContainer.loadUrl("https://github.com/wangzhengyi/Android-NestedDetail");
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
        List<InfoBean> data = getCommentData();
        RvAdapter rvAdapter = new RvAdapter(this, data);
        rvList.setAdapter(rvAdapter);
    }


    private List<InfoBean> getCommentData() {
        List<InfoBean> commentList = new ArrayList<>();
        InfoBean titleBean = new InfoBean();
        titleBean.type = InfoBean.TYPE_TITLE;
        titleBean.title = "评论列表";
        commentList.add(titleBean);
        for (int i = 0; i < 40; i++) {
            InfoBean contentBean = new InfoBean();
            contentBean.type = InfoBean.TYPE_ITEM;
            contentBean.title = "评论标题" + i;
            contentBean.content = "评论内容" + i;
            commentList.add(contentBean);
        }
        return commentList;
    }
}
