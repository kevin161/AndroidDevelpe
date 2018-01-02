package com.gyz.androiddevelope.activity.custom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.util.FileUtil;
import com.gyz.androiddevelope.util.LogUtils;
import com.gyz.androiddevelope.util.RSA;
import com.gyz.androiddevelope.util.SPUtils;
import com.gyz.androiddevelope.util.ToastUtil;
import com.gyz.androiddevelope.view.RulerView;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @version V1.0
 * @author: ZhaoHao
 */

public class ScaleViewActivity extends BaseActivity implements RulerView.OnRulerChangeListener {

    @Bind(R.id.tvValue)
    TextView tvValue;
    @Bind(R.id.rulerView)
    RulerView rulerView;
    @Bind(R.id.btnBuildKey)
    Button btnBuildKey;
    @Bind(R.id.jiami)
    Button jiami;
    @Bind(R.id.jiemi)
    Button jiemi;
    @Bind(R.id.edtData)
    EditText edtData;
    @Bind(R.id.edtJieData)
    EditText edtJieData;
    @Bind(R.id.txtPrivateKey)
    TextView txtPrivateKey;
    @Bind(R.id.txtPublicKey)
    TextView txtPublicKey;
    @Bind(R.id.txtAfterJiemi)
    TextView txtAfterJiemi;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_scale_layout);
        ButterKnife.bind(this);

        rulerView.setOnRulerChangeListener(this);
        rulerView.setCurrLocation(10000);
        rulerView.setMaxValue(160000);
        tvValue.setText(10000 + "");


    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onChanged(int newValue) {
        tvValue.setText(newValue + "");
    }

    Map<String, String> keyPair;

    @OnClick({R.id.btnBuildKey, R.id.jiami, R.id.jiemi, R.id.btnCopyPublicKey})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBuildKey:
                try {
                    keyPair = RSA.generateKeyPair();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                txtPrivateKey.setText(keyPair.get("privateKey"));
                txtPublicKey.setText(keyPair.get("publicKey"));
                SPUtils.put(getBaseContext(), "privateKey", keyPair.get("privateKey"));
                SPUtils.put(getBaseContext(), "publicKey", keyPair.get("publicKey"));
                break;
            case R.id.btnCopyPublicKey:
                String str = txtPublicKey.getText().toString();
                FileUtil.copyToClipboard(getApplicationContext(), str);
                ToastUtil.showShort(ScaleViewActivity.this, "复制成功！");
                break;
            case R.id.jiami:
                //加密
                String str3 = edtData.getText().toString();
                String kk = (String) SPUtils.get(getBaseContext(), "publicKey", "null");
                String kk2 = (String) SPUtils.get(getBaseContext(), "privateKey", "null");
                try {
                    String aa = RSA.encrypt(str3, kk);
                    LogUtils.e("tag", "aaaa自己加密后：" + aa);
                    String bb = RSA.decrypt(aa, kk2);
                    LogUtils.e("tag", "aaaa自己解密后：" + bb);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.jiemi:
                String privateK = (String) SPUtils.get(getBaseContext(), "privateKey", "null");
                String str2 = edtJieData.getText().toString();
                try {
                    String uu = RSA.decrypt(str2, privateK);
                    txtAfterJiemi.setText(uu);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
        }
    }
}
