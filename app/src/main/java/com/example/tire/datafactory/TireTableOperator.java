package com.example.tire.datafactory;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.tire.database.TireTable;

public class TireTableOperator {
    private Context mContext;
    public static Uri URI = Uri.parse("content://com.example.tire.provider/" + TireTable.TABLE_NAME);
    private ContentResolver resolver = mContext.getContentResolver();
    boolean isInsert = false;
    private static volatile TireTableOperator instance;

    private TireTableOperator(Context context) {
        mContext = context;
    }

    public static TireTableOperator getInstance(Context context){
        if(instance == null){
            synchronized (TireTableOperator.class){
                if (instance == null){
                    instance = new TireTableOperator(context);
                }
            }
        }
        return instance;
    }

    public float getValue(String where) {
        float value = -1;
        Cursor cursor = resolver.query(URI, new String[]{TireTable.ID},
                TireTable.ID + "=? ", new String[]{"112"}, null);
        while (cursor.moveToNext()) {
            value = cursor.getFloat(cursor.getColumnIndex(where));
        }
        return value;
    }

    public void insert() {
        if(!isInsert){
            float f = getValue(TireTable.PRESSURE_FL);
            if(f>0){
                isInsert = true;
            }
        }

        if (isInsert) {
            ContentValues values = new ContentValues();
            values.put(TireTable.PRESSURE_FL, 1.3);
            values.put(TireTable.PRESSURE_FR, 1.3);
            values.put(TireTable.PRESSURE_BL, 1.3);
            values.put(TireTable.PRESSURE_BR, 1.3);
            values.put(TireTable.TEMPERATURE_FL, 1.3);
            values.put(TireTable.TEMPERATURE_FR, 1.3);
            values.put(TireTable.TEMPERATURE_BL, 1.3);
            values.put(TireTable.TEMPERATURE_BR, 1.3);
            resolver.update(URI, values, TireTable.ID, new String[]{"112"});
        } else {
            ContentValues values = new ContentValues();
            values.put(TireTable.ID, 112);
            values.put(TireTable.PRESSURE_FL, 1.2);
            values.put(TireTable.PRESSURE_FR, 1.2);
            values.put(TireTable.PRESSURE_BL, 1.2);
            values.put(TireTable.PRESSURE_BR, 1.2);
            values.put(TireTable.TEMPERATURE_FL, 1.2);
            values.put(TireTable.TEMPERATURE_FR, 1.2);
            values.put(TireTable.TEMPERATURE_BL, 1.2);
            values.put(TireTable.TEMPERATURE_BR, 1.2);
            resolver.insert(URI, values);
        }

    }
}
