package com.example.tire.presenter;

import android.os.Handler;

import com.example.tire.common.TireUtils;
import com.example.tire.model.ITirePressureDetectionModel;
import com.example.tire.view.ITirePressureDetectionView;

public class TirePressureDetectionPresenter implements ITirePressureDetectionPresenter {
    private ITirePressureDetectionView mTirePressureDetectionView;
    private ITirePressureDetectionModel mTirePressureDetectionModel;
    private Handler mHandler = new Handler();

    public TirePressureDetectionPresenter(ITirePressureDetectionView view, ITirePressureDetectionModel model){
        mTirePressureDetectionView = view;
        mTirePressureDetectionModel = model;
    }

    @Override
    public float getTirePressure(int whichTire) {
        return mTirePressureDetectionModel.getTirePressureFromDB(whichTire);
    }

    @Override
    public float getTireTemperature(int whichTire) {
        return mTirePressureDetectionModel.getTireTemperatureFromDB(whichTire);
    }

    @Override
    public void startTireDetection() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //应该Model层去获取数据，回调到presenter层
                mTirePressureDetectionView.showTirePressureFL(getTirePressure(TireUtils.TIRE_FL),
                        getTireTemperature(TireUtils.TIRE_FL));
                mTirePressureDetectionView.showTirePressureFR(getTirePressure(TireUtils.TIRE_FR),
                        getTireTemperature(TireUtils.TIRE_FR));
                mTirePressureDetectionView.showTirePressureBL(getTirePressure(TireUtils.TIRE_BL),
                        getTireTemperature(TireUtils.TIRE_BL));
                mTirePressureDetectionView.showTirePressureBR(getTirePressure(TireUtils.TIRE_BR),
                        getTireTemperature(TireUtils.TIRE_BR));
            }
        }, 1000);
    }

    @Override
    public void stopTireDetection() {
        mHandler.removeCallbacksAndMessages(null);
    }
}
