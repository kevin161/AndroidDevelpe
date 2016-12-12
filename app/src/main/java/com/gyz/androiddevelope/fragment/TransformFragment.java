package com.gyz.androiddevelope.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.activity.HomeActivity;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.fragment.TransformFragment.java
 * @author: ZhaoHao
 * @date: 2016-05-23 16:20
 */
public class TransformFragment extends Fragment {
    private static final String TAG = "TransformFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int layoutId = bundle.getInt("layoutId");
        int pageIndex = bundle.getInt("pageIndex");
        View view = inflater.inflate(layoutId, null);
        view.setTag(pageIndex);
        if (pageIndex == 2){
            view.findViewById(R.id.btnIn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().finish();
                }
            });
        }

        return view;
    }
}
