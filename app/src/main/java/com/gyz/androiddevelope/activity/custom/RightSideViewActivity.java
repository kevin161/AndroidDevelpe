package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.response_bean.CityInfo;
import com.gyz.androiddevelope.util.CharacterParser;
import com.gyz.androiddevelope.view.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @version V3.0
 * @FileName: com.gyz.androiddevelope.activity.custom.RightSideViewActivity.java
 * @author: GYZ
 * asia161@qq.com
 * @date: 2019-07-18 15:33
 */
public class RightSideViewActivity extends AppCompatActivity {
    private final String TAG ="RightSideViewActivity";
    private SideBar mSideBar;
    private List<CityInfo> citys;
    private RecyclerView mRecyclerView;
    private CityAdapter mCityAdapter;
    private static Toast makeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_side_view);
        initData();
        mSideBar = findViewById(R.id.sidebar);
        mRecyclerView = findViewById(R.id.recycelview);
        mSideBar.setItemSelectedListener(new SideBar.SideBarItemSelectedListener() {
            @Override
            public void itemSelected(String str) {
                int index = mCityAdapter.getIndex(str);
                showToast(str);
                if (index != -1) {
                    mRecyclerView.scrollToPosition(index);
                }

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCityAdapter = new CityAdapter();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mCityAdapter);
    }

    private void showToast(String str) {
        if (makeText == null) {
            makeText = Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT);
            makeText.setGravity(Gravity.CENTER, 0, 0);
        } else {
            makeText.setText(str);
            makeText.setDuration(Toast.LENGTH_SHORT);
        }
        makeText.show();
    }

    private void initData() {
        citys = new ArrayList<>();
        citys.add(new CityInfo("北京", 1));
        citys.add(new CityInfo("上海", 2));
        citys.add(new CityInfo("天津", 3));
        citys.add(new CityInfo("重庆", 4));
        citys.add(new CityInfo("山东", 5));
        citys.add(new CityInfo("山西", 6));
        citys.add(new CityInfo("河南", 7));
        citys.add(new CityInfo("河北", 8));
        citys.add(new CityInfo("广东", 9));
        citys.add(new CityInfo("广西", 10));
        citys.add(new CityInfo("安徽", 11));
        citys.add(new CityInfo("江苏", 12));
        citys.add(new CityInfo("福建", 13));
        citys.add(new CityInfo("湖南", 14));
        citys.add(new CityInfo("湖北", 15));
        citys.add(new CityInfo("浙江", 16));
        citys.add(new CityInfo("四川", 17));
        citys.add(new CityInfo("云南", 18));
        citys.add(new CityInfo("台湾", 19));
        citys.add(new CityInfo("辽宁", 20));
        citys.add(new CityInfo("吉林", 21));
        citys.add(new CityInfo("黑龙江", 22));
        citys.add(new CityInfo("西藏", 23));
        citys.add(new CityInfo("青海", 24));
        citys.add(new CityInfo("新疆", 25));
        citys.add(new CityInfo("宁夏", 26));
        citys.add(new CityInfo("#东京", 27));
        citys.add(new CityInfo("北京2", 28));
        citys.add(new CityInfo("上海2", 29));
        citys.add(new CityInfo("天津2", 30));
        citys.add(new CityInfo("重庆2", 31));
        citys.add(new CityInfo("山东2", 32));
        citys.add(new CityInfo("山西2", 33));
        citys.add(new CityInfo("河南2", 34));
        citys.add(new CityInfo("河北2", 35));
        citys.add(new CityInfo("广东2", 36));
        citys.add(new CityInfo("广西2", 37));
        citys.add(new CityInfo("安徽2", 38));
        citys.add(new CityInfo("江苏2", 39));
        citys.add(new CityInfo("福建2", 40));
        citys.add(new CityInfo("湖南2", 41));
        citys.add(new CityInfo("湖北2", 42));
        citys.add(new CityInfo("浙江2", 43));
        citys.add(new CityInfo("四川2", 44));
        citys.add(new CityInfo("云南2", 45));
        citys.add(new CityInfo("台湾2", 46));
        citys.add(new CityInfo("辽宁2", 47));
        citys.add(new CityInfo("吉林2", 48));
        citys.add(new CityInfo("黑龙江2", 49));
        citys.add(new CityInfo("西藏2", 50));
        citys.add(new CityInfo("青海2", 51));
        citys.add(new CityInfo("新疆2", 52));
        citys.add(new CityInfo("宁夏2", 53));
        citys.add(new CityInfo("#东京2", 54));
        citys.add(new CityInfo("@伦敦", 28));
        for (CityInfo city : citys) {
            String pinyin = CharacterParser.getInstance().getSelling(city.getName());
            String letter = pinyin.substring(0, 1);
            if (letter.matches("\\w")) {// 匹配拼音第一个位置是否为a-zA-Z
                city.setLetter(letter.toUpperCase());
            } else {
                city.setLetter("#");
            }

        }
        Collections.sort(citys, new PinyinComparable());

    }

    public class PinyinComparable implements Comparator<CityInfo> {


        @Override
        public int compare(CityInfo cityInfo, CityInfo t1) {
            if (cityInfo.getLetter().equals("@") || t1.getLetter().equals("#")) {
                return -1;
            } else if (cityInfo.getLetter().equals("#") || t1.getLetter().equals("@")) {
                return -1;
            }
            return cityInfo.getLetter().compareTo(t1.getLetter());
        }
    }

    private class CityAdapter extends RecyclerView.Adapter<CityHolder> {
        @NonNull
        @Override
        public CityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = android.view.LayoutInflater.from(RightSideViewActivity.this).inflate(R.layout.layout_item, viewGroup, false);
            CityHolder holder = new CityHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull CityHolder cityHolder, int i) {
            cityHolder.cityname.setText(citys.get(i).getName());
        }

        @Override
        public int getItemCount() {
            return citys.size();
        }

        public int getIndex(String letter) {
            for (CityInfo cityInfo : citys) {
                if (cityInfo.getLetter().equals(letter)) {
                    return citys.indexOf(cityInfo);
                }
            }
            return -1;
        }
    }

    private class CityHolder extends RecyclerView.ViewHolder {
        private android.widget.TextView cityname;

        public CityHolder(@NonNull View itemView) {
            super(itemView);
            cityname = itemView.findViewById(R.id.tv_city);
        }
    }
}
