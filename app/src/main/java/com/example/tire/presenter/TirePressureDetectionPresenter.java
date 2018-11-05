package com.example.tire.presenter;

import com.example.tire.model.ITirePressureDetectionModel;
import com.example.tire.view.ITirePressureDetectionView;

public class TirePressureDetectionPresenter implements ITirePressureDetectionPresenter {
    private ITirePressureDetectionView mTirePressureDetectionView;
    private ITirePressureDetectionModel mTirePressureDetectionModel;

    public TirePressureDetectionPresenter(ITirePressureDetectionView view, ITirePressureDetectionModel model){
        mTirePressureDetectionView = view;
        mTirePressureDetectionModel = model;
    }

    @Override
    public float getTirePressure(int whichTire) {
        return 0;
    }

    @Override
    public float getTireTemperature(int whichTire) {
        return 0;
    }

    @Override
    public void startTireDetection() {

    }

    @Override
    public void stopTireDetection() {

    }
}
