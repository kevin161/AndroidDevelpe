package com.gyz.androiddevelope.activity.custom;

import android.Manifest;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.util.permission.PermissionDenied;
import com.gyz.androiddevelope.util.permission.PermissionGrant;
import com.gyz.androiddevelope.util.permission.PermissionProxy;
import com.gyz.androiddevelope.util.permission.ShowRequestPermissionRationale;
import com.gyz.androiddevelope.base.BaseActivity;
import com.gyz.androiddevelope.util.permission.PermissionUtils;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.activity.custom.PermissionActivity.java
 * @author: ZhaoHao
 * @date: 2017-08-18 10:37
 */

public class PermissionActivity extends BaseActivity {
    private static final int REQUECT_CODE_CALL_PHONE = 3;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_permission);
        findViewById(R.id.btnTestPermission).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PermissionUtils.requestPermissions(PermissionActivity.this, REQUECT_CODE_CALL_PHONE, Manifest.permission.CALL_PHONE);
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @ShowRequestPermissionRationale(REQUECT_CODE_CALL_PHONE)
    public void whyNeedSdCard() {
        Toast.makeText(this, "I need write news to sdcard!", Toast.LENGTH_SHORT).show();
        PermissionUtils.requestPermissions(PermissionActivity.this, REQUECT_CODE_CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(PermissionActivity.this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUECT_CODE_CALL_PHONE)
    public void requestSdcardSuccess() {
        Toast.makeText(this, "GRANT ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE_CALL_PHONE)
    public void requestSdcardFailed() {
        Toast.makeText(this, "DENY ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }


}
