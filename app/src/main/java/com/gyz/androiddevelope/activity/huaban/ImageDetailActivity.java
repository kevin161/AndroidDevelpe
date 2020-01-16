package com.gyz.androiddevelope.activity.huaban;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.base.BaseApplication;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.fragment.huaban.HuaBanRecommendListFragment;
import com.gyz.androiddevelope.net.retrofit.ReUtil;
import com.gyz.androiddevelope.net.retrofit.RxUtil;
import com.gyz.androiddevelope.response_bean.LikePinsOperateBean;
import com.gyz.androiddevelope.response_bean.PinsMainEntity;
import com.gyz.androiddevelope.service.DownloadService;
import com.gyz.androiddevelope.util.AnimatorUtils;
import com.gyz.androiddevelope.util.ImageLoadFresco;
import com.gyz.androiddevelope.util.ImageUtils;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.util.SnackbarUtils;
import com.gyz.androiddevelope.util.UserUtils;
import com.gyz.androiddevelope.util.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.huaban.ImageDetailActivity.java
 * @author: ZhaoHao
 * @date: 2016-06-20 10:14
 */
public class ImageDetailActivity extends BaseActivity {
    private static final String TAG = "ImageDetailActivity";
    public static final String IMAGE_BEAN = "img_bean";

    @Bind(R.id.imgBig)
    SimpleDraweeView imgBig;
    @Bind(R.id.imgToolbar)
    Toolbar imgToolbar;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.appbarLayout)
    AppBarLayout appbarLayout;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    private PinsMainEntity pinsMainEntity;
    public String mImageUrl;//图片地址
    public String mImageType;//图片类型
    public String mPinsId;

    private boolean isLike = false;//该图片是否被喜欢操作 默认false 没有被操作过
    private boolean isGathered = false;//该图片是否被采集过
//
//    @BindDrawable(R.drawable.ic_cancel_black_24dp)
//    Drawable mDrawableCancel;
//    @BindDrawable(R.drawable.ic_refresh_black_24dp)
//    Drawable mDrawableRefresh;

    public static void startActivity(Context context, PinsMainEntity bean) {

        Intent intent = new Intent(context, ImageDetailActivity.class);
        intent.putExtra(IMAGE_BEAN, bean);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void initVariables() {
        pinsMainEntity = (PinsMainEntity) getIntent().getParcelableExtra(IMAGE_BEAN);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

        setSupportActionBar(imgToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        imgToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mImageUrl = pinsMainEntity.getFile().getKey();
        mImageType = pinsMainEntity.getFile().getType();
        mPinsId = pinsMainEntity.getPin_id();
        isLike = pinsMainEntity.isLiked();

        //设置图片宽高比
        imgBig.setAspectRatio(Utils.getAspectRatio(pinsMainEntity.getFile().getWidth(), pinsMainEntity.getFile().getHeight()));

        //    设置fragent
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, HuaBanRecommendListFragment.newInstance(mPinsId)).commit();


    }

    @Override
    protected void loadData() {
        showImage();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        setIconDrawable(menu.findItem(R.id.action_like), isLike);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_image_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_like:
                actionLike(item);
                break;

            case R.id.action_download:
              actionDownLoad(item);
                break;

            case R.id.action_gather:
                actionGather(item);
                break;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showImage() {
        if (pinsMainEntity == null)
            return;
        final ObjectAnimator objectAnimator;
        if (ImageUtils.checkIsGif(mImageType)){
            objectAnimator = AnimatorUtils.getRotationFS(floatingActionButton);
            objectAnimator.start();
        }else {
            objectAnimator = null;
            AnimatorSet set =  AnimatorUtils.getScale(floatingActionButton);
            set.setStartDelay(200);
            set.start();
        }

        String url = String.format(getString(R.string.url_image_big), mImageUrl);
        String urlLow = String.format(getString(R.string.url_image_small), mImageUrl);

        new ImageLoadFresco.LoadImageFrescoBuilder(getBaseContext(), imgBig, url)
                .setUrlLow(urlLow)
                .setRetryImage(getDrawable(R.drawable.ic_refresh_black_24dp))
                .setFailureImage(getDrawable(R.drawable.ic_cancel_black_24dp))
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        //图片加载成功回调
                        super.onFinalImageSet(id, imageInfo, animatable);

                        if (animatable != null) {
                            animatable.start();
                        }
                        // 停止fab旋转
                        if (objectAnimator != null && objectAnimator.isRunning()) {
                            imgBig.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    objectAnimator.cancel();
                                }
                            },600);
                        }
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        //失败的回调
                        super.onFailure(id, throwable);
                        LogUtils.e(throwable.toString());
                    }
                }).build();
    }


    private void setIconDrawable(MenuItem item, boolean isLike) {
        item.setIcon(isLike ? R.drawable.ic_favorite_accent_24dp : R.drawable.ic_favorite_white_24dp);
    }

    private void actionLike(final MenuItem item) {

        //根据当前值 取操作符
        final String operate = isLike ? AppContants.OPERATEUNLIKE : AppContants.OPERATELIKE;

        RxUtil.subscribeOnNext(new Func1<String, Observable<LikePinsOperateBean>>() {
            @Override
            public Observable<LikePinsOperateBean> call(String s) {
                return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsLikeOperate(UserUtils.getAuthorizations(
                        BaseApplication.getInstantce().isLogin(), getApplicationContext()), mPinsId, operate);
            }
        }, new Action1<LikePinsOperateBean>() {
            @Override
            public void call(LikePinsOperateBean likePinsOperateBean) {
                isLike = !isLike;
                SnackbarUtils.showSnackBar(ImageDetailActivity.this,frameLayout,isLike? R.string.is_like: R.string.not_like).show();
                setIconDrawable(item, isLike);
            }
        });
    }

    private void actionGather(MenuItem item) {

    }

    private void actionDownLoad(MenuItem item) {
        DownloadService.launch(ImageDetailActivity.this,mImageUrl,mImageType);
    }
}
