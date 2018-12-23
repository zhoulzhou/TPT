package com.example.tire.model;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

import com.example.tire.common.LogUtils;
import com.example.tire.datafactory.TireTableOperator;

import java.util.HashMap;

public class TirePressureDetectionModel implements ITirePressureDetectionModel {
    private TirePressureDetectionModel instance = null;
    private Context mContext;
    private IOnDataChangedListener mOnDataChangedListener;

    public TirePressureDetectionModel() {

    }

    public TirePressureDetectionModel getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            instance = new TirePressureDetectionModel();
        }
        return instance;
    }

    @Override
    public HashMap getTirePressureValueFromDB() {
        return TireTableOperator.getInstance().getValue();
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
            LogUtils.d("observer onChange ");
            mOnDataChangedListener.onDataChanged();
        }
    };
}
