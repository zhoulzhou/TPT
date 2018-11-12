package com.example.tire.presenter;

import android.os.Handler;

import com.example.tire.common.LogUtils;
import com.example.tire.common.TireUtils;
import com.example.tire.database.TireTable;
import com.example.tire.model.IOnDataChangedListener;
import com.example.tire.model.ITirePressureDetectionModel;
import com.example.tire.view.ITirePressureDetectionView;

import java.util.HashMap;
import java.util.Map;

public class TirePressureDetectionPresenter implements ITirePressureDetectionPresenter {
    private ITirePressureDetectionView mTirePressureDetectionView;
    private ITirePressureDetectionModel mTirePressureDetectionModel;
    private Handler mHandler = new Handler();
    private Map<String,Float> mTirePressureMap = new HashMap<>(16);

    public TirePressureDetectionPresenter(ITirePressureDetectionModel model){
        mTirePressureDetectionModel = model;
    }

    @Override
    public void attachView(ITirePressureDetectionView view) {
        mTirePressureDetectionView = view;
    }

    @Override
    public void detachView() {
        mTirePressureDetectionView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mTirePressureDetectionView != null;
    }

    @Override
    public void startTireDetection() {
        mTirePressureDetectionModel.registerDBObserver();
        mTirePressureDetectionModel.setOnDataChangedListener(new IOnDataChangedListener() {
            @Override
            public void onDataChanged() {
                if(!isViewAttached()){
                    return;
                }

                if(mTirePressureDetectionModel == null){
                    return;
                }

                mTirePressureMap = mTirePressureDetectionModel.getTirePressureValueFromDB();
                mTirePressureDetectionView.showTirePressureFL(mTirePressureMap.get(TireTable.PRESSURE_FL),
                        mTirePressureMap.get(TireTable.TEMPERATURE_FL));
                mTirePressureDetectionView.showTirePressureFR(mTirePressureMap.get(TireTable.PRESSURE_FR),
                        mTirePressureMap.get(TireTable.TEMPERATURE_FR));
                mTirePressureDetectionView.showTirePressureBL(mTirePressureMap.get(TireTable.PRESSURE_BL),
                        mTirePressureMap.get(TireTable.TEMPERATURE_BL));
                mTirePressureDetectionView.showTirePressureBR(mTirePressureMap.get(TireTable.PRESSURE_BR),
                        mTirePressureMap.get(TireTable.TEMPERATURE_BR));
            }
        });
    }

    @Override
    public void stopTireDetection() {
        mTirePressureDetectionModel.unregisterDBObserver();
        mHandler.removeCallbacksAndMessages(null);
    }
}
