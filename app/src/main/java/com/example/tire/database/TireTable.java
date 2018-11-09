package com.example.tire.database;

import android.provider.BaseColumns;

public class TireTable implements BaseColumns {
    public final static String TABLE_NAME = "tire";

    public final static String PRESSURE_FL = "pressure_fl";
    public final static String PRESSURE_FR = "pressure_fr";
    public final static String PRESSURE_BL = "pressure_bl";
    public final static String PRESSURE_BR = "pressure_br";
    public final static String TEMPERATURE_FL = "temperature_fl";
    public final static String TEMPERATURE_FR = "temperature_fr";
    public final static String TEMPERATURE_BL = "temperature_bl";
    public final static String TEMPERATURE_BR = "temperature_br";
}
