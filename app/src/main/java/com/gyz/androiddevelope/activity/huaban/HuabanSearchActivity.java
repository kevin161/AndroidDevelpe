package com.gyz.androiddevelope.activity.huaban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.adapter.SearchArrayAdapter;
import com.gyz.androiddevelope.base.BaseApplication;
import com.gyz.androiddevelope.base.BaseToolbarActivity;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.net.retrofit.ReUtil;
import com.gyz.androiddevelope.net.retrofit.RxUtil;
import com.gyz.androiddevelope.response_bean.SearchHintBean;
import com.gyz.androiddevelope.util.UserUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.huaban.HuabanSearchActivity.java
 * @author: ZhaoHao
 * @date: 2016-06-27 17:06
 */
public class HuabanSearchActivity extends BaseToolbarActivity {
    private static final String TAG = "HuabanSearchActivity";
    private List<String> mListHttpHint = new ArrayList<>();

    private SearchView searchView;
    private SearchArrayAdapter mAdapter;
    SearchView.SearchAutoComplete searchAutoComplete;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, HuabanSearchActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_huaban_search, menu);
        setSearchView(menu);

        return true;
    }

    private void setSearchView(Menu menu) {
        //SearchView在Menu里面
        MenuItem item = menu.findItem(R.id.action_search_view);
        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        //进来就呈现搜索框并且不能被隐藏
        searchView.setIconifiedByDefault(false);
        mAdapter = new SearchArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, mListHttpHint);
        searchAutoComplete.setAdapter(mAdapter);

        SearchView.SearchAutoComplete ss = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        ss.setHint("输入类型···");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                //监听用户输入   用做提示
                RxUtil.subscribeOnNext(new Func1<String, Observable<SearchHintBean>>() {
                    @Override
                    public Observable<SearchHintBean> call(String s) {
                        return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsSearHintBean(UserUtils.getAuthorizations(
                                BaseApplication.getInstantce().isLogin(), getBaseContext()), newText);
                    }
                }, new Action1<SearchHintBean>() {
                    @Override
                    public void call(SearchHintBean searchHintBean) {
                        mListHttpHint.clear();
                        //List<String>
                        mListHttpHint.addAll(searchHintBean.getResult());
                        mAdapter.notifyDataSetChanged();
                    }
                });

                return false;
            }
        });


    }

    @Override
    protected void loadData() {

    }

    /**
     * 描述当前页面的title--便于友盟统计
     */
    @Override
    protected String currActivityName() {
        return null;
    }
}
