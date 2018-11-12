package com.example.tire.database;

import android.provider.BaseColumns;

public class TireTable implements BaseColumns {
    public final static String TABLE_NAME = "tire";

    public final static String ID= "id";
    public final static String PRESSURE_FL = "pressureFL";
    public final static String PRESSURE_FR = "pressureFR";
    public final static String PRESSURE_BL = "pressureBL";
    public final static String PRESSURE_BR = "pressureBR";
    public final static String TEMPERATURE_FL = "temperatureFL";
    public final static String TEMPERATURE_FR = "temperatureFR";
    public final static String TEMPERATURE_BL = "temperatureBL";
    public final static String TEMPERATURE_BR = "temperatureBR";
}
