package com.gyz.androiddevelope.activity.huaban;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.fragment.huaban.HuaBanBoardFragment;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.huaban.HuaBanBoardActivity.java
 * @author: ZhaoHao
 * @date: 2016-06-21 16:18
 */
public class HuaBanBoardActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "HuaBanBoardActivity";
    public static final String TYPE_ID = "type_id";
    private static final String TYPE_NAME = "type_name";

    private String mBoardName;
    private String mBoardId;


    public static void startActivity(Context context, String mBoardId, String mBoardName) {
        Intent intent = new Intent(context, HuaBanBoardActivity.class);
        intent.putExtra(TYPE_ID, mBoardId);
        intent.putExtra(TYPE_NAME, mBoardName);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        mBoardId = getIntent().getStringExtra(TYPE_ID);
        mBoardName = getIntent().getStringExtra(TYPE_NAME);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_huaban_board);
//        getAppBar().setBackImage(R.mipmap.ic_back);

        HuaBanBoardFragment fragment = new HuaBanBoardFragment();
        Bundle bundle = new Bundle();
        bundle.putString(HuaBanBoardActivity.TYPE_ID,mBoardId);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }

    @Override
    protected void loadData() {
    }


    /**
     * 描述当前页面的title--便于友盟统计
     */
    @Override
    protected String currActivityName() {
        return mBoardName;
    }

}
