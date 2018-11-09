package com.example.tire.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

public class TireTableOperator {
    private static Context mContext;

    public static void setContext(Context context){
        mContext = context;
    }

    public static void update(){

    }

    public static void insert(){
        ContentValues values = new ContentValues();
        values.put(TireTable.PRESSURE_FL,1.2);
        values.put(TireTable.PRESSURE_FR,1.2);
        values.put(TireTable.PRESSURE_BL,1.2);
        values.put(TireTable.PRESSURE_BR,1.2);
        values.put(TireTable.TEMPERATURE_FL,1.2);
        values.put(TireTable.TEMPERATURE_FR,1.2);
        values.put(TireTable.TEMPERATURE_BL,1.2);
        values.put(TireTable.TEMPERATURE_BR,1.2);

        ContentResolver contentResolver = mContext.getContentResolver();
        contentResolver.insert(Uri.parse("com.example.tire.provider/" + TireTable.TABLE_NAME),values);
    }
}
