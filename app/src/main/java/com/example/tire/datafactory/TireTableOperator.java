package com.example.tire.datafactory;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.tire.database.TireTable;

public class TireTableOperator {
    private static Context mContext;
    public static Uri URI = Uri.parse("content://com.example.tire.provider/" + TireTable.TABLE_NAME);

    public static void setContext(Context context){
        mContext = context;
    }

    public static void update(){

    }

    static boolean isInsert = false;
    public static void insert(){
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor =  resolver.query(URI,new String[]{TireTable._ID},
               TireTable._ID + "=? ",new String[]{"112"},null);

        if(!isInsert){
            while (cursor.moveToNext()){
                float f = cursor.getFloat(cursor.getColumnIndex(TireTable.PRESSURE_FL));
                if (f >= 0){
                    isInsert = true;
                    break;
                }
            }
        }


        if(isInsert){
            ContentValues values = new ContentValues();
            values.put(TireTable.PRESSURE_FL,1.3);
            values.put(TireTable.PRESSURE_FR,1.3);
            values.put(TireTable.PRESSURE_BL,1.3);
            values.put(TireTable.PRESSURE_BR,1.3);
            values.put(TireTable.TEMPERATURE_FL,1.3);
            values.put(TireTable.TEMPERATURE_FR,1.3);
            values.put(TireTable.TEMPERATURE_BL,1.3);
            values.put(TireTable.TEMPERATURE_BR,1.3);
            resolver.update(URI,values,TireTable._ID,new String[]{"112"});
        }else{
            ContentValues values = new ContentValues();
            values.put(TireTable._ID,112);
            values.put(TireTable.PRESSURE_FL,1.2);
            values.put(TireTable.PRESSURE_FR,1.2);
            values.put(TireTable.PRESSURE_BL,1.2);
            values.put(TireTable.PRESSURE_BR,1.2);
            values.put(TireTable.TEMPERATURE_FL,1.2);
            values.put(TireTable.TEMPERATURE_FR,1.2);
            values.put(TireTable.TEMPERATURE_BL,1.2);
            values.put(TireTable.TEMPERATURE_BR,1.2);
            resolver.insert(URI,values);
        }

    }
}
