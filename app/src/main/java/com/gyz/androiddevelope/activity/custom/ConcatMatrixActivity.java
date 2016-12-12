package com.gyz.androiddevelope.activity.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;

/**
 * @author: guoyazhou
 * @date: 2016-05-09 16:28
 */
public class ConcatMatrixActivity extends BaseToolbarNormalActivity {
    private static final String TAG = "ConcatMatrixActivity";

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(new ConcatMatrixView(this));
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected String currActivityName() {
        return "Matrix ";
    }

    public class ConcatMatrixView extends View {

        private int scanAngle = 30;//扫描旋转的角度
        private Matrix matrix = new Matrix();
        private Matrix smallMatrix = new Matrix();
        private Paint bgPaint = new Paint();
        private Paint arcPaint = new Paint();
        private Paint arcSmallPaint = new Paint();
        private Paint keduPaint;
        private int centerX = 450;
        private int centerY = 450;
        int bigCircleR = 200;
        int smallCircleR = 170;
        float rotateDegree = 3.6f;

        public ConcatMatrixView(Context context) {
            super(context);
            initPaint();
            post(run);
        }

        private void initPaint() {
            keduPaint = new Paint();
            keduPaint.setColor(Color.BLACK);
            keduPaint.setAntiAlias(true);
            keduPaint.setStyle(Paint.Style.FILL);

            bgPaint.setColor(Color.RED);
            bgPaint.setAntiAlias(true);
            bgPaint.setStyle(Paint.Style.STROKE);

            arcPaint.setColor(Color.RED);
            arcPaint.setStrokeWidth(4);
            arcPaint.setStyle(Paint.Style.STROKE);

            arcSmallPaint.setColor(Color.RED);
            arcSmallPaint.setStrokeWidth(14);
            arcSmallPaint.setStrokeCap(Paint.Cap.ROUND);
            arcSmallPaint.setStyle(Paint.Style.STROKE);
        }

        private Runnable run = new Runnable() {
            @Override
            public void run() {
//                scanAngle+=10;
                matrix.postRotate(scanAngle, 450, 450);
                smallMatrix.postRotate(-30, 450, 450);
                invalidate();
                postDelayed(run, 130);
            }
        };

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.save();
            canvas.concat(matrix);
//           细线条两根
            canvas.drawArc(100, 100, 800, 800, 0, 190, false, arcPaint);
            canvas.drawArc(180, 180, 720, 720, 160, 120, false, arcPaint);
            canvas.restore();

            canvas.save();
            canvas.concat(smallMatrix);
            //粗线条两根
            canvas.drawArc(125, 125, 775, 775, 220, 120, false, arcSmallPaint);
            canvas.drawArc(205, 205, 695, 695, 0, 120, false, arcSmallPaint);

            canvas.restore();
            canvas.drawCircle(centerX, centerX, smallCircleR, bgPaint);
            canvas.drawCircle(centerX, centerX, bigCircleR, bgPaint);
            canvas.save();

            for (int i = 0; i < 100; i++) {

                canvas.rotate(rotateDegree, centerX, centerY);
                canvas.drawLine(centerX, centerY - bigCircleR, centerX, centerY - smallCircleR, bgPaint);

            }
            canvas.restore();

        }
    }
}



