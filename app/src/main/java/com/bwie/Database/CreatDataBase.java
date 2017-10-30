package com.bwie.Database;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 董绍华 on 2017/9/18.
 * 创建数据库
 */

public class CreatDataBase extends SQLiteOpenHelper{


    public CreatDataBase(Context context){
        super(context,"data.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table title" +
                "(id varchar(50)," +
                "createdAt varchar(50)," +
                "desc varchar(50)," +
                "publishedAt varchar(50)," +
                "source varchar(10)," +
                "type varchar(20)," +
                "url varchar(50)," +
                "who varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
