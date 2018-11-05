package com.example.tire.model;

import com.example.tire.common.LogUtils;
import com.example.tire.common.TireUtils;

import java.util.Random;

public class TirePressureDetectionModel implements ITirePressureDetectionModel {
    private volatile static TirePressureDetectionModel instance = null;

    public TirePressureDetectionModel() {

    }

    public static TirePressureDetectionModel getInstance() {
        if (instance == null) {
            synchronized (TirePressureDetectionModel.class) {
                if (instance == null) {
                    instance = new TirePressureDetectionModel();
                }
            }
        }
        return instance;
    }

    @Override
    public float getTirePressureFromDB(int whichTire) {
        float pressure = 0;
        switch (whichTire) {
            case TireUtils.TIRE_FL:
                pressure = generateRandomNumber();
                break;
            case TireUtils.TIRE_FR:
                pressure = generateIncreaseNumber();
                break;
            case TireUtils.TIRE_BL:
                pressure = generateRandomNumber();
                break;
            case TireUtils.TIRE_BR:
                pressure = generateIncreaseNumber();
                break;
            default:
                pressure = -1;
                break;
        }
        return pressure;
    }

    @Override
    public float getTireTemperatureFromDB(int whichTire) {
        float temperature = 0;
        switch (whichTire) {
            case TireUtils.TIRE_FL:
                temperature = generateRandomNumber() * 10;
                break;
            case TireUtils.TIRE_FR:
                temperature = generateIncreaseNumber() * 10;
                LogUtils.d("temperature= " + temperature);
                break;
            case TireUtils.TIRE_BL:
                temperature = generateRandomNumber() * 10;
                break;
            case TireUtils.TIRE_BR:
                temperature = generateIncreaseNumber() * 10;
                break;
            default:
                temperature = -1;
                break;
        }
        return temperature;
    }

    private float generateRandomNumber(){
        float min = 1f;
        float max = 10f;
        float random = min + new Random().nextFloat() * (max - min);
        return random;
    }

    private float f= 0;
    private float generateIncreaseNumber(){
        if(f <= 10){
            ++f;
        }else {
            f = 0;
        }
        LogUtils.d("f3= " + f);
        return f;
    }
}
