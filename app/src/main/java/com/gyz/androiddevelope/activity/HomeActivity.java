package com.gyz.androiddevelope.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.activity.custom.SettingsActivity;
import com.gyz.androiddevelope.activity.huaban.LoginActivity;
import com.gyz.androiddevelope.activity.huaban.UserActivity;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.base.BaseApplication;
import com.gyz.androiddevelope.base.BaseFragment;
import com.gyz.androiddevelope.base.BaseRecyclerFragment;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.fragment.TestFragment;
import com.gyz.androiddevelope.fragment.Tngou.TngouFragment;
import com.gyz.androiddevelope.fragment.huaban.HuabanFragment;
import com.gyz.androiddevelope.fragment.zhihu.ZhiHu2Fragment;
import com.gyz.androiddevelope.listener.OnRecyclerRefreshListener;
import com.gyz.androiddevelope.listener.OnSwipeRefreshFragmentListener;
import com.gyz.androiddevelope.util.ImageLoadFresco;
import com.gyz.androiddevelope.util.SPUtils;
import com.gyz.androiddevelope.util.SnackbarUtils;
import com.tencent.bugly.Bugly;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnSwipeRefreshFragmentListener, View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.contain_home)
    FrameLayout containHome;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    protected static final int[] ints = new int[]{R.color.colorPrimaryDark};
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    SimpleDraweeView imgHead;
    TextView tvUserName, tvUserInfo;

    private BaseFragment zhihuFragment, tngouPicFragment, testFragment, huabanFragment;
    long firstTime = 0;

    //刷新的接口 子Fragment实现
    private OnRecyclerRefreshListener mListenerRefresh;
    private String mUserName;
    private String mUserId;
    private boolean isLogin;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Bugly.init(getApplicationContext(),AppContants.BUGLY_APP_ID,AppContants.isDebug);

        getSwipeBackLayout().setEnableGesture(false);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View headView = navigationView.getHeaderView(0);
        imgHead = ButterKnife.findById(headView, R.id.imageView);
        tvUserName = ButterKnife.findById(headView, R.id.tvUserName);
        tvUserInfo = ButterKnife.findById(headView, R.id.textView);

        imgHead.setOnClickListener(this);

        swipeRefreshLayout.setColorSchemeResources(ints);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mListenerRefresh.onRecyclerRefresh();
            }
        });

        zhihuFragment = new ZhiHu2Fragment();
        switchFragment(zhihuFragment);

    }

    @Override
    protected void loadData() {
        isLogin = BaseApplication.getInstantce().isLogin();
        if (isLogin) {
            mUserName = (String) SPUtils.get(this, AppContants.USERNAME, "");
            mUserId = (String) SPUtils.get(this, AppContants.USERID, "");
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                SnackbarUtils.showSnackBar(getBaseContext(), containHome, R.string.exit_app);
                firstTime = secondTime;
            } else {
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            SettingsActivity.startActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            switchFragment(zhihuFragment);
            toolbar.setTitle(getString(R.string.title_zhihu));

        } else if (id == R.id.nav_test) {

            if (testFragment == null)
                testFragment = new TestFragment();
            switchFragment(testFragment);

        } else if (id == R.id.nav_picshow) {

            if (tngouPicFragment == null)
                tngouPicFragment = new TngouFragment();
            switchFragment(tngouPicFragment);
            toolbar.setTitle(getString(R.string.title_tngou));

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
//            if (huabanFragment == null) {
            huabanFragment = new HuabanFragment();
//            }


            Bundle bundle = new Bundle();
            bundle.putString(HuabanFragment.KEY, "all");
            huabanFragment.setArguments(bundle);
            switchFragment(huabanFragment);
            toolbar.setTitle("花瓣");

        } else if (id == R.id.navTest) {

            huabanFragment = new HuabanFragment();
            Bundle bundle = new Bundle();
            bundle.putString(HuabanFragment.KEY, "beauty");
            huabanFragment.setArguments(bundle);
            switchFragment(huabanFragment);
            toolbar.setTitle("花瓣-美女");
        } else if (id == R.id.huaban_food) {

            huabanFragment = new HuabanFragment();
            Bundle bundle = new Bundle();
            bundle.putString(HuabanFragment.KEY, "food_drink");
            huabanFragment.setArguments(bundle);
            switchFragment(huabanFragment);
            toolbar.setTitle("花瓣-美食");
        } else if (id == R.id.huaban_photo) {

            huabanFragment = new HuabanFragment();
            Bundle bundle = new Bundle();
            bundle.putString(HuabanFragment.KEY, "photography");
            huabanFragment.setArguments(bundle);
            switchFragment(huabanFragment);
            toolbar.setTitle("花瓣-摄影");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    protected void switchFragment(BaseFragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (fragment != null) {
            if (fragment instanceof BaseRecyclerFragment) {
                mListenerRefresh = (OnRecyclerRefreshListener) fragment;
            }
            ft.replace(R.id.contain_home, fragment);
        }
        ft.commit();
    }

    @Override
    public void OnRefreshState(boolean isRefreshing) {
        swipeRefreshLayout.setRefreshing(isRefreshing);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                if (BaseApplication.getInstantce().isLogin()) {
                    UserActivity.startActivity(HomeActivity.this, mUserId, mUserName);
                } else {
                    LoginActivity.startActivity(HomeActivity.this);
                }
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setNavUserInfo();
    }

    private void setNavUserInfo() {
        if (isLogin) {
            String key = (String) SPUtils.get(this, AppContants.USERHEADKEY, "");
            if (!TextUtils.isEmpty(key)) {
                key =  getResources().getString(R.string.url_image_general,key) ;
                new ImageLoadFresco.LoadImageFrescoBuilder(this, imgHead, key)
                        .setIsCircle(true, true)
                        .build();
            }

            if (!TextUtils.isEmpty(mUserName)) {
                tvUserName.setText(mUserName);
            }

            String email = (String) SPUtils.get(this, AppContants.USEREMAIL, "");
            if (!TextUtils.isEmpty(email)) {
                tvUserInfo.setText(email);
            }
        }
    }
}
