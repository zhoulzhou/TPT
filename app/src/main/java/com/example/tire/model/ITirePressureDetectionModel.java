package com.example.tire.model;

public interface ITirePressureDetectionModel {
    float getTirePressureFromDB(int whichTire);
    float getTireTemperatureFromDB(int whichTire);
}
