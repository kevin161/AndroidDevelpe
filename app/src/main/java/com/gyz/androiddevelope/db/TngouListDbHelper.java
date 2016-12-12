package com.gyz.androiddevelope.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gyz.androiddevelope.util.LogUtils;

/**
 * @author: guoyazhou
 * @date: 2016-04-27 16:37
 */
public class TngouListDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "TngouDbHelper";

    public TngouListDbHelper(Context context, int version) {
        super(context, "tngouList.db", null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        LogUtils.i(TAG,"create table tngouListDbHelper_____________________________");
        db.execSQL("create table if not exists tngouList " +
                "(id INTEGER primary key autoincrement," +

                "typeid INTEGER ,"+
                "json text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        LogUtils.i("onUpgrade table TngouListDbHelper++++++++++++++++++");
    }
}
