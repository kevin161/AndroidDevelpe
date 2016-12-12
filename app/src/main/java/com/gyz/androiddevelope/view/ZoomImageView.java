package com.gyz.androiddevelope.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.gyz.androiddevelope.R;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.ZoomImageView.java
 * @author: ZhaoHao
 * @date: 2016-08-30 15:28
 */
public class ZoomImageView extends View {
    private static final String TAG = "ZoomImageView";

    private Bitmap bitmap;
    private ShapeDrawable shapeDrawable;
    //放大倍数
    private static final int FACTOR = 3;
    //放大镜的半径
    private static final int RADIUS = 300;

    private Matrix matrix = new Matrix();

    public ZoomImageView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_load);
        Bitmap bit = bitmap;
        //放大后的图片
        bit = Bitmap.createScaledBitmap(bit,bit.getWidth()*FACTOR,bit.getHeight()*FACTOR,true);
        //制作一个圆形图片（放大镜） 盖在canvas上
        BitmapShader bitmapShader = new BitmapShader(bit, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(bitmapShader);
        //切出矩形区域，用于绘制内切圆
        shapeDrawable.setBounds(0,0,RADIUS*2,RADIUS*2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,null);
        shapeDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        matrix.setTranslate(RADIUS - x*FACTOR,RADIUS - y*FACTOR);

        shapeDrawable.getPaint().getShader().setLocalMatrix(matrix);
        shapeDrawable.setBounds(x-RADIUS,y-RADIUS,x+RADIUS,y+RADIUS);
        invalidate();

        return super.onTouchEvent(event);

    }
}
