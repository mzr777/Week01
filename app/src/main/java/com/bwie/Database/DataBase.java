package com.bwie.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 董绍华 on 2017/9/19.
 * 对数据库操作的类
 */

public class DataBase {
      //CreatDataBase creatDataBase;
      SQLiteDatabase database;

    public DataBase(Context context)
    {
        //creatDataBase=new CreatDataBase(context);
        database=new CreatDataBase(context).getReadableDatabase();
    }

    //添加的方法
    public void add(String id,String createdAt,String desc,String publishedAt,String source,String type,String url,String who)
    {

        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("createdAt",createdAt);
        values.put("desc",desc);
        values.put("publishedAt",publishedAt);
        values.put("source",source);
        values.put("type",type);
        values.put("url",url);
        values.put("who",who);

        database.insert("title",null,values);

    }
      //查询的方法
      public void select()
      {
          Cursor cursor=database.query("title",null,null,null,null,null,null);

          while (cursor.moveToNext())
          {
              String id=cursor.getString(cursor.getColumnIndex("id"));
              String createdAt=cursor.getString(cursor.getColumnIndex("createdAt"));
              String desc=cursor.getString(cursor.getColumnIndex("desc"));
              String images=cursor.getString(cursor.getColumnIndex("images"));
              String publishedAt=cursor.getString(cursor.getColumnIndex("publishedAt"));
              String source=cursor.getString(cursor.getColumnIndex("source"));
              String type=cursor.getString(cursor.getColumnIndex("type"));
              String url=cursor.getString(cursor.getColumnIndex("url"));
              String used=cursor.getString(cursor.getColumnIndex("used"));
              String who=cursor.getString(cursor.getColumnIndex("who"));

          }
      }

}
