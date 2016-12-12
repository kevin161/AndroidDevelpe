package com.gyz.androiddevelope.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gyz.androiddevelope.util.LogUtils;

/**
 * @author: guoyazhou
 * @date: 2016-03-15 15:31
 */
public class WebCacheDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "WebCacheDbHelper";

    public WebCacheDbHelper(Context context, int version) {
        super(context, "webCache.db", null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        LogUtils.i("create table webCache_____________");
        db.execSQL("create table if not exists webCache " +
                "(id INTEGER primary key autoincrement," +
                "newsId INTEGER unique," +
                "json text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        LogUtils.i("onUpgrade table webCache++++++++++++++++++");
    }
}
