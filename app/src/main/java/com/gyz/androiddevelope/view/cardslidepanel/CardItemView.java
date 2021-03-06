package com.gyz.androiddevelope.view.cardslidepanel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.util.ImageUtils;

/**
 * 卡片View项
 * @author xmuSistone
 */
public class CardItemView extends FrameLayout {

    private SimpleDraweeView imageView;
    private View shadeView;
    private Context context;

    public CardItemView(Context context) {
        this(context, null);
    }

    public CardItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        inflate(context, R.layout.card_item, this);
        imageView = (SimpleDraweeView) findViewById(R.id.card_image_view);
        shadeView = findViewById(R.id.shade);
    }

    public void fillData(CardDataItem itemData)  {
//
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setUri(Uri.parse(itemData.imagePath))
//                .setTapToRetryEnabled(true)
//                .setOldController(imageView.getController())
//                .build();
//
//        imageView.setController(controller);

        ImageUtils.setControllerByFresco(context,imageView,itemData.imagePath,true);

    }

    public void setShadeLayer(int shaderLayer) {
        shadeView.setBackgroundResource(shaderLayer);
    }

}
