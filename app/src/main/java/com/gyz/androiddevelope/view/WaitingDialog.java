package com.gyz.androiddevelope.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.util.DensityUtils;


public class WaitingDialog extends MyDialog {
    private Context context;
    //	private ImageView anim_img;
    private TextView content_tv;

    public WaitingDialog(Context context) {
        super(context, R.style.DT_DIALOG_Translucent);
        this.context = context;
        super.createView();
        super.setCancelable(true);
        super.setCanceledOnTouchOutside(true);
        setWindowBgAlpha(0);
        int width = DensityUtils.dp2px(context, 90);
        setWindowSize(width, width);
    }

    @Override
    protected View getView() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_page_loading, null);
//		anim_img = (ImageView) view.findViewById(R.id.anim_img);
        content_tv = (TextView) view.findViewById(R.id.content_tv);
        return view;
    }

    public void startAnimation() {
//		AnimationDrawable anim = (AnimationDrawable) anim_img.getBackground();
//		if (anim != null && !anim.isRunning()) {
//			anim.start();
//		}
    }

    public void stopAnimation() {
//		AnimationDrawable anim = (AnimationDrawable) anim_img.getBackground();
//		if (anim != null && anim.isRunning()) {
//			anim.stop();
//		}
    }

    public ImageView getAnim_img() {
//		return anim_img;
        return null;
    }

    public TextView getContent_tv() {
        return content_tv;
    }
}
