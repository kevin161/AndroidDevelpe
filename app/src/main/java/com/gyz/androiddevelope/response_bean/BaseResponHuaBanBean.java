package com.gyz.androiddevelope.response_bean;

import java.io.Serializable;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.response_bean.BaseResponHuaBanBean.java
 * @author: ZhaoHao
 * @date: 2016-06-20 09:32
 */
public class BaseResponHuaBanBean implements Serializable {

    protected int err;
    protected String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }
}
