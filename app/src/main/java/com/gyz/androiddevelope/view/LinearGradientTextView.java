package com.gyz.androiddevelope.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.view.LinearGradientTextView.java
 * @author: ZhaoHao
 * @date: 2016-08-24 11:06
 */
public class LinearGradientTextView extends TextView {

    private TextPaint paint;
    private LinearGradient linearGradient;
    private Matrix matrix;
    private float translateX;
    private float deltaX = 20;


    public LinearGradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint = getPaint();
        //GradientSize=3个文字的大小
        String text = getText().toString();
        float textWidth = paint.measureText(text);
        int gradientSize = (int) ((3*textWidth / text.length()));
        //设置光影效果区域及效果
        linearGradient = new LinearGradient(-gradientSize,0,0,0,new int[]{0x22ffffff,0xffffffff,0x22ffffff},
                new float[]{0,0.5f,1}, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        matrix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float textWidth = getPaint().measureText(getText().toString());
        translateX += deltaX;
        if (translateX >textWidth+1 || translateX <1){
            deltaX = -deltaX;
        }
        matrix.setTranslate(translateX,0);
        linearGradient.setLocalMatrix(matrix);

        postInvalidateDelayed(50);
    }
}
