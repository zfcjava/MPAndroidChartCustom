package com.example.chenxunliu;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.chenxunliu.util.DBOperator;

/**
 *
 * @date 2019-11-27
 */
public class MyApp extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();

        try{
            DBOperator.copyDB(getBaseContext());
            Log.e("copyDb", "success");
        }catch(Exception e){
            Log.e("copyDb", "failed");
            e.printStackTrace();
        }

    }

    public static Context getContext() {
        return context;
    }
}
