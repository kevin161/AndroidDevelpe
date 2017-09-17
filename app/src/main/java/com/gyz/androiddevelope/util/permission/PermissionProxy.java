package com.gyz.androiddevelope.util.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.util.permission.PermissionUtils.java
 * @author: ZhaoHao
 * @date: 2017-08-18 09:14
 */
public interface PermissionProxy<T> {

    void grant(T source, int requestCode);

    void denied(T source, int requestCode);

    void rationale(T source, int requestCode);

    boolean needShowRationale(int requestCode);
}
