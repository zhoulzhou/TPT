package com.example.tire.database;

import android.provider.BaseColumns;

public class TireTable implements BaseColumns {
    public final static String TABLE_NAME = "tire";

    public final static String ID= "id";
    public final static String PRESSURE_FL = "pressurefl";
    public final static String PRESSURE_FR = "pressurefr";
    public final static String PRESSURE_BL = "pressurebl";
    public final static String PRESSURE_BR = "pressurebr";
    public final static String TEMPERATURE_FL = "temperaturefl";
    public final static String TEMPERATURE_FR = "temperaturefr";
    public final static String TEMPERATURE_BL = "temperaturebl";
    public final static String TEMPERATURE_BR = "temperaturebr";
}
