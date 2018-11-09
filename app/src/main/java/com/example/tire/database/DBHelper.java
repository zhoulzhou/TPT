package com.example.tire.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tire.common.LogUtils;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "tirepressuredetection.db";
    private final static int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TireTable.TABLE_NAME + "("
                + TireTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TireTable.PRESSURE_FL + " REAL, " + TireTable.PRESSURE_FR + " REAL, "
                + TireTable.PRESSURE_BL + " REAL, " + TireTable.PRESSURE_BR + " REAL, "
                + TireTable.TEMPERATURE_FL + " REAL, " + TireTable.TEMPERATURE_FR + " REAL, "
                + TireTable.TEMPERATURE_BL + " REAL, " + TireTable.TEMPERATURE_BR + " REAL "
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.d("DBHelper onUpgrade ");
    }
}
