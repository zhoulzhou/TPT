package com.example.tire.datafactory;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.tire.application.TirePressureDetectionApplication;
import com.example.tire.common.LogUtils;
import com.example.tire.database.TireTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TireTableOperator {
    private Context mContext;
    public static Uri URI = Uri.parse("content://com.example.tire.provider/" + TireTable.TABLE_NAME);
    private ContentResolver resolver;
    private static volatile TireTableOperator instance;

    private TireTableOperator() {
        mContext = TirePressureDetectionApplication.getContext();
        resolver = mContext.getContentResolver();
    }

    public static TireTableOperator getInstance() {
        if (instance == null) {
            synchronized (TireTableOperator.class) {
                if (instance == null) {
                    instance = new TireTableOperator();
                }
            }
        }
        return instance;
    }

    Map<String,Float> map = new HashMap<>(16);
    Cursor cursor;
    public HashMap getValue() {
        map.clear();
        cursor = resolver.query(URI, null, TireTable.ID + "=? ", new String[]{"112"},null);
        if(cursor !=null && cursor.moveToFirst()){
            LogUtils.d("TireTableOperator moveToFirst getCount= " + cursor.getCount());
            do {
                map.put(TireTable.PRESSURE_FL,cursor.getFloat(cursor.getColumnIndex(TireTable.PRESSURE_FL)));
                map.put(TireTable.PRESSURE_FR,cursor.getFloat(cursor.getColumnIndex(TireTable.PRESSURE_FR)));
                map.put(TireTable.PRESSURE_BL,cursor.getFloat(cursor.getColumnIndex(TireTable.PRESSURE_BL)));
                map.put(TireTable.PRESSURE_BR,cursor.getFloat(cursor.getColumnIndex(TireTable.PRESSURE_BR)));
                map.put(TireTable.TEMPERATURE_FL,cursor.getFloat(cursor.getColumnIndex(TireTable.TEMPERATURE_FL)));
                map.put(TireTable.TEMPERATURE_FR,cursor.getFloat(cursor.getColumnIndex(TireTable.TEMPERATURE_FR)));
                map.put(TireTable.TEMPERATURE_BL,cursor.getFloat(cursor.getColumnIndex(TireTable.TEMPERATURE_BL)));
                map.put(TireTable.TEMPERATURE_BR,cursor.getFloat(cursor.getColumnIndex(TireTable.TEMPERATURE_BR)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        LogUtils.d("TireTableOperator getValue map= " + map.toString());
        return (HashMap) map;
    }

    public void insert() {
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
        resolver.insert(URI,values);
    }

    public void update() {
        f = generateIncreaseNumber();
        ContentValues values = new ContentValues();
        values.put(TireTable.PRESSURE_FL, generateRandomNumber());
        values.put(TireTable.PRESSURE_FR, f);
        values.put(TireTable.PRESSURE_BL, generateRandomNumber());
        values.put(TireTable.PRESSURE_BR, generateRandomNumber());
        values.put(TireTable.TEMPERATURE_FL, generateRandomNumber()*10);
        values.put(TireTable.TEMPERATURE_FR, f * 10);
        values.put(TireTable.TEMPERATURE_BL, generateRandomNumber()*10);
        values.put(TireTable.TEMPERATURE_BR, generateRandomNumber()*10);
        LogUtils.d("TireTableOperator update URI= " + URI.toString());
        LogUtils.d("TireTableOperator update values= " + values.toString());
        resolver.update(URI, values, TireTable.ID + "=?", new String[]{"112"});
    }

    float min = 1f;
    float max = 10f;
    private float generateRandomNumber(){
        return min + new Random().nextFloat() * (max - min);
    }

    private float f= 0;
    public float generateIncreaseNumber(){
        if(f <= 10){
            f += 1;
        }else {
            f = 0;
        }
        LogUtils.d("generateIncreaseNumber f= " + f);
        return f;
    }
}
