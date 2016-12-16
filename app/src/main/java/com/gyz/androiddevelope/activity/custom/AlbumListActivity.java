package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.listener.ItemRemovedListener;
import com.gyz.androiddevelope.manager.AlbumLayoutManager;
import com.gyz.androiddevelope.view.SwipeCardRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 仿美图详情页 手势画廊 RecyclerView实现
 *
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.AlbumListActivity.java
 * @author: ZhaoHao
 * @date: 2016-12-12 17:19
 */
public class AlbumListActivity extends AppCompatActivity {
    private static final String TAG = "AlbumListActivity";
    @Bind(R.id.swipeCardRecyclerView)
    SwipeCardRecyclerView swipeCardRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        ButterKnife.bind(this);
        swipeCardRecyclerView.setLayoutManager(new AlbumLayoutManager());
        list = initData();
        swipeCardRecyclerView.setAdapter(new ListAdapter(list));
        swipeCardRecyclerView.setRemovedListener(new ItemRemovedListener() {
            @Override
            public void onRightRemoved() {
                Toast.makeText(AlbumListActivity.this, list.get(list.size() - 1) +
                        " was right removed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLeftRemoved() {
                Toast.makeText(AlbumListActivity.this, list.get(list.size() - 1) +
                        " was left removed", Toast.LENGTH_SHORT).show();

            }
        });
    }


    List list;

    protected List<String> initData() {
        List mDatas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mDatas.add("" + i);
        }
        return mDatas;
    }


    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {

        private List<String> mDatas;

        public ListAdapter(List<String> mDatas) {
            this.mDatas = mDatas;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            Holder holder = new Holder(LayoutInflater.from(AlbumListActivity.this)
                    .inflate(R.layout.item_album_list, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.textView.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        public void delTopItem() {
            int position = this.getItemCount() - 1;
            mDatas.remove(position);
            this.notifyItemRemoved(position);
        }

        public class Holder extends RecyclerView.ViewHolder {
            TextView textView;

            public Holder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.txtView);
            }
        }
    }
}
