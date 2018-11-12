package com.example.tire.datafactory;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.tire.common.LogUtils;
import com.example.tire.database.DBHelper;
import com.example.tire.database.TireTable;

import java.util.HashMap;
import java.util.Map;

public class TireTableOperator {
    private Context mContext;
    public static Uri URI = Uri.parse("content://com.example.tire.provider/" + TireTable.TABLE_NAME);
    private ContentResolver resolver;
    private static volatile TireTableOperator instance;

    private TireTableOperator(Context context) {
        mContext = context;
        resolver = mContext.getContentResolver();
    }

    public static TireTableOperator getInstance(Context context) {
        if (instance == null) {
            synchronized (TireTableOperator.class) {
                if (instance == null) {
                    instance = new TireTableOperator(context);
                }
            }
        }
        return instance;
    }

    Map<String,Float> map = new HashMap<>(16);
    public HashMap getValue() {
        map.clear();
        Cursor cursor = resolver.query(URI, new String[]{TireTable.ID},
                TireTable.ID + "=? ", new String[]{"112"}, null);
        while (cursor.moveToNext()) {
            map.put(TireTable.PRESSURE_FL,cursor.getFloat(cursor.getColumnIndex(TireTable.PRESSURE_FL)));
            map.put(TireTable.PRESSURE_FR,cursor.getFloat(cursor.getColumnIndex(TireTable.PRESSURE_FR)));
            map.put(TireTable.PRESSURE_BL,cursor.getFloat(cursor.getColumnIndex(TireTable.PRESSURE_BL)));
            map.put(TireTable.PRESSURE_BR,cursor.getFloat(cursor.getColumnIndex(TireTable.PRESSURE_BR)));
            map.put(TireTable.TEMPERATURE_FL,cursor.getFloat(cursor.getColumnIndex(TireTable.TEMPERATURE_FL)));
            map.put(TireTable.TEMPERATURE_FR,cursor.getFloat(cursor.getColumnIndex(TireTable.TEMPERATURE_FR)));
            map.put(TireTable.TEMPERATURE_BL,cursor.getFloat(cursor.getColumnIndex(TireTable.TEMPERATURE_BL)));
            map.put(TireTable.TEMPERATURE_BR,cursor.getFloat(cursor.getColumnIndex(TireTable.TEMPERATURE_BR)));
        }
        return (HashMap) map;
    }

    public void insert() {
        SQLiteDatabase db = new DBHelper(mContext).getWritableDatabase();
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
        LogUtils.d("TireTableOperator insert values= " + values.toString());
        db.insert(TireTable.TABLE_NAME, null, values);
    }

    public void update() {
        ContentValues values = new ContentValues();
        values.put(TireTable.PRESSURE_FL, 1.3);
        values.put(TireTable.PRESSURE_FR, 1.3);
        values.put(TireTable.PRESSURE_BL, 1.3);
        values.put(TireTable.PRESSURE_BR, 1.3);
        values.put(TireTable.TEMPERATURE_FL, 1.3);
        values.put(TireTable.TEMPERATURE_FR, 1.3);
        values.put(TireTable.TEMPERATURE_BL, 1.3);
        values.put(TireTable.TEMPERATURE_BR, 1.3);
        LogUtils.d("TireTableOperator update URI= " + URI.toString());
        LogUtils.d("TireTableOperator update values= " + values.toString());
        resolver.update(URI, values, TireTable.ID + "=?", new String[]{"112"});
    }
}
