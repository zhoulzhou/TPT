package com.example.tire.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

import com.example.tire.common.LogUtils;
import com.example.tire.common.TireUtils;
import com.example.tire.database.TireTableOperator;

import java.util.Random;

public class TirePressureDetectionModel implements ITirePressureDetectionModel {
    private volatile static TirePressureDetectionModel instance = null;
    private Context mContext;
    private IOnDataChangedListener mOnDataChangedListener;

    public TirePressureDetectionModel(Context context) {
        mContext = context;
    }

    public static TirePressureDetectionModel getInstance(Context context) {
        if (instance == null) {
            synchronized (TirePressureDetectionModel.class) {
                if (instance == null) {
                    instance = new TirePressureDetectionModel(context);
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
        mContext.getContentResolver().registerContentObserver(TireTableOperator.URI,true,observer);
    }

    @Override
    public void unregisterDBObserver() {
        mContext.getContentResolver().unregisterContentObserver(observer);
    }

    @Override
    public void setOnDataChangedListener(IOnDataChangedListener listener) {
        mOnDataChangedListener = listener;
    }

    private ContentObserver observer = new ContentObserver(new Handler()) {
        @Override
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            mOnDataChangedListener.onDataChanged();
        }
    };

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
