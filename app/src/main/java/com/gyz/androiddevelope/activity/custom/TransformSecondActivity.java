package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gyz.androiddevelope.R;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.TransformActivity.java
 * @author: ZhaoHao
 * @date: 2016-07-20 10:18
 */
public class TransformSecondActivity extends AppCompatActivity {
    private static final String TAG = "TransformActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform_second);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.imgView);
        simpleDraweeView.setImageURI("http://image17-c.poco.cn/mypoco/myphoto/20150517/21/5654698720150517213553082.jpg?600x900_120");
    }
}
