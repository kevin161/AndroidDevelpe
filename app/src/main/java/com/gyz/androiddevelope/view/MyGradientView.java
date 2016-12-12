package com.gyz.androiddevelope.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.gyz.androiddevelope.R;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.MyGradientView.java
 * @author: ZhaoHao
 * @date: 2016-08-30 15:53
 */
public class MyGradientView extends View {
    private static final String TAG = "MyGradientView";
    private Bitmap bitmap;
    private Paint paint;
    private BitmapShader bitmapShader;
    private int width;
    private int height;
    LinearGradient linearGradient;
    private RadialGradient radialGradient;
    private SweepGradient sweepGradient;
    private ComposeShader composeShader;

    int[] colors = {0xFF9A9BF8, 0xFF9AA2F7, 0xFF65CCD1, 0xFF63D0CD, 0xFF68CBD0, 0xFF999AF6, 0xFF9A9BF8};
    float[] positions = {0, 1f / 6, 2f / 6, 3f / 6, 4f / 6, 5f / 6, 1};

    public MyGradientView(Context context) {
        this(context, null);
    }

    public MyGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_logo)).getBitmap();

        paint = new Paint();
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
//        canvas.drawBitmap(bitmap, 0, 0, paint);

        /**
         * TileMode.CLAMP 拉伸最后一个像素去铺满剩下的地方
         * TileMode.MIRROR 通过镜像翻转铺满剩下的地方。
         * TileMode.REPEAT 重复图片平铺整个画面（电脑设置壁纸）
         */
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);

        //设置像素矩阵，来调整大小，为了解决宽高不一致的问题。
    		float scale = Math.max(width, height)*1.0f/Math.min(width, height);
        Matrix matrix  = new Matrix();
        matrix.setScale(scale,scale);//缩放比例
        bitmapShader.setLocalMatrix(matrix);

        /**线性渐变
         * x0, y0, 起始点
         *  x1, y1, 结束点
         * int[]  colors, 中间依次要出现的几个颜色
         * float[] positions,数组大小跟colors数组一样大，中间依次摆放的几个颜色分别放置在那个位置上(参考比例从左往右)
         *    tile
         */

          linearGradient = new LinearGradient(0, 0, 400, 400, colors, positions, Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);
		canvas.drawRect(0, 0, 400, 400, paint);
//
//		radialGradient = new RadialGradient(300, 300, 100, colors, positions, Shader.TileMode.REPEAT);
//		paint.setShader(radialGradient);
//		canvas.drawCircle(300, 300, 300, paint);

//		sweepGradient = new SweepGradient(300, 300, colors, null);
//		paint.setShader(sweepGradient);
//		canvas.drawCircle(300, 300, 300, paint);


        composeShader = new ComposeShader(linearGradient, bitmapShader, PorterDuff.Mode.SRC_OVER);
        paint.setShader(composeShader);
        canvas.drawRect(0, 0, 800, 1000, paint);

    }
}
