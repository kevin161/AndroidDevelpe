package com.gyz.androiddevelope.activity.custom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gyz.androiddevelope.R;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.TransformActivity.java
 * @author: ZhaoHao
 * @date: 2016-07-20 10:18
 */
public class TransformActivity extends AppCompatActivity {
    private static final String TAG = "TransformActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);

        final SimpleDraweeView imageView = (SimpleDraweeView) findViewById(R.id.imgView);
        imageView.setImageURI("http://image17-c.poco.cn/mypoco/myphoto/20150517/21/5654698720150517213553082.jpg?600x900_120");
        final Activity activity = this;

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,imageView,"imgView");
//                Intent intent = new Intent(getApplicationContext(),TransformSecondActivity.class);
//                    startActivity(intent,optionsCompat.toBundle());

                    Slide slide = new Slide();
                    Explode explode = new Explode();
                    Fade fade = new Fade();


                    explode.setDuration(400);
                    getWindow().setExitTransition(explode);//出去的动画
                    getWindow().setEnterTransition(explode);//进来的动画
                    //如果有共享元素，可以设置共享元素，那么它就会按照共享元素动画执行，其他的子view就会按照Fade动画执行。
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity);
                    Intent intent = new Intent(getApplicationContext(), TransformSecondActivity.class);
                    startActivity(intent, optionsCompat.toBundle());
                }
            }
        });
    }
}
