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
//                LogUtils.d("getTirePressureFromDB pressure FL= " + pressure);
                break;
            case TireUtils.TIRE_FR:
                f = pressure = generateIncreaseNumber();
                LogUtils.d("getTirePressureFromDB pressure FR= " + pressure);
                break;
            case TireUtils.TIRE_BL:
                pressure = generateRandomNumber();
                break;
            case TireUtils.TIRE_BR:
                pressure = generateRandomNumber();
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
//                LogUtils.d("getTireTemperatureFromDB temperature FL= " + temperature);
                break;
            case TireUtils.TIRE_FR:
//                temperature = generateIncreaseNumber() * 10;
                temperature = f * 10;
                LogUtils.d("getTireTemperatureFromDB temperature FR= " + temperature);
                break;
            case TireUtils.TIRE_BL:
                temperature = generateRandomNumber() * 10;
                break;
            case TireUtils.TIRE_BR:
                temperature = generateRandomNumber() * 10;
                break;
            default:
                temperature = -1;
                break;
        }
        return temperature;
    }

    @Override
    public void registerDBObserver() {

    }

    @Override
    public void unregisterDBObserver() {

    }

    float min = 1f;
    float max = 10f;
    private float generateRandomNumber(){
        return min + new Random().nextFloat() * (max - min);
    }

    private float f= 1;
    private float generateIncreaseNumber(){
        if(f <= 10){
            f += 1;
        }else {
            f = 0;
        }
        LogUtils.d("f3= " + f);
        return f;
    }
}
