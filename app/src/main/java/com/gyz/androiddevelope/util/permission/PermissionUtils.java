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

public class PermissionUtils {
    private static final String SUFFIX = "$$PermissionProxy";

    public static void requestPermissions(Activity object, int requestCode, String... permissions) {
        _requestPermissions(object, requestCode, permissions);
    }

    public static void requestPermissions(Fragment object, int requestCode, String... permissions) {
        _requestPermissions(object, requestCode, permissions);
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String permission, int requestCode) {
        PermissionProxy proxy = findPermissionProxy(activity);
        if (!proxy.needShowRationale(requestCode)) return false;
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                permission)) {
            proxy.rationale(activity, requestCode);
            return true;
        }
        return false;
    }


    @TargetApi(value = Build.VERSION_CODES.M)
    private static void _requestPermissions(Object object, int requestCode, String... permissions) {
        if (!isOverMarshmallow()) {
            doExecuteSuccess(object, requestCode);
            return;
        }
        List<String> deniedPermissions = findDeniedPermissions(getActivity(object), permissions);

        if (deniedPermissions.size() > 0) {
            if (object instanceof Activity) {
                ((Activity) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else if (object instanceof Fragment) {
                ((Fragment) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else {
                throw new IllegalArgumentException(object.getClass().getName() + " 不支持的类!");
            }
        } else {
            doExecuteSuccess(object, requestCode);
        }
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public static List<String> findDeniedPermissions(Activity activity, String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }

    public static void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions,
                                                  int[] grantResults) {
        requestResult(activity, requestCode, permissions, grantResults);
    }

    public static void onRequestPermissionsResult(Fragment fragment, int requestCode, String[] permissions,
                                                  int[] grantResults) {
        requestResult(fragment, requestCode, permissions, grantResults);
    }

    private static void requestResult(Object obj, int requestCode, String[] permissions,
                                      int[] grantResults) {
        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }
        if (deniedPermissions.size() > 0) {
            doExecuteFail(obj, requestCode);
        } else {
            doExecuteSuccess(obj, requestCode);
        }
    }

    private static void doExecuteSuccess(Object activity, int requestCode) {
        findPermissionProxy(activity).grant(activity, requestCode);

    }

    private static void doExecuteFail(Object activity, int requestCode) {
        findPermissionProxy(activity).denied(activity, requestCode);
    }


    private static PermissionProxy findPermissionProxy(Object activity) {
        try {
            Class clazz = activity.getClass();
            Class injectorClazz = Class.forName(clazz.getName() + SUFFIX);
            return (PermissionProxy) injectorClazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException(String.format("can not find %s , something when compiler.", activity.getClass().getSimpleName() + SUFFIX));
    }

    private static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static Activity getActivity(Object object) {
        if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof Activity) {
            return (Activity) object;
        }
        return null;
    }

    public static List<Method> findAnnotationMethods(Class clazz, Class<? extends Annotation> clazz1) {
        List<Method> methods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(clazz1)) {
                methods.add(method);
            }
        }
        return methods;
    }
}
