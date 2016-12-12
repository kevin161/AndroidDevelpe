package com.gyz.androiddevelope.util;

import android.util.Log;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author: guoyazhou
 * @date: 2016-03-15 13:51
 */
public class DateUtil {
    private static final String TAG = "DateUtil";
    public static final int SECOND = 1000;
    public static final int MINUTES = SECOND * 60;
    public static final int HOUR = MINUTES * 60;
    public static final int DAY = HOUR * 24;
    public static final int WEEK = DAY * 7;

    public static String convertDate(String date) {
        String result = date.substring(0, 4);
        result += "年";
        result += date.substring(4, 6);
        result += "月";
        result += date.substring(6, 8);
        result += "日";
        return result;
    }


    /**
     * 返回用户相差的时间差
     *
     * @param httpTime    来自网络数据的时间
     * @param currentTime 当前时间
     * @return
     */
    public static String getTimeDifference(int httpTime, long currentTime) {

        long hTime = ((long) httpTime) * 1000;
        long dTime = currentTime - hTime;
        Log.d(TAG,"hTime=" + hTime + " currentTime=" + currentTime + " dTime=" + dTime);

        if (dTime < MINUTES) {
            return dTime / SECOND + "秒前";
        } else if (dTime < HOUR) {
            return dTime / MINUTES + "分钟前";
        } else if (dTime < DAY) {
            return dTime / HOUR + "小时前";
        } else if (dTime < WEEK) {
            return dTime / DAY + "天前";
        } else {
            return DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new Date(hTime));
        }

    }

}
