package com.gyz.androiddevelope.util.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.util.permission.PermissionDenied.java
 * @author: ZhaoHao
 * @date: 2017-08-18 09:05
 */
@Target(ElementType.METHOD)
public @interface PermissionDenied{
    int value();
}
