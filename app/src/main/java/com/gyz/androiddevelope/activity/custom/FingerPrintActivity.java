package com.gyz.androiddevelope.activity.custom;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gyz.androiddevelope.R;
import com.gyz.androiddevelope.base.BaseToolbarNormalActivity;
import com.gyz.androiddevelope.listener.MyAuthCallback;
import com.gyz.androiddevelope.util.CryptoObjectHelper;
import com.gyz.androiddevelope.util.ToastUtil;

public class FingerPrintActivity extends BaseToolbarNormalActivity {
    public static final int MSG_AUTH_SUCCESS = 1;
    public static final int MSG_AUTH_FAILED = 2;
    public static final int MSG_AUTH_ERROR = 3;
    public static final int MSG_AUTH_HELP = 4;

    private Button mStartBtn,mCancelBtn;
    FingerprintManagerCompat fingerprintManager;
    private CancellationSignal cancellationSignal = null;
    MyAuthCallback myAuthCallback;
    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
                setContentView(R.layout.activity_finger_print);
        fingerprintManager = FingerprintManagerCompat.from(this);
        mStartBtn = (Button) findViewById(R.id.mStartBtn);
        mCancelBtn = (Button) findViewById(R.id.mCancelBtn);
        mCancelBtn.setEnabled(false);
        mStartBtn.setEnabled(true);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start fingerprint auth here.
                // start fingerprint auth here.
                try {
                    CryptoObjectHelper cryptoObjectHelper = new CryptoObjectHelper();
                    if (cancellationSignal == null) {
                        cancellationSignal = new CancellationSignal();
                    }
                    fingerprintManager.authenticate(cryptoObjectHelper.buildCryptoObject(), 0,
                            cancellationSignal, myAuthCallback, null);
                    // set button state.
                    mStartBtn.setEnabled(false);
                    mCancelBtn.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(FingerPrintActivity.this, "Fingerprint init failed! Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set button state
                mCancelBtn.setEnabled(false);
                mStartBtn.setEnabled(true);

                // cancel fingerprint auth here.
                cancellationSignal.cancel();
                cancellationSignal = null;
            }
        });
        // init fingerprint.
        fingerprintManager = FingerprintManagerCompat.from(this);

        if (!getSystemEnabled(this)) {
            // no fingerprint sensor is detected, show dialog to tell user.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("没有传感器");
            builder.setMessage("不支持指纹");
            builder.setIcon(android.R.drawable.stat_sys_warning);
            builder.setCancelable(false);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            // show this dialog.
            builder.create().show();
        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
            // no fingerprint image has been enrolled.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("未录入指纹");
            builder.setMessage("未录入指纹");
            builder.setIcon(android.R.drawable.stat_sys_warning);
            builder.setCancelable(false);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            // show this dialog
            builder.create().show();
        } else {
            try {
                myAuthCallback = new MyAuthCallback(handler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean getSystemEnabled(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        }

        FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(context);
        boolean ii  = fingerprintManager.isHardwareDetected();
        boolean ll = fingerprintManager.hasEnrolledFingerprints();

        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        return fingerprintManager.isHardwareDetected()
                && fingerprintManager.hasEnrolledFingerprints()
                && keyguardManager.isKeyguardSecure();
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected String currActivityName() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!mStartBtn.isEnabled() && cancellationSignal != null) {
            cancellationSignal.cancel();
        }
    }

     Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.d("tag", "msg: " + msg.what + " ,arg1: " + msg.arg1);
            switch (msg.what) {
                case MSG_AUTH_SUCCESS:
                    setResultInfo("指纹解锁成功");
                    mCancelBtn.setEnabled(false);
                    mStartBtn.setEnabled(true);
                    cancellationSignal = null;
                    break;
                case MSG_AUTH_FAILED:
                    setResultInfo("指纹解锁失败");
                    mCancelBtn.setEnabled(false);
                    mStartBtn.setEnabled(true);
                    cancellationSignal = null;
                    break;
                case MSG_AUTH_ERROR:
                    handleErrorCode(msg.arg1);
                    break;
                case MSG_AUTH_HELP:
                    handleHelpCode(msg.arg1);
                    break;
            }
        }
    };
    private void handleErrorCode(int code) {
        switch (code) {
            case FingerprintManager.FINGERPRINT_ERROR_CANCELED:
                setResultInfo("FINGERPRINT_ERROR_CANCELED");
                break;
            case FingerprintManager.FINGERPRINT_ERROR_HW_UNAVAILABLE:
                setResultInfo("FINGERPRINT_ERROR_HW_UNAVAILABLE");
                break;
            case FingerprintManager.FINGERPRINT_ERROR_LOCKOUT:
                setResultInfo("FINGERPRINT_ERROR_LOCKOUT");
                break;
            case FingerprintManager.FINGERPRINT_ERROR_NO_SPACE:
                setResultInfo("FINGERPRINT_ERROR_NO_SPACE");
                break;
            case FingerprintManager.FINGERPRINT_ERROR_TIMEOUT:
                setResultInfo("FINGERPRINT_ERROR_TIMEOUT");
                break;
            case FingerprintManager.FINGERPRINT_ERROR_UNABLE_TO_PROCESS:
                setResultInfo("FINGERPRINT_ERROR_UNABLE_TO_PROCESS");
                break;
        }
    }

    private void handleHelpCode(int code) {
        switch (code) {
            case FingerprintManager.FINGERPRINT_ACQUIRED_GOOD:
                setResultInfo("FINGERPRINT_ACQUIRED_GOOD");
                break;
            case FingerprintManager.FINGERPRINT_ACQUIRED_IMAGER_DIRTY:
                setResultInfo("FINGERPRINT_ACQUIRED_IMAGER_DIRTY");
                break;
            case FingerprintManager.FINGERPRINT_ACQUIRED_INSUFFICIENT:
                setResultInfo("FINGERPRINT_ACQUIRED_INSUFFICIENT");
                break;
            case FingerprintManager.FINGERPRINT_ACQUIRED_PARTIAL:
                setResultInfo("FINGERPRINT_ACQUIRED_PARTIAL");
                break;
            case FingerprintManager.FINGERPRINT_ACQUIRED_TOO_FAST:
                setResultInfo("FINGERPRINT_ACQUIRED_TOO_FAST");
                break;
            case FingerprintManager.FINGERPRINT_ACQUIRED_TOO_SLOW:
                setResultInfo("FINGERPRINT_ACQUIRED_TOO_SLOW");
                break;
        }
    }
    void setResultInfo(String str){
        ToastUtil.showShort(FingerPrintActivity.this,str);
    }

}
