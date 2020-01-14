package com.example.chenxunliu.util;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.chenxunliu.MyApp;
import com.example.chenxunliu.constant.DBConstant;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class to manipulate tables & data
 * Uses singleton pattern to create single instance
 */
public class DBOperator
{
    private static DBOperator instance = null;
    private SQLiteDatabase db;

    private DBOperator()
    {
        //path of database file
        String path = MyApp.getContext().getDataDir() + "/" + DBConstant.DATABASE_FILE;
        Log.e("DbPath", path);
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }
    /*
     * Singleton Pattern
     * Why should we avoid multiple instances here?
     */
    public static DBOperator getInstance()
    {
        if (instance==null) instance = new DBOperator();
        return instance;
    }
    /**
     * Copy database file
     * From assets folder (in the project) to android folder (on device)
     */
    public static void copyDB(Context context) throws IOException,FileNotFoundException{
        String path = context.getDataDir() + "/" + DBConstant.DATABASE_FILE;
        File file = new File(path);
        if (!file.exists()){
            InputStream is = context.getAssets().open(DBConstant.DATABASE_FILE);
            OutputStream os = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer))>0){
                os.write(buffer, 0, length);
            }
            is.close();
            os.flush();
            os.close();
        }

//        DBOpenHelper dbhelper = new DBOpenHelper(context, path ,1);
//            dbhelper.getWritableDatabase();
    }

    /**
     * execute sql without returning data, such as alter
     * @param sql
     */
    public void execSQL(String sql) throws SQLException
    {
        db.execSQL(sql);
    }
    /**
     * execute sql such as update/delete/insert
     * @param sql
     * @param args
     * @throws SQLException
     */
    public void execSQL(String sql, Object[] args)
    {
        db.execSQL(sql, args);
    }
    /**
     * execute sql query
     * @param sql
     * @param selectionArgs
     * @return cursor
     * @throws SQLException
     */
    public Cursor execQuery(String sql,String[] selectionArgs)	{
        return db.rawQuery(sql, selectionArgs);
    }
    /**
     * execute query without arguments
     * @param sql
     * @return
     * @throws SQLException
     */
    public Cursor execQuery(String sql)	{
        return this.execQuery(sql, null);
    }
    /**
     * close database
     */
    public void closeDB()
    {
        if (db!=null) db.close();
    }
}


