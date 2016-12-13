package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.adapter.BaseRecyclerAdapter;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.EleListActivity.java
 * @author: ZhaoHao
 * @date: 2016-12-12 17:19
 */
public class EleListActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "EleListActivity";
    @Bind(R.id.eleRecyclerView)
    RecyclerView eleRecyclerView;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_ele_list);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {


    }

    @Override
    protected String currActivityName() {
        return "饿了别叫妈！";
    }


    class ListAdapter extends RecyclerView.Adapter {


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class Holder extends RecyclerView.ViewHolder {
            public Holder(View itemView) {
                super(itemView);
            }
        }
    }
}
