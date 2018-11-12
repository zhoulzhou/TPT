package com.example.tire.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SPrefUtils {
    private static final String SP_FILE_NAME = "tire_share_preference";
    public static final String IS_INSERT_DB_FIRST = "is_insert_db_first";

    public static void setBoolean(Context context,String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue){
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }
}
