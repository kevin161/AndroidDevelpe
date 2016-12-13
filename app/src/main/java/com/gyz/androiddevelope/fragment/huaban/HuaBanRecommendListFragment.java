package com.gyz.androiddevelope.fragment.huaban;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.activity.huaban.HuaBanBoardActivity;
import com.gyz.androiddevelope.activity.huaban.ImageDetailActivity;
import com.gyz.androiddevelope.activity.huaban.UserActivity;
import com.gyz.androiddevelope.adapter.HuabanRecyclerAdapter;
import com.gyz.androiddevelope.base.BaseApplication;
import com.gyz.androiddevelope.adapter.BaseRecyclerAdapter;
import com.gyz.androiddevelope.base.BaseRecyclerFragment;
import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.net.retrofit.MySubscriber;
import com.gyz.androiddevelope.net.retrofit.ReUtil;
import com.gyz.androiddevelope.net.retrofit.RxUtil;
import com.gyz.androiddevelope.response_bean.PinsDetailBean;
import com.gyz.androiddevelope.response_bean.PinsMainEntity;
import com.gyz.androiddevelope.util.CompatUtils;
import com.gyz.androiddevelope.util.DateUtil;
import com.gyz.androiddevelope.util.ImageLoadFresco;
import com.gyz.androiddevelope.util.UserUtils;

import java.util.List;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.fragment.huaban.HuaBanRecommendListFragment.java
 * @author: ZhaoHao
 * @date: 2016-06-20 16:00
 */
public class HuaBanRecommendListFragment extends BaseRecyclerFragment implements View.OnClickListener {
    private static final String TAG = "HuaBanRecommendListFragment";
    public static final String PinsId = "pinId";

    private String pinId;
    private HuabanRecyclerAdapter adapter;
    private int page = 1;
    private TextView tv_image_text, tv_image_link, tv_image_gather, tv_image_like, tv_image_user, tv_image_time, tv_image_board;
    private SimpleDraweeView img_image_user, img_image_board_1, img_image_board_2, img_image_board_3, img_image_board_4;
    ImageButton ibtn_image_board_chevron_right, ibtn_image_user_chevron_right;
    RelativeLayout mRLImageUser;//用户信息的父视图
    RelativeLayout mRLImageBoard;//画板信息的父视图
//    @BindString(R.string.url_image_small)
//    String mFormatUrlSmall;
//    @BindString(R.string.txt_no_describe)
//    String mStringNullDescribe;
//
//    @BindString(R.string.text_gather_number)
//    String mStringGatherNumber;
//    @BindString(R.string.text_like_number)
//    String mStringLikeNumber;
    private String mBoardName, mBoardId, mUserId, mUserName;

    public static Fragment newInstance(String mPinsId) {

        HuaBanRecommendListFragment fragment = new HuaBanRecommendListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PinsId, mPinsId);
        fragment.setArguments(bundle);

        return fragment;
    }

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

        adapter = new HuabanRecyclerAdapter(context);
        adapter.setRecyclerAdapterListener(new HuabanRecyclerAdapter.RecyclerAdapterListener() {
            @Override
            public void onClickImage(PinsMainEntity bean, View view) {
                ImageDetailActivity.startActivity(context, bean);
            }

            @Override
            public void onClickTitleInfo(PinsMainEntity bean, View view) {
                ImageDetailActivity.startActivity(context, bean);
            }

            @Override
            public void onClickInfoGather(PinsMainEntity bean, View view) {
//                TODO
            }

            @Override
            public void onClickInfoLike(PinsMainEntity bean, View view) {
            }
        });

        View view = LayoutInflater.from(context).inflate(R.layout.view_img_detail, getRecyclerView(), false);
        findHeadView(view);
        adapter.setHeaderView(view);

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

    /**
     * @return 获取recyclerView的layoutmanager
     */
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void getBundleDatas(Bundle bundle) {
        pinId = bundle.getString(PinsId);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        requestData(false);
        requestPinDetail();
    }

    @Override
    public String getTitle() {
        return null;
    }

    private void requestPinDetail() {

        RxUtil.subscribeAll(new Func1<String, Observable<PinsDetailBean>>() {
            @Override
            public Observable<PinsDetailBean> call(String s) {
                return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpsPinsDetailRx(UserUtils.getAuthorizations(BaseApplication.getInstantce().isLogin(), context), pinId);
            }
        }, new MySubscriber<PinsDetailBean>(){

            @Override
            public void onNext(PinsDetailBean o) {
                super.onNext(o);
                if (o.getErr() == AppContants.HUABAN_ERR_NUM)
                    return;
                setHeadViewInfo(o);
            }
        });
    }

    /**
     * 使用网络数据填充UI控件 赋值全局变量 填充UI控件
     *
     * @param pinsDetailBean 网络bean
     */
    private void setHeadViewInfo(PinsDetailBean pinsDetailBean) {
        PinsDetailBean.PinBean bean = pinsDetailBean.getPin();

        mBoardId = String.valueOf(bean.getBoard_id());
        mUserId = String.valueOf(bean.getUser_id());
        mUserName = bean.getUser().getUrlname();
        mBoardName = bean.getBoard().getTitle();

        //描述
        setImageTextInfo(bean.getRaw_text(),
                bean.getLink(),
                bean.getSource(),
                bean.getRepin_count(),
                bean.getLike_count()
        );

        //用户信息
        String url = bean.getUser().getAvatar().getKey();

        setImageUserInfo(url,
                bean.getUser().getUsername(),
                bean.getCreated_at()
        );

        //画板信息
        String url1 =getString(R.string.url_image_small,bean.getBoard().getPins().get(0).getFile().getKey()) ;
        String url2 = getString(R.string.url_image_small,bean.getBoard().getPins().get(1).getFile().getKey()) ;
        String url3 = getString(R.string.url_image_small,bean.getBoard().getPins().get(2).getFile().getKey()) ;
        String url4 = getString(R.string.url_image_small,bean.getBoard().getPins().get(3).getFile().getKey()) ;
        setImageBoardInfo(url1, url2, url3, url4, bean.getBoard().getTitle());
    }

    private void requestData(final boolean isAdd) {

        if (isAdd) {
            page++;
        } else {
            page = 1;
        }

        RxUtil.subscribeAll(new Func1<String, Observable<List<PinsMainEntity>>>() {
            @Override
            public Observable<List<PinsMainEntity>> call(String s) {
                return ReUtil.getApiManager(AppContants.HUABAN_HTTP).httpPinsRecommendRx(UserUtils.getAuthorizations(BaseApplication.getInstantce().isLogin(), context), pinId, page, AppContants.LIMIT);
            }
        }, new Subscriber<List<PinsMainEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<PinsMainEntity> pinsMainEntities) {
                if (!isAdd) {
                    adapter.clearDatas();
                }
                adapter.addDatas(pinsMainEntities);
            }
        });
    }


    private void findHeadView(View headView) {
        tv_image_text = ButterKnife.findById(headView, R.id.tv_image_text);
        tv_image_link = ButterKnife.findById(headView, R.id.tv_image_link);
        tv_image_gather = ButterKnife.findById(headView, R.id.tv_image_gather);
        tv_image_like = ButterKnife.findById(headView, R.id.tv_image_like);
        tv_image_user = ButterKnife.findById(headView, R.id.tv_image_user);
        tv_image_time = ButterKnife.findById(headView, R.id.tv_image_about);
        tv_image_board = ButterKnife.findById(headView, R.id.tv_image_board);

        img_image_user = ButterKnife.findById(headView, R.id.img_image_user);
        img_image_board_1 = ButterKnife.findById(headView, R.id.img_image_board_1);
        img_image_board_2 = ButterKnife.findById(headView, R.id.img_image_board_2);
        img_image_board_3 = ButterKnife.findById(headView, R.id.img_image_board_3);
        img_image_board_4 = ButterKnife.findById(headView, R.id.img_image_board_4);

        ibtn_image_board_chevron_right = ButterKnife.findById(headView, R.id.ibtn_image_board_chevron_right);
        ibtn_image_user_chevron_right = ButterKnife.findById(headView, R.id.ibtn_image_user_chevron_right);

        mRLImageUser = ButterKnife.findById(headView, R.id.relativelayout_image_user);
        mRLImageBoard = ButterKnife.findById(headView, R.id.relativelayout_image_board);

        tv_image_link.setOnClickListener(this);
        mRLImageUser.setOnClickListener(this);
        mRLImageBoard.setOnClickListener(this);
        setViewDrawable();
    }

    private void setViewDrawable() {
        initTintDrawable(tv_image_gather, R.drawable.ic_camera_black_24dp);
        initTintDrawable(tv_image_like, R.drawable.ic_favorite_black_24dp);
        initTintDrawable(ibtn_image_user_chevron_right, R.drawable.ic_chevron_right_black_36dp);
        initTintDrawable(ibtn_image_board_chevron_right, R.drawable.ic_chevron_right_black_36dp);
        initTintDrawable(tv_image_link, R.drawable.ic_insert_link_black_24dp);
    }

    private void initTintDrawable(View view, int resId) {

        if (view instanceof ImageButton) {
            ((ImageButton) view).setImageDrawable
                    (CompatUtils.getTintListDrawable(context, resId, R.color.tint_list_grey));
            return;
        }
        if (view instanceof TextView) {
            ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(
                    CompatUtils.getTintListDrawable(context, resId, R.color.tint_list_grey),
                    null,
                    null,
                    null);

        }
    }


    //图像文字信息 填充
    private void setImageTextInfo(String raw, String link, String source, int gather, int like) {
        if (!TextUtils.isEmpty(raw)) {
            tv_image_text.setText(raw);
        } else {
            tv_image_text.setText(R.string.txt_no_describe);
        }

        if ((!TextUtils.isEmpty(link)) && (!TextUtils.isEmpty(source))) {
            tv_image_link.setText(source);
            tv_image_link.setTag(link);
        } else {
            tv_image_link.setVisibility(View.GONE);
        }

        tv_image_gather.setText(getString(R.string.text_gather_number,gather));
        setTvImageLikeNumber(like);
    }

    private void setTvImageLikeNumber(int like) {
        tv_image_like.setText(getString(R.string.text_like_number,like));
    }

    //图像的用户信息 填充
    private void setImageUserInfo(String url_head, String username, int created_time) {
        //因为图片来源不定 需要做处理
        if (url_head != null) {
            if (!url_head.contains("http://")) {
                url_head = getString(R.string.url_image_small,url_head);
            }
            //用户名头像加载
            new ImageLoadFresco.LoadImageFrescoBuilder(context, img_image_user, url_head)
                    .setIsCircle(true)
                    .build();
        }

        //用户名
        tv_image_user.setText(username);
        //创建时间
        tv_image_time.setText(DateUtil.getTimeDifference(created_time, System.currentTimeMillis()));
    }

    private void setImageBoardInfo(String url1, String url2, String url3, String url4, String board_name) {
        //画板名称
        if (!TextUtils.isEmpty(board_name)) {
            tv_image_board.setText(board_name);
        } else {
            tv_image_board.setText("暂无画板信息");
        }

        new ImageLoadFresco.LoadImageFrescoBuilder(context, img_image_board_1, url1)
                .setIsRadius(true, 5)
                .build();
        new ImageLoadFresco.LoadImageFrescoBuilder(context, img_image_board_2, url2)
                .setIsRadius(true, 5)
                .build();
        new ImageLoadFresco.LoadImageFrescoBuilder(context, img_image_board_3, url3)
                .setIsRadius(true, 5)
                .build();
        new ImageLoadFresco.LoadImageFrescoBuilder(context, img_image_board_4, url4)
                .setIsRadius(true, 5)
                .build();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_image_link:

// TODO
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                //点击图片链接的回调
//                //打开选择浏览器 再浏览界面
//                Uri uri = Uri.parse(tv_image_link.getText().toString());
//                intent.setData(uri);
//                context.startActivity(intent);

                break;

            case R.id.relativelayout_image_user:
                UserActivity.startActivity(context,mUserId,mUserName);
                break;

            case R.id.relativelayout_image_board:
                HuaBanBoardActivity.startActivity(context, mBoardId, mBoardName);
                break;
        }
    }

}
